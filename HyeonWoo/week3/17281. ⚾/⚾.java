import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] player;        // 이닝 별 타자의 결과를 저장 할 2차원 배열
    static int[] batting_order;   // 타순을 저장 할 배열
    static boolean[] visited;     // 타순을 정할 때 타자를 선택했는지 체크 할 배열
    static boolean[] ground;      // 주자가 베이스에 나가고 들어오는 것을 관리 할 배열
    static int out;               // 아웃을 카운트 할 배열
    static int score;             // 경우마다 점수의 총합을 저장할 배열
    static int next;              // 이닝이 모두 끝날 때까지 타자의 순서를 관리 할 변수
    static int max = 0;           // 결과 값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        player = new int[n][10];

        for(int inning = 0; inning < n; inning++) {
            st = new StringTokenizer(br.readLine());

            for(int i = 1; i < 10; i++) {
                int current_player = Integer.parseInt(st.nextToken());
                player[inning][i] = current_player;                     // 이닝 별 타자의 결과를 저장
            }
        }

        batting_order = new int[10];
        batting_order[4] = 1;                                           // 4번 타자는 첫번 째 선수로 고정

        visited = new boolean[10];
        visited[1] = true;

        permutation(1);                                                 // 순열로 경우의 수를 구함

        System.out.print(max);                                          // 결과 출력
    }

    static void permutation(int index) {    // 순열을 통해 모든 경우의 타순을 구하는 함수
        if(index == 4) {                    // 4번 타자는 고정이기 때문에 바로 재귀호출
            permutation(5);
            return;
        }
        if(index == 10) {                   // 모든 타순을 정했다면
            playGame();                     // 게임 실행
            return;
        }

        for(int i = 2; i < 10; i++) {       // 백트래킹을 통해 경우의 수를 확인
            if(visited[i]) continue;

            visited[i] = true;
            batting_order[index] = i;
            permutation(index + 1);
            visited[i] = false;
            batting_order[index] = 0;
        }
    }

    static void playGame() {                                // 정해진 타순으로 게임을 실행하는 함수
        score = 0;
        next = 1;                                           // 모든 경우를 이닝이 시작하기 전 타자를 첫번째부터 확인

        for(int inning = 0; inning < n; inning++) {
            ground = new boolean[4];                        // 새로운 이닝이 시작할 때마다 베이스를 초기화
            out = 0;

            while(out < 3) {                                // 아웃이 세번 카운트 될때까지 반복
                int order = batting_order[next++];          // next를 하나씩 키워주며 다음 타자의 번호를 확인

                switch (player[inning][order]) {            // 타자의 번호에 해당된 결과를 확인
                    case 0:                                 // 0일 경우 아웃을 카운트
                        out++;
                        break;
                    case 1:                                 // 1일 경우
                        for(int i = 3; i > 0; i--) {
                            if(ground[i]) {
                                if(i == 3) {                // 3루에 선수가 있을 경우
                                    ground[i] = false;      // 3루 선수 제거 및 점수 카운트
                                    score++;
                                }
                                else {                      // 1루, 2루인 경우
                                    ground[i] = false;      // 1만큼 이동
                                    ground[i + 1] = true;
                                }
                            }
                        }
                        ground[1] = true;                   // 공을 친 타자도 이동
                        break;
                    case 2:
                        for(int i = 3; i > 0; i--) {        // 2일 경우
                            if(ground[i]) {
                                if(i >= 2) {                // 2루, 3루의 경우
                                    ground[i] = false;      // 2루, 3루 선수 제거 및 점수 카운트
                                    score++;
                                }
                                else {                      // 1루의 경우
                                    ground[i] = false;      // 2만큼 이동
                                    ground[i + 2] = true;
                                }
                            }
                        }
                        ground[2] = true;                   // 공을 친 타자도 이동
                        break;
                    case 3:
                        for(int i = 3; i > 0; i--) {        // 모든 주자를 제거 및 점수 카운트
                            if(ground[i]) {
                                ground[i] = false;
                                score++;
                            }
                        }
                        ground[3] = true;                   // 공을 친 타자도 이동
                        break;
                    case 4:                                 // 4일 경우
                        for(int i = 3; i > 0; i--) {        
                            if(ground[i]) {                 // 모든 주자를 제거 및 점수 카운트
                                ground[i] = false;
                                score++;
                            }
                        }
                        score++;                            // 공을 친 타자도 점수 카운트
                        break;
                }

                if(next == 10) next = 1;                    // 다음 타순이 10이라면 1로 줄여줌
            }
        }

        max = Math.max(max, score);
    }
}
