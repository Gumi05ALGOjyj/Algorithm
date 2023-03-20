import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class num1495기타리스트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,S,M;
    static int[][] dp;
    static int[] volume;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        volume = new int[N];
        dp = new int[N][M+1];

        st= new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            volume[i] = Integer.parseInt(st.nextToken());
        }

        if(S+volume[0] <=M){
            dp[0][S+volume[0]] = 1;
        }
        if(S - volume[0] >=0){
            dp[0][S-volume[0]] = 1;
        }

        for(int i=1; i<N; i++){
            calc(i);
        }

        System.out.println(getMaxVol());


    }
    private static void calc(int cur) {
        int v = volume[cur];
        int prev = cur-1;
        for(int i=0;i<=M;i++)
            if(dp[prev][i]==1) {
                if(i + v <= M) dp[cur][i+v] = 1;
                if(i - v >= 0) dp[cur][i-v] = 1;
            }
    }

    private static int getMaxVol() {
        for(int i=M;i>=0;i--)
            if(dp[N-1][i]==1) return i;
        return -1;
    }
}
