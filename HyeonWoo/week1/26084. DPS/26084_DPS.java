import java.util.*;
import java.io.*;

public class Main {
    static String S[];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
      
        S = new String[3];
        S = st.nextToken().split("");

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        long[] visited = new long[26];                                                // 팀 이름의 알파벳을 저장할 배열
        for(int i = 0; i < 3; i++) {
            int alpha_num = (int)S[i].charAt(0) - 65;                                 // 아스키코드로 변경 후 시작을 0으로 맞추기 위해 -65
            visited[alpha_num]++;                                                     // 구한 인덱스에 해당 되는 값 +1
        }

        long[] alpha_visited = new long[26];                                          // 핸들의 첫글자의 알파벳을 저장할 배열
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int alpha_num = (int)st.nextToken().charAt(0) - 65;                       // 20줄과 동일
            alpha_visited[alpha_num]++;
        }

        long count = 1;
        for(int i = 0; i < 26; i++) {                                                 // 팀 이름에 없는 알파벳은 생략
            if(visited[i] != 0) count *= solution(alpha_visited[i], visited[i]);      // 순서 상관 없이 핸들의 특정 글자의 개수 중에서 
        }                                                                             // 동일한 팀 이름의 글자 개수를 선택하는 경우의 수  => 조합
                                                                                      // 모두 count에 곱하여 모든 경우의 수를 구함
        System.out.print(count);
    }

    static long solution(long n, long r) {                                            // n개(핸들)에서 r개(팀 이름)를 선택하는 경우의 수
        if (r == 1) {
            return n;
        } else if (r == 2) {
            return (n * (n - 1)) / 2;
        } else {
            return (n * (n - 1) * (n - 2)) / 6;
        }
    }
}
