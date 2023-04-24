import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.function.IntUnaryOperator;

public class Main {
    static int N, K;
    static int sensors[];
    static int diff[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        sensors = new int[N];
        diff = new int[N];  //Distance difference between the sensor and the concentrating station

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            sensors[i] = Integer.parseInt(st.nextToken());

        if (K >= N) {
            System.out.println("0");
            return;
        }

        //sort
        Arrays.sort(sensors);

        //diff[0] = sensors[0];
        for (int i = 0; i < N-1; i++) {
            diff[i] = sensors[i+1]-sensors[i];
        }

        int result=0;

        Arrays.sort(diff);
        for (int i = 0; i < N-K+1; i++) {
            result+=diff[i];
        }

        System.out.println(result);
    }
}
