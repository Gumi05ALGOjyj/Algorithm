import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.TreeSet;

public class Main {

	public static int s, N, K, r[], c[];

	public static StringBuilder sb;

	public static int[][] total;

	public static char[][] memo;

	// 화이트 : 0, 블랙 : 1
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		r = new int[2];
		c = new int[2];

		s = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		for (int i = 0; i < 2; i++) {
			r[i] = Integer.parseInt(str[i + 3]);
			c[i] = Integer.parseInt(str[i + 5]);
		}

		//memo = new char[(int) Math.pow(N, s)][(int) Math.pow(N, s)];
		
		memo = new char[51][51];
		
		extendsRec(0, 0, (int) Math.pow(N, s), false);

		sb = new StringBuilder();
		for (int i = 0; i <= r[1] - r[0]; i++) {
			for (int j = 0; j <= c[1] - c[0]; j++) {
				sb.append(memo[i][j] + "");
			}
			sb.append("\n");
		}

//		sb = new StringBuilder();
//		for(int i = r[0]; i<=r[1]; i++) {
//			for(int j = c[0]; j<=c[1]; j++) {
//				sb.append(memo[i][j]+"");
//			}
//			sb.append("\n");
//		}

		System.out.println(sb.toString());

	}
  // 분할탐색
	public static void extendsRec(int x, int y, int size, boolean isBlack) {
		if (y > r[1] || y + size <= r[0] || x > c[1] || x + size <= c[0]) { // r1,c1 ~ r2,c2를 벗어나는 경우 return을 반환한다.
			return;
		}

		if (size == 1) {
			if (isBlack)
				memo[y - r[0]][x - c[0]] = '1';
			else
				memo[y - r[0]][x - c[0]] = '0';

//			if (isBlack)
//				memo[y][x] = '1';
//			else
//				memo[y][x] = '0';
			return;
		}

		int nSize = size / N;
		int blackStart = (N - K) / 2;
		int blackEnd = N - blackStart;
		int nx, ny;
		for (int i = 0; i < N; i++) {
			ny = y + i * nSize;
			for (int j = 0; j < N; j++) {
				nx = x + j * nSize;
				extendsRec(nx, ny, nSize,
						isBlack || (blackStart <= i && i < blackEnd) && (blackStart <= j && j < blackEnd)); // 이전에 isBlack이 true였거나 black의 범위안에 존재한다면 true를 반환한다.
			}
		}
	}

}
