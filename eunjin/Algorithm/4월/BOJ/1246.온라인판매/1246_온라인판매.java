import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, price[];
    static int resultPrice = 0, resultIncome = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        price = new int[M];
        for (int i = 0; i < M; i++) {
            price[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(price);
        int addP =0;
        for (int i = 0; i < M; i++) {
            if(M-i>N) addP = N;
            else addP = M-i;
            int nowIncom=(addP)*price[i];
            if(nowIncom>resultIncome){
                resultIncome = nowIncom;
                resultPrice = price[i];
            }
        }

        System.out.println(resultPrice+ " "+resultIncome);

    }
}
