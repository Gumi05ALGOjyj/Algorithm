package 백준.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 팀원모집_11578 {
    static int N,M;
    static int MIN;
    static int result;
    static int[] memInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MIN = N+1;

        memInfo = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
               memInfo[i]+=Math.pow(2,Integer.parseInt(st.nextToken())-1);
            }
        }
        //----- 멤버 정보까지 입력 완료 ----------

            com(0,0,0);
        System.out.println(MIN==N+1?-1:MIN);
    }


    static public void com(int cnt,int start,int result){
        if(Integer.bitCount(result)==N){
            MIN = Math.min(MIN,cnt);
            return;
        }


        for (int i = start; i < M; i++) {

                com( cnt+1, i+1,result);

                com(cnt+1, i+1, result|memInfo[i]);

        }

    }
}
