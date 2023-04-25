import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] top = new int[h + 1];
        int[] bottom = new int[h + 1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int current = Integer.parseInt(st.nextToken());

            if(i % 2 == 0) bottom[current] += 1;
            else top[h - current + 1] += 1;
        }

        for(int i = 2; i <= h; i++) {
            top[i] += top[i - 1];
        }
        for(int i = h - 1; i > 0; i--) {
            bottom[i] += bottom[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int count = 0;

        for(int i = 1; i <= h; i++) {
            int sum = top[i] + bottom[i];

            if(min > sum) {
                min = sum;
                count = 1;
            }
            else if(min == sum) count++;
        }

        System.out.print(min + " " + count);
    }
}
