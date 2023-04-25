import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[k + 1][n + 1];                             // 경우의 수를 저장할 배열 생성

        for(int i = 0; i <= n; i++) dp[1][i] = 1;                       // 첫줄과 0을 만드는 경우는 모두 1가지 경우이므로 1로 초기화
        for(int i = 1; i <= k; i++) dp[i][0] = 1;

        int TEMP = 1_000_000_000;                                       // 문제에 맞춰 10억으로 나눠주기 위한 상수

        for(int i = 2; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] % TEMP + dp[i][j - 1] % TEMP;   // 점화식에 맞게 계산
            }
        }

        System.out.print(dp[k][n] % TEMP);
    }
}
