package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합분해_2225 {
    static int N,K;
    static int resultArr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        resultArr= new int[K+1][N+1];
        Arrays.fill(resultArr[1],1);
        for (int i = 2; i < K+1; i++) {
            for (int j = 0; j < N+1; j++) {
                if(j==0){
                    resultArr[i][j]=1;
                    continue;
                }
//                for (int k = 1; k<=i; k++) {
//                    resultArr[i][j]+=(resultArr[k][j-1]%1000000000);
//                }
//                resultArr[i][j]%=1000000000;
                resultArr[i][j] = (resultArr[i-1][j]+resultArr[i][j-1])%1000000000;
            }
        }
        resultArr[K][N]%=1000000000;

        System.out.println(resultArr[K][N]);
    }
}
