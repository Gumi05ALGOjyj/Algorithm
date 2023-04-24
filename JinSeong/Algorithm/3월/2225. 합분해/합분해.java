import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class num2225합분해 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[K+1][N+1];
        Arrays.fill(dp[1],1);
        for(int i=0; i<=K; i++){
            dp[i][0]=1;
        }
        for(int i=2; i<=K; i++){
            for(int j=1;j<=N; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                dp[i][j] %=1000000000;
            }
        }
        System.out.println(dp[K][N]);
    }
}
