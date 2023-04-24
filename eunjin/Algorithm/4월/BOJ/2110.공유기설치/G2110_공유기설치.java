import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int C;
    static int[] home;
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        home = new int[N];

        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);
        int start = 1;
        int end = home[N-1]-home[0]+1;
        int mid;
        while(start<end){
            mid = (start +end)/2;
//            if(install(mid)==C) {
//                result = mid;
//                break;
//            }
            //내가 설치한 공유기가 목적한 공유기 수보다 많다? 그럼 dist를 늘려줘야한다.
            if(install(mid)<C){
                end = mid;

              //  start = mid+1;
            }else{
//                result = end;
                start =mid+1;
            }

        }

        System.out.println(start-1);




    }

    public static int install(int distance){
        int install = home[0];
        int count = 1;
        for (int i = 1; i < N; i++) {
            int now = home[i];
            if(now-install>=distance){
                count++;
                install = now;
            }
        }
        return count;
    }
}
