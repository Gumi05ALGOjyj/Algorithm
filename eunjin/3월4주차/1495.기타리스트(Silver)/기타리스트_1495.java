package 백준.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.StringTokenizer;

public class 기타리스트_1495 {
    static int N,start,max;
    static int result;
    static int[] V;
    static boolean[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        V = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }
        //----- END : 입력받기 --------------

        //초기값을 설정해준다.
        dp = new boolean[N][max+1];
        if(start-V[0]>=0){
            dp[0][(start-V[0])] = true;
        }

        if(start+V[0]<=max){
            dp[0][(start+V[0])] = true;
        }

        for (int i = 1; i < N; i++) {
            //N-1번 반복해준다 0번째는 앞에서 초기화해주었기 때문!!
            for (int j = 0; j < max+1; j++) {
                if(!dp[i-1][j]) continue;
                int tmp = j+V[i];
                if(tmp<=max && tmp>=0) dp[i][tmp]=true;
                tmp = j-V[i];
                if(tmp<=max && tmp>=0) dp[i][tmp]=true;
            }
        }

        boolean flag = false;
        for (int i = max; i >=0 ; i--) {
            if(dp[N-1][i]){
                result = i;
                flag = true;
                break;
            }
        }
        System.out.println(!flag?-1:result);
    }
}
