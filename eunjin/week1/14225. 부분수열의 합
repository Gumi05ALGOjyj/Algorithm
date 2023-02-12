import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int inputs[];
	static boolean flags[] = new boolean[2000001];
	static boolean isVisited[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		isVisited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		func(0, 0);
		for (int i = 0; i < flags.length; i++) {
			if (!flags[i]) {
				sb.append(i);
				break;
			}
		}

		System.out.println(sb);

	}

	public static void func(int cnt, int sum) {
		if (cnt == N) {
			flags[sum] = true;
			return;
		}

		// 선택했을경우
		isVisited[cnt] = true;
		func(cnt + 1, sum + inputs[cnt]);
		// 선택안했을경우
		isVisited[cnt] = false;
		func(cnt + 1, sum);

	}
}
