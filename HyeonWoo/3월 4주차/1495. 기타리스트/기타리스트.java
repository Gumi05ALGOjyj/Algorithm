import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nsmVolume = br.readLine().split(" ");
        String[] volumes = br.readLine().split(" ");

        int n = Integer.parseInt(nsmVolume[0]);
        int s = Integer.parseInt(nsmVolume[1]);
        int m = Integer.parseInt(nsmVolume[2]);

        int[] volume = new int[n];
        for (int i = 0; i < n; i++) {
            volume[i] = Integer.parseInt(volumes[i]);
        }

        boolean[][] dp = new boolean[51][1001];
        dp[0][s] = true;

        int max = -1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (!dp[i - 1][j]) continue;

                if (j - volume[i - 1] >= 0) dp[i][j - volume[i - 1]] = true;
                if (j + volume[i - 1] <= m) dp[i][j + volume[i - 1]] = true;
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[n][i]) {
                max = i;
                break;
            }
        }

        System.out.println(max);
    }

}
