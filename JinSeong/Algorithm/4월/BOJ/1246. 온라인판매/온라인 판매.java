import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class num1246온라인판매 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M, arr[];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr= new int[M];
        for(int i=0; i<M; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int max = Integer.MIN_VALUE;
        int idx=0;

        for(int i=0; i<M; i++){
            int tmp = arr[i];
            int tmpsum=0;
            if(M-i<N){
                // 구매 가능한 잠재고객의 수가 달걀의 수보다 많을 경우는 최대 달걀의 수만큼만 구매한 것으로 계산
                tmpsum = tmp *(M-i);
            }
            else{
                tmpsum = tmp *N;
            }
            if(tmpsum>max){
                max= tmpsum;
                idx = tmp;
            }
        }

        System.out.println(idx+" "+max);
    }
}
