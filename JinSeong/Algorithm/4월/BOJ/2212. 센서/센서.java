import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class num2212센서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K, arr[] ,diff[];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K =Integer.parseInt(br.readLine());
        arr= new int[N];
        diff = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for(int i=0; i<N-1; i++){
            diff[i] = arr[i+1]-arr[i];
        }


        Arrays.sort(diff);
        int sum=0;
        for(int i=0; i<N-K; i++){
            sum+=diff[i];
        }

        System.out.println(sum);

    }
}
