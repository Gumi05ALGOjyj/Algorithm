import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class num3020개똥벌레 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,H, up[], down[];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        up = new int[N/2];
        down = new int[N/2];
        for(int i=0; i<N/2; i++){
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(down);
        Arrays.sort(up);

        int min = N;
        int cnt=0;
        for(int i=1; i<H+1; i++) {
            int conflict =binarySearch(0, N/2, i, down) + binarySearch(0, N/2, H-i+1, up);
            if(min == conflict) {
                cnt++;
                continue;
            }
            if(min > conflict) {
                min = conflict;
                cnt=1;
            }
        }
        System.out.println(min +" " +cnt);
    }
    static int binarySearch(int left, int right, int h, int[] arr) {
        while(left<right) {
            int mid = (left+right)/2;

            if(arr[mid] >= h) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return arr.length-right;
    }
}
