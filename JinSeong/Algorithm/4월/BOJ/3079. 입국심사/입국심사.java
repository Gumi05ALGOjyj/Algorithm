import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class num3079입국심사 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long M, T[];
    static int N;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        T= new long[N];
        for(int i=0; i<N; i++){
            T[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(T);

        long start = 1;
        long end = M * T[N-1];
        long result=Long.MAX_VALUE;
        while(start<=end){
            long mid = (start+end)/2;
            long cnt=0;
            for(int i=0; i<N; i++){
                cnt+=(mid/T[i]);
                if(cnt>=M){
                    break;
                }
            }
            if(cnt>=M){
                result = Math.min(mid,result);
                end=mid-1;
            }
            else {
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}
