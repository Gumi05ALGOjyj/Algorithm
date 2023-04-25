import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static void comb(int cnt, int start) {
		if (size == cnt) {
			used[Arrays.stream(numbers).sum()] = true;
			return;
		}

		for (int i = start; i < N; i++) {
			numbers[cnt] = input[i];
			comb(cnt + 1, i + 1);

		}
	}

	static boolean[] used = new boolean[2000001];
	public static int size; 		// 부분집합 원소의 개수
	public static int N; 			// 수열 s의 크기
	public static int[] input; 		// N개의 원소를 가지고 있는 배열
	public static int[] numbers;  	        // size개의 크기의 배열, 조합이 저장될 배열

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];

		// 수열s의 각각의 원소를 배열에 저장하기
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// 부분집합 원소의 개수가 1부터 N까지인 부분집합
		for (size = 1; size <= N; size++) {
			numbers = new int[size];
			comb(0, 0);
		}

		// 부분 수열의 합으로 나올 수 없는 가장 작은 자연수 찾기
		for (int i = 1; i < used.length; i++) { // 자연수니까 i는 1부터
			if (used[i] == false) {
				System.out.println(i);
				break;
			}
		}
	}
}
