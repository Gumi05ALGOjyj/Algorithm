import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int[][] board;                                                           // 주어지는 격자 판을 저장할 이차원 배열
    static int island_num = 2;                                                      // 섬마다 붙여줄 임의의 번호
    static ArrayList<ArrayList<int[]>> island = new ArrayList<ArrayList<int[]>>();  // 섬마다 가능한 다리를 저장할 3차원 리스트
    static ArrayList<int[]> square_info;                                            // 위의 3차원 리스트에 저장 할 섬 하나의 다리 정보
    static boolean[] visited;                                                       // 섬 방문을 저장 할 배열
    static boolean[][] cross_visited;                                               // 다리 건넘을 저장 할 이차원 배열 -> 방향 O

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int len = 0;                                                             // 경우마다 총 길이를 저장하는 변수
    static int min = Integer.MAX_VALUE;                                             // 결과 값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for(int i = 0; i < n; i++) {                                    // 격자판 생성 및 저장
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        island.add(new ArrayList<int[]>());                             // 이미 격자판에 섬이 1로 표기되기 때문에
        island.add(new ArrayList<int[]>());                             // 임의의 섬 번호를 2부터 참조하기 위해 쓰레기 값 2개를 채워줌

        searchBoard();                                                  // 격자판을 탐색하며 섬마다 가능한 다리를 저장

        visited = new boolean[island_num + 1];                          // 섬의 방문을 저장할 배열
        visited[2] = true;                                              // 시작을 임의로 2번 섬부터 할 것이기 때문에 미리 방문 처리
        cross_visited = new boolean[island_num + 1][island_num + 1];    // 다리 건넘을 저장할 배열
                                                                        // -> cross_visited[i][j]가 true라면 i번 섬에서 j번 섬으로 이미 건넜다는 의미
        island_num -= 3;                                                // 방문하지 않은 섬을 확인하기 위해 값 수정
                                                                        // -> 0과 1 인덱스는 사용하지 않고 2는 이미 방문했기 때문에 3을 빼준다.
        crossIsland(2);                                                 // 2부터 가능한 경우를 확인

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);          // min값이 바뀌지 않았다면 불가능, 바뀌었다면 바뀐 값 
    }

    static void searchBoard() {                             // 격자판 탐색
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 1) {                      // 1을 발견했다면
                    square_info = new ArrayList<int[]>();   // 섬의 가능한 다리를 저장할 2차원 리스트 생성
                  
                    board[i][j] = -1;                       // 백트래킹을 위해 해당 위치에 -1을 저장
                    searchIsland(i, j);                     // 백트래킹 탐색 시작
                    board[i][j] = island_num++;             // 전부 탐색이 끝나면 다음 섬을 위해 후위증가 연산과 함께 임의의 섬 번호를 부여
                                                            // -> 2, 3, 4, ...
                    island.add(square_info);                // 섬의 가능한 다리를 저장한 리스트를 저장
                }
            }
        }
    }

    static void searchIsland(int x, int y) {                        // 백트래킹으로 하나의 섬을 탐색하는 함수
        for(int i = 0; i < 4; i++) {                                // 상하좌우를 확인
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;    // 격자판 범위 내 인지 확인

            if(board[nx][ny] == 0) {                                // 0이라면, 즉 바다라면 다리를 놓을 수 있는지 확인
                findGround(nx, ny, i);                              // 다리 확인 함수
            } else if(board[nx][ny] == 1) {                         // 1이라면, 즉 연결된 땅이라면 백트래킹 탐색
                board[nx][ny] = -1;
                searchIsland(nx, ny);
                board[nx][ny] = island_num;                         // 섬 번호를 부여
            }
        }
    }

    static void findGround(int x, int y, int dec) {           // 다리를 놓을 수 있는지 확인하는 함수
        int count = 0;                                        // 다리의 길이도 동시에 확인
        int tx = x;
        int ty = y;

        while(tx >= 0 && tx < n && ty >= 0 && ty < m) {       // 격자판 범위 내 일동안 반복
            if(board[tx][ty] > 0) {                           // 해당 좌표가 1이거나 임의로 부여한 섬 번호라면
                if(count >= 2) {                              // 조건 중 하나인 다리의 길이가 2 이상 일 때
                    int[] posi_info = {tx, ty, count};        // 출발 기준 다리의 끝 좌표와 다리의 길이를 저장
                    square_info.add(posi_info);
                }
                return;
            }

            count++;                                          // 다리의 길이 카운트

            tx += dx[dec];                                    // 입력받은 방향으로 좌표 수정
            ty += dy[dec];                                    // 0: 상, 1: 하, 2: 좌, 3: 우
        }
    }

    static void crossIsland(int index) {                                                            // 다리를 건너며 백트래킹으로 가능한 경우를 확인하는 함수
        if(island_num == 0) {                                                                       // 방문하지 않은 섬의 개수가 0이라면 min 값 최신화
            min = Math.min(min, len);
            return;
        }

        ArrayList<int[]> next = island.get(index);                                                  // 해당 번호의 섬의 다리 정보를 가져옴

        for(int[] info: next) {                                                                     // 다리 순회
            int nx = info[0];                                                                       // 다리의 끝 좌표: 다음 섬의 좌표
            int ny = info[1];
            int count = info[2];                                                                    // 다리의 길이
            int nIsland_num = board[nx][ny];                                                        // 다음 섬의 번호

            if(cross_visited[nIsland_num][index] && cross_visited[index][nIsland_num]) continue;    // 이미 다음 섬을 왕복 했다면 continue

            if(visited[nIsland_num]) {                                                              // 1. 이미 방문 한 섬이라면
                if(!cross_visited[nIsland_num][index] && !cross_visited[index][nIsland_num]) {      // 한번도 왕복한 적이 없는 경우
                    cross_visited[index][nIsland_num] = true;                                       // 지금 섬 -> 다음 섬 다리 방문 처리
                    len += count;                                                                   // 다리 길이 카운트
                    crossIsland(nIsland_num);                                                       // 백트래킹 탐색
                    len -= count;
                    cross_visited[index][nIsland_num] = false;
                } else if(!cross_visited[index][nIsland_num]) {                                     // 한번이라도 건넌적이 있다면
                    cross_visited[index][nIsland_num] = true;                                       // : 왕복은 위에서 걸러줬으므로 지금 섬 -> 다음 섬으로 가지 않은 경우만 확인
                    crossIsland(nIsland_num);                                                       // 백트래킹 탐색
                    cross_visited[index][nIsland_num] = false;
                }
            } else {                                                                                // 2. 방문하지 않은 섬이라면
                island_num--;                                                                       // 첫 방문이므로 섬 개수 -1
                visited[nIsland_num] = true;                                                        // 방문 처리

                if(!cross_visited[nIsland_num][index] && !cross_visited[index][nIsland_num]) {      // ----------
                    cross_visited[index][nIsland_num] = true;
                    len += count;
                    crossIsland(nIsland_num);
                    len -= count;
                    cross_visited[index][nIsland_num] = false;                                      // 1번의 과정과 동일
                } else if(!cross_visited[index][nIsland_num]) {
                    cross_visited[index][nIsland_num] = true;
                    crossIsland(nIsland_num);
                    cross_visited[index][nIsland_num] = false;
                }                                                                                   // ----------

                island_num++;
                visited[nIsland_num] = false;
            }
        }
    }
}
