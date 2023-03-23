import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] solvable;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        solvable = new int[m];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int value = 0;

            for(int j = 0; j < o; j++) {
                value += Math.pow(2, Integer.parseInt(st.nextToken()) - 1);
            }

            solvable[i] = value;
        }

        for(int i = 0; i < m; i++) {
            search(i, solvable[i], 1);
        }

        System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void search(int index, int bit, int count) {
        if(Integer.bitCount(bit) == n) {
            answer = Math.min(answer, count);
            return;
        }

        for(int i = index + 1; i < m; i++) {
            search(i, bit | solvable[i], count + 1);
        }
    }
}
