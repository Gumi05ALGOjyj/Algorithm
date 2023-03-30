import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int count = 0;

        while(countOne(n) > k) {

            int temp = n & -n;
            int posi = -1;

            while(temp > 0) {
                posi++;
                temp = temp >> 1;
            }

            count += Math.pow(2, posi);

            n = n + (n & -n);
        }

        System.out.print(count);
    }

    static int countOne(int n) {
        int current = n;
        int size = 0;

        while(current > 0) {
            if((current & 1) == 1) size++;
            current = current >> 1;
        }
        return size;
    }
}
