import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 2225_합분해 {
	
	public static int N,K;
	
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		
		dp = new int[N+1][K+1]; // (0,0) ~ (N,K)
		
		for(int i = 0; i<N+1; i++)
			Arrays.fill(dp[i], 1);
		
		for(int c = 2; c<K+1; c++) {
			for(int r = 1; r<N+1; r++) {
				int value;
				dp[r][c] = 0;
				for(int i =0; i<=r; i++) {
					value = r - i;
					dp[r][c] += dp[value][c-1];
					dp[r][c] = dp[r][c] %1000000000;
				}
			}
		}
		
		System.out.println(dp[N][K]%1000000000);

	}

}
