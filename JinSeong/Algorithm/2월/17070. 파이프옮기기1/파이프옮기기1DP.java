import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class num17070파이프옮기기1DP {

    private static int N, map[][], dp[][][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[0][1][0] = 1;
        System.out.println(dp());
    }

    private static int dp() {
        for (int i = 0; i < N; i++) {
            //파이프를 놓을 수 있는건 2열 부터임
            for (int j = 2; j < N; j++) {

                //길이 막혀있으면 continue
                if (map[i][j] == 1) continue;
                //가로
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

                //맨 윗줄인 경우 continue
                if (i == 0) continue;;
                //세로
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
                
                //해당 index에서 왼쪽이랑 오른쪽 벽이면 continue                
                if (map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;
                //대각선
                dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }
        return dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
    }
}
