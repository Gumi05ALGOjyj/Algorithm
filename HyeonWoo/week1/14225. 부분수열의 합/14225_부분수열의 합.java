import java.io.*;
import java.util.*;

class Solution_14225 {
    static int n;
    static int[] s;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = new int[n];
        int sum = 0;
      
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {                      // 전체 수열을 저장함과 동시에 sum 변수에 총 합계를 저장
            s[i] = Integer.parseInt(st.nextToken());
            sum += s[i];
        }

        visited = new boolean[sum + 2];                   // 방문 범위는 합계를 넘을 수 없으므로 합계 크기의 배열 생성

        solution(0, 0);                                   // dfs 함수 호출

        for(int i = 1; i <= sum + 1; i++) {
            if(!visited[i]) {                             // visited 배열을 작은 수 부터 검사하여 결과 출력
                System.out.print(i);
                return;
            }
        }
    }

    static void solution(int index, int sum) {
        if(index == n) {                                  // 최대 개수 일때 방문 처리
            visited[sum] = true;
        } else {
            solution(index + 1, sum);                     // 개수만 올라가고 합은 그대로인 경우
            solution(index + 1, sum + s[index]);          // 개수가 올라감과 동시에 합도 증가하는 경우
        }
    }
}
