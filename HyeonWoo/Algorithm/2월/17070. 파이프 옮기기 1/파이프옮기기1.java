package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_17070 {
    static int n;
    static String board[][];                                                        // 게임 격자판을 저장할 2차원 배열
    static boolean[][][][] visited;                                                 // 방문 처리를 할 4차원 배열
                                                                                    // -> 파이프의 방향도 고려해야 하기 때문에 4차원 배열
    static int[][] horizon = {{0, 1, 0, 1, 0}, {0, 1, 1, 1, 1}};                    // 파이프가 가로일 경우 가능한 다음 좌표 -> 가로, 대각선
    static int[][] perpen = {{1, 0, 1, 0, 2}, {1, 0, 1, 1, 1}};                     // 파이프가 세로일 경우 가능한 다음 좌표 -> 세로, 대각선
    static int[][] diagonal = {{1, 1, 0, 1, 0}, {1, 1, 1, 1, 1}, {1, 1, 1, 0, 2}};  // 파이프가 대각선일 경우 가능한 다음 좌표 -> 가로, 대각선, 세로

    static int count = 0;                                                           // 가능한 경우의 수를 저장 할 변수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new String[n][n];
        visited = new boolean[n][n][n][n];

        for(int i = 0; i < n; i++) {                                                // 격자 판 생성 및 저장
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++) {
                board[i][j] = st.nextToken();
            }
        }

        int[] init = {0, 0, 0, 1, 0};                                               // 초기 파이프 좌표 -> 다섯번 째 수는 모양을 저장하는 수
                                                                                    // 0: 가로, 1: 대각선, 2: 세로
        solution(init);                                                             // 백트래킹 탐색

        System.out.print(count);
    }

    static void solution(int[] current) {
        int x2 = current[2];                                                        // 파이프의 나아가는 방향에 해당되는 좌표
        int y2 = current[3];

        if(x2 == n - 1 && y2 == n - 1) {                                            // 목적지에 도착했다면 count + 1하고 종료
            count++;
            return;
        }

        if(current[4] == 0) {                                                       // 파이프가 가로 모양이라면
            for(int[] next: horizon) {                                              // 가로에서 가능한 다음 좌표를 확인
                int[] posi = move(current, next);                                   // 파이프를 움직이는 함수

                if(posi[0] != -1) {                                                 // 움직인 결과가 가능하다면 재귀 호출
                    visited[posi[0]][posi[1]][posi[2]][posi[3]] = true;
                    solution(posi);
                    visited[posi[0]][posi[1]][posi[2]][posi[3]] = false;
                }
            }
        }
        else if(current[4] == 2) {                                                  // 파이프가 가로 모양이라면
            for(int[] next: perpen) {
                int[] posi = move(current, next);

                if(posi[0] != -1) {
                    visited[posi[0]][posi[1]][posi[2]][posi[3]] = true;
                    solution(posi);
                    visited[posi[0]][posi[1]][posi[2]][posi[3]] = false;
                }
            }
        }
        else {                                                                      // 파이프가 대각선 모양이라면 
            for(int[] next: diagonal) {
                int[] posi = move(current, next);

                if(posi[0] != -1) {
                    visited[posi[0]][posi[1]][posi[2]][posi[3]] = true;
                    solution(posi);
                    visited[posi[0]][posi[1]][posi[2]][posi[3]] = false;
                }
            }
        }
    }

    static int[] move(int[] current, int[] next) {
        int[] answer = {-1, -1, -1, -1, next[4]};                                   // 반환을 할 배열

        int nx1 = current[0] + next[0];                                             // 움직인 후의 좌표
        int ny1 = current[1] + next[1];
        int nx2 = current[2] + next[2];
        int ny2 = current[3] + next[3];

        if(nx1 >= 0 && ny1 >= 0 && nx2 >= 0 && ny2 >= 0
                && nx1 < n && ny1 < n && nx2 < n && ny2 < n) {                      // 움직였을 때 격자 판 안의 범위라면
            if(visited[nx1][ny1][nx2][ny2] || board[nx2][ny2].equals("1")) return answer;   // 이미 방문했거나, 해당 칸에 벽이 있다면 종료

            if(next[4] == 1) {                                                              // 대각선 모양이라면
                if(board[nx2 - 1][ny2].equals("1") || board[nx2][ny2 - 1].equals("1"))      // 나아가는 방향에서 위, 왼쪽에 벽이 있다면 종료
                    return answer;
            }

            answer[0] = nx1;                                                                // 문제 없다면 반환값에 저장
            answer[1] = ny1;
            answer[2] = nx2;
            answer[3] = ny2;
        }

        return answer;
    }
}
