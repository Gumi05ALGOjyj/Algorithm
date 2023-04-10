package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동물원_1309 {
    static int N;
    static int[][] dp;
    static int result=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        //배열 생성! 이제 계산
        for (int i = 0; i < 3; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i-1][1]+dp[i-1][2])%9901;
            dp[i][1] = (dp[i-1][0]+dp[i-1][2])%9901;
            dp[i][2] = (dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%9901;
        }

        for (int i = 0; i < 3; i++) {
            result+=(dp[N-1][i]%9901);
        }
        result%=9901;

        System.out.println(result);
    }
}
