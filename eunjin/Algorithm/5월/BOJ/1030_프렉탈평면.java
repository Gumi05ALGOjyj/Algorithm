package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1030_프렉탈평면 {
    static int s,N,K,R1,R2,C1,C2;
    static StringBuilder sb = new StringBuilder();
    static int[][] map = new int[51][51];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());

        solve(0,0,(int)Math.pow(N,s),false);

        //배열의 최대 범위가 2^30 => 다 못만들어 준다.
        for(int i=0; i<=R2-R1; i++){
            for(int j=0; j<=C2-C1; j++){
                sb.append(map[i][j]+"");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void solve(int x, int y, int size, boolean isBlack){
        //지금 내가 구하는 범위 내에 있는지 먼저 구해준다.
        if(x > C2 || x+size <= C1|| y > R2 || y+size <= R1) return;
        if(size==1){
            map[y-R1][x-C1] = isBlack? 1:0;
            return;
        }
        int nextSize = size/N;
        int start = (N-K)/2;
        int end = N-start;
        for(int i=0; i<N; i++){
            int nextY = y+nextSize*i;
            for(int j=0; j<N; j++){
                int nextX = x+nextSize*j;
                solve(nextX,nextY,nextSize, isBlack || (i >= start && i < end) && (j >= start && j < end));
            }
        }
    }
}
