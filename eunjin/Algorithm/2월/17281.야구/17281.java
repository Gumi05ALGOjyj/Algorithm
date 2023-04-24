import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; // 이닝 이닝은 out이 3번 이루어지면 다음으로 넘어간다.
	static int power[][]; // 선수들이 이닝에서 얻을 수 있는 결과
	static int selectedPlayers[] = new int[10]; // 순열을 통해 모두 선택된 순서
	static boolean isSelected[] = new boolean[10]; // 선택이 되었는지 확인할 정보
	static int locations[]; // 0 = 시작 안함 1~3 = 루 4 = 홈
	static int MAX = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		power = new int[N][10]; // 각 루마다의 힘을 저장해둔다.

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}

			// System.out.println(Arrays.toString(power[i]));
		}

		selectPlayer(1);

		System.out.println(MAX);

	}

	public static void selectPlayer(int cnt) {
		// System.out.println();
		isSelected[1] = true;
		// System.out.println("실행이 되긴 함");
		if (cnt == 4) {
			// 1번 타자는 항상 4번째 이므로 고정해준다.
			selectedPlayers[cnt] = 1;
			selectPlayer(cnt + 1);
			return;
		}
		if (cnt == 10) {
			// 종료 조건
			int score = play();

			MAX = Math.max(MAX, score);

			return;
		}

		for (int i = 1; i < 10; i++) {

			if (isSelected[i])
				continue;
			selectedPlayers[cnt] = i;
			isSelected[i] = true;
			selectPlayer(cnt + 1);
			isSelected[i] = false;
		}
	}

	public static int play() {
		int score = 0;
		int out = 0;
		int idx = 0;

		for (int n = 0; n < N; n++) {
			// 이닝이 시작될 떄는 주자는 없으니까 location을 시작할때마다 생성해서 초기화 시켜 준다.
			boolean basteState[] = new boolean[4];
			out = 0;
			while (out < 3) {
				idx += 1;
				if (idx > 9)
					idx = 1;

				switch (power[n][selectedPlayers[idx]]) {
				case 0:
					out += 1;
					break;
				case 1: // 안타 : 타자와 모든 주자가 한루씩 진루
					// 현재 타자 한루씩 진루 -> 3루에 사람이 있으면 result++, 나머지는 한칸씩 전진한다.
					if (basteState[3]) {
						score++;
						basteState[3] = false;
					}
					if (basteState[2]) {
						basteState[2] = false;
						basteState[3] = true;
					}
					if (basteState[1]) {
						basteState[2] = true;
					}
					basteState[1] = true;
					break;
				case 2: // 타자와 모든 주자가 2루씩 진루 -> 1루에 있는 애는 3루로 2,3루 애는 점수가 됌
					if (basteState[3]) {
						score++;
						basteState[3] = false;
					}
					if (basteState[2]) {
						score++;
						basteState[2] = false;
					}
					if (basteState[1]) {
						basteState[1] = false;
						basteState[3] = true;
					}
					basteState[2] = true;
					break;
				case 3: // 1루타 -> 친 애는 3루로 1,2,3루에 있는 애는 점수가 됌
					if (basteState[3]) {
						score++;
						basteState[3] = false;
					}
					if (basteState[2]) {
						score++;
						basteState[2] = false;
					}
					if (basteState[1]) {
						score++;
						basteState[1] = false;
					}
					basteState[3] = true;
					break;
				case 4:
					for (int i = 0; i < 4; i++) {
						if (basteState[i]) {
							score++;
							basteState[i] = false;
						}

					}
					score++;
				}

			}
		}

		return score;
	}
}
