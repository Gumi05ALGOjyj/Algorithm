import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,C;
    static int arr[];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr= new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int max=0;

        int left=1;
        int right = arr[N-1]-arr[0];

        while(left<=right){
            int mid = (left+right)/2;
            int start = arr[0];
            int cnt=1;

            for(int i=1; i<N; i++){
                if(arr[i]-start>=mid){
                    cnt++;
                    start = arr[i];
                }
            }

            if(cnt>=C){
                max=mid;
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        System.out.println(max);
    }
}
