import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class num1309동물원 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        N= Integer.parseInt(br.readLine());

        dp= new long[N+1][3];
        Arrays.fill(dp[1],1);
        for(int i=2; i<=N; i++){
            //두 칸 다 사자를 안넣을 경우
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%9901;
            //첫 번째 칸에 사자를 넣을 경우
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            //두 번째 칸에 사자를 넣을 경우
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }

        long res = (dp[N][0] +dp[N][1] + dp[N][2]) % 9901;

        System.out.println(res);
    }
}
