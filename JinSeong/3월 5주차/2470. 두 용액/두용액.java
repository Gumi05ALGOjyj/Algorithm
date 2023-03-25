import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//
//public class num2470두용액 {
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static StringTokenizer st;
//    static int N;
//    static int[] arr;
//    public static void main(String[] args) throws IOException {
//        N = Integer.parseInt(br.readLine());
//        arr = new int[N];
//        st = new StringTokenizer(br.readLine());
//        for(int i=0; i<N; i++){
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//        Arrays.sort(arr);
//        int start = 0;
//        int end = arr.length-1;
//
//        int min = Integer.MAX_VALUE;
//        int left=0;
//        int right = 0;
//        int sum=0;
//
//        while(end>start){
//            sum=arr[start]+arr[end];
//            if(min>Math.abs(sum)){
//                min=Math.abs(sum);
//                left = arr[start];
//                right = arr[end];
//            }
//            if(sum>0){
//                end--;
//            }
//            else{
//                start++;
//            }
//        }
//        System.out.println(left+" "+right);
//    }
//
//}

public class num2470두용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, arr[];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;

        for (int i = 0; i < N - 1; i++) {

            int now = arr[i];
            int target = now * -1;

            int tmp = binary_search(i + 1, N, target, arr);

            int sum = Math.abs(arr[i] + tmp);

            if (Math.abs(answer) >= sum) {
                answer = sum;
                ans1 = arr[i];
                ans2 = tmp;
            }
        }

        if (ans1 < ans2) {
            System.out.println(ans1 + " " + ans2);
        } else {
            System.out.println(ans2 + " " + ans1);
        }


    }

    public static int binary_search(int start, int end, int target, int[] arr) {
        int min  = Integer.MAX_VALUE;
        int answer = 0;

        while (start < end) {
            int mid = (start + end) / 2;

            if (Math.abs(target - arr[mid]) < min) {
                min = Math.abs(target - arr[mid]);
                answer = arr[mid];
            }
            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid;
            } else {
                return target;
            }
        }

        return answer;
    }
}
