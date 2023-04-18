package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 분할과 정복, 재귀
 */

public class B5904_Moo게임 {
	static int N;
	static int currLength = 3;
	static int K = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		while (currLength < N) {
			K++;
			currLength = currLength + (K + 3) + currLength;
		}

		findMoo(K);
		System.out.println(sb);

	}

	public static void findMoo(int k) {
		// 정답일 조건 확인
		if (3 >= N) {
			if (N == 1)
				sb.append("m");
			else
				sb.append("o");
			return;
		}

		int tmp = (currLength - (k + 3)) / 2;
		if (N <= tmp) {
			// 앞 부분으로 간당!
			currLength = tmp;
			findMoo(k - 1);
		} else if (N - tmp <= k + 3 && N - tmp <= (k + 3) + tmp) {
			N -= tmp;
			if (N == 1)
				sb.append("m");
			else
				sb.append("o");
			return;
		} else {
			N -= tmp;
			N -= (k + 3);
			currLength = tmp;
			findMoo(k - 1);
		}

	}

}
