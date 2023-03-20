import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] p = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		int[][] dp = new int[m + 1][n];

		for(int i = 1; i <= m; i++) {
			BigInteger max = new BigInteger("-1");
			int[] max_count = new int[n];

			for(int j = n - 1; j >= 0; j--) {
				if(p[j] > i) continue;

				int[] pre = dp[i - p[j]].clone();
				pre[j]++;

				StringBuilder sb = new StringBuilder();

				for(int k = n - 1; k >= 0; k--) {
					int cnt = pre[k];

					while(cnt > 0) {
						sb.append(k);

						cnt--;
					}
				}

				BigInteger temp = new BigInteger(sb.toString());

				if (max.compareTo(temp) == -1) {
				      max = temp;
				      max_count = pre;
				}
			}

			if(!max.toString().equals("-1")) {
				dp[i] = max_count;
			}

			if(i == m) {
				System.out.print(max.toString());
			}
		}
	}
}
