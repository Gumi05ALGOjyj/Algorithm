import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;   //입국심사대의 개수
    static int M;   //사람의 개수;
    static long[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        table = new long[N];
        //long end = -1;
        long start = 0;
        long mid;
        for (int i = 0; i < N; i++) {
            table[i] = Long.parseLong(br.readLine());
            // end = Math.max(end, table[i] * M);
        }
        Arrays.sort(table);
        long end = M * table[N - 1];
        // time = new int[max_time + 1];
        long result = Long.MAX_VALUE;
        //end += 1;
        while (start <= end) {
            mid = (start + end) / 2;
            long count = 0;
            for (long num : table) {
                count+=(mid/num);
                if(count>M) break;
            }
//            long curCnt = count(mid);
            //작으면? 시간을 더 늘려준다.
            if (count < M) {
                start = mid + 1;
            } else {
                result = Math.min(result, mid);
                end = mid - 1;
            }

        }
        System.out.println(result);

    }

//    static public long count(long time) {
//        long count = 0;
//        for (int i = 0; i < N; i++) {
//            count += time / table[i];
//        }
//        return count;
//    }
}import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;   //입국심사대의 개수
    static int M;   //사람의 개수;
    static long[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        table = new long[N];
        //long end = -1;
        long start = 0;
        long mid;
        for (int i = 0; i < N; i++) {
            table[i] = Long.parseLong(br.readLine());
            // end = Math.max(end, table[i] * M);
        }
        Arrays.sort(table);
        long end = M * table[N - 1];
        // time = new int[max_time + 1];
        long result = Long.MAX_VALUE;
        //end += 1;
        while (start <= end) {
            mid = (start + end) / 2;
            long count = 0;
            for (long num : table) {
                count+=(mid/num);
                if(count>M) break;
            }
//            long curCnt = count(mid);
            //작으면? 시간을 더 늘려준다.
            if (count < M) {
                start = mid + 1;
            } else {
                result = Math.min(result, mid);
                end = mid - 1;
            }

        }
        System.out.println(result);

    }

//    static public long count(long time) {
//        long count = 0;
//        for (int i = 0; i < N; i++) {
//            count += time / table[i];
//        }
//        return count;
//    }
}
