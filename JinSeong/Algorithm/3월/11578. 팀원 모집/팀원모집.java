import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class num11578팀원모집 {
    static int student[];
    static int total, N, M, ans = 11;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        student = new int[M + 1];

        for (int i = 1; i <= N; i++) {
            total = (total | (1 << i));
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int flag = 0;
            for (int j = 1; j <= cnt; j++) {
                int value = Integer.parseInt(st.nextToken());
                flag = (flag | (1 << value));

            }
            student[i] = flag;
        }
        combination(1, 0, 0);
        if (ans != 11)
            System.out.println(ans);
        else
            System.out.println(-1);
    }

    static void combination(int index, int cnt, int flag) {
        if (flag == total)
        {
            ans = Math.min(ans, cnt);
        } else {
            for (int i = index; i <= M; i++) {
                combination(i + 1, cnt + 1, (flag | student[i]));
            }
        }

    }
}
