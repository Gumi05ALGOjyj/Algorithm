

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,H;
    static int[] top;
    static int[] bottom;
    static int resultCnt = 0;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        top = new int[H];//아래에서 위로
        bottom = new int[H];//위에서 아래로

        for (int i = 0; i < N; i++) {

            if(i%2==0){
                bottom[Integer.parseInt(br.readLine())-1] += 1;
            }
            else{
                //H-i-1;
                top[H-Integer.parseInt(br.readLine())] += 1;
            }
        }
        //====== 입력 다 받았음ㅋ =====================
        // 누적합 실시!
        //1. top은 아래에서 위로!

        for (int i = 1; i < H; i++) {
            top[i] += top[i-1];
        }

        //2.bottom은 위에서 아래로!
        for (int i = H-2; i >= 0 ; i--) {
            bottom[i] += bottom[i+1];
        }

        //이제 비교!!!
        for (int i = 0; i < H; i++) {

            if(top[i]+bottom[i]<min){
                min = top[i]+bottom[i];
                resultCnt=1;
            }else if(top[i]+bottom[i]==min){
                resultCnt+=1;
            }
        }
//        System.out.println(Arrays.toString(bottom));
//        System.out.println(Arrays.toString(top));
        System.out.println(min + " "+resultCnt);
    }
}
