package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_1926 {
    static int n;
    static int m;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};                                                    // 상, 하, 좌, 우를 검사하기 위한 배열
    static int[] dy = {0, 0, 1, -1};

    static int count = 0;
    static int max_size = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];                                                          // 도화지를 저장 할 2차원 배열
        visited = new boolean[n][m];                                                    // 방문을 체크 할 2차원 배열

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) board[i][j] = Integer.parseInt(st.nextToken());  // 도화지 원소를 2차원 배열에 저장
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && board[i][j] == 1) {                                // 방문을 하지 않았고 원소가 1이라면
                    count++;                                                            // 그림 개수를 +1
                    int temp_sum = solution(i, j, 1);                                   // 그림의 넓이를 확인하여 최대 값인지 판단 후 저장
                    max_size = Math.max(max_size, temp_sum);
                }
            }
        }

        System.out.print(count + "\n" + max_size);
    }
  
    static int solution(int x, int y, int sum) {                                        // 인접한 4방향을 dfs로 탐색
        visited[x][y] = true;                                                           // 방문 처리
        int temp_sum = 1;

        for(int i = 0; i < 4; i++) {                                                    // 상하좌우 확인
            int tx = x + dx[i];
            int ty = y + dy[i];

            if(tx > -1 && tx < n && ty > -1 && ty < m) {                                // 해당 값이 배열의 범위 안에 있다면
                if(!visited[tx][ty] && board[tx][ty] == 1) {                            // 방문하지 않았고 원소가 1이라면
                    temp_sum += solution(tx, ty, sum + 1);                              // 재귀 호출
                }
            }
        }

        return temp_sum;
    }
}
