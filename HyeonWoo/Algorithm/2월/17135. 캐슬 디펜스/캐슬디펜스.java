// solved

package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_17135 {
    static int n;
    static int m;
    static int d;
    static int[][] board;                                       // 입력을 저장하는 2차원 배열
    static ArrayList<int[]> enemy = new ArrayList<>();          // 적의 좌표를 저장하는 2차원 리스트
    static ArrayList<int[]> t_enemy = new ArrayList<>();        // 경우마다 적을 이동을 저장하는 2차원 리스트
    static int[] archer = new int[3];                           // 궁수의 y값을 저장하는 리스트 -> x값은 고정이기 때문에

    static int max_kill;                                        // 최대 값을 저장 할 변수
    static int enemy_count;                                     // 남은 적 수를 저장할 변수
    static int kill_count;                                      // 죽인 수를 저장할 변수
    static boolean[] kill_visited;                              // 한번 죽인 적은 쏘거나 이동할 필요가 없기 때문에 boolean으로 처리

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++) {
                int space = Integer.parseInt(st.nextToken());

                board[i][j] = space;                            // 격자 판 저장

                if(space == 1) {                                // 적 좌표 저장
                    int[] one_posi = {i, j};
                    enemy.add(one_posi);
                }
            }
        }

        Collections.sort(enemy, new Comparator<int[]>() {       // 같은 거리라면 왼쪽부터 쏘야한다는 조건이 있기 때문에
            @Override                                           // 왼쪽 적부터 볼 수 있게 y값 기준으로 오름차순 정렬
            public int compare(int[] e1, int[] e2) {
                return e1[1] == e2[1] ? e2[0] - e1[0] : e1[1] - e2[1];
            }
        });

        for(int i = 0; i <= m - 3; i++) {                       // 궁수 3명이 설 수 있는 모든 경우를 구하기 위한 함수 호출
            Archer(i, 0);
        }

        System.out.print(max_kill);                             // 최대 값 출력
    }

    static void Archer(int index, int count) {                  // 궁수 위치의 모든 경우를 구하는 함수
        if(count == 3) {                                        // 궁수가 3명 전부 채워졌다면
            Simulation();                                       // 적을 쏘고 움직이는 함수 호출
            return;
        }

        for(int i = index; i <= m - (3 - count); i++) {         // 백트래킹을 통해 궁수의 모든 경우를 저장
            board[n][i] = 2;
            archer[count] = i;

            Archer(i + 1, count + 1);
            board[n][i] = 0;
        }
    }

    static void Simulation() {                                  // 적을 쏘고 움직이는 모든 경우를 확인
        t_enemy.clear();                                        // 적의 초기 위치는 저장되어있어야 하기 때문에
        for(int i = 0; i < enemy.size(); i++) {                 // t_enemy 배열을 새로 생성
            t_enemy.add(enemy.get(i));
        }

        kill_count = 0;                                         // 죽인 수, 남은 적의 개수, 죽인 적을 저장하는 변수를 초기화
        enemy_count = enemy.size();
        kill_visited = new boolean[enemy_count];

        while(enemy_count > 0) {                                // 적이 없을 때까지 쏘고 움직이는 함수를 반복 호출
            Shoot();
            Move();
        }

        max_kill = Math.max(max_kill, kill_count);              // 죽인 수가 최대 값보다 크다면 저장
    }

    static void Shoot() {                                       // 궁수가 적을 쏘는 함수
        Set<Integer> current_kill = new HashSet<Integer>();     // 현재 턴에 죽인 적을 저장할 변수
                                                                // -> 적을 죽여도 현재 턴 내에서는 쏠 수 있기 때문에 중복 제거 및 저장
        for(int j = 0; j < archer.length; j++) {                // 궁수 마다 쏠 적을 확인
            int archer_x = n;
            int archer_y = archer[j];

            int[] min_info = {-1, Integer.MAX_VALUE};           // 가장 가까운 적을 저장하기 위한 변수

            for(int i = 0; i < t_enemy.size(); i++) {           // 모든 적을 확인하기 위한 반복문
                if(kill_visited[i]) continue;                   // 전 턴에 이미 죽인 적이라면 continue

                int enemy_x = t_enemy.get(i)[0];
                int enemy_y = t_enemy.get(i)[1];

                int distance = Math.abs(enemy_x - archer_x) + Math.abs(enemy_y - archer_y);   // 적과 궁수와의 거리

                if(distance <= d) {                             // 거리가 쏠 수있는 범위라면
                    if(distance < min_info[1]) {                // 저장된 최소 값보다 작다면
                        min_info[0] = i;                        // 적의 인덱스와 거리를 저장
                        min_info[1] = distance;
                    }
                }
            }

            if(min_info[1] < Integer.MAX_VALUE) {               // 최소값이 저장되어 있다면 현재 턴에 죽인 적에 추가
                current_kill.add(min_info[0]);
            }
        }

        for(int index: current_kill) {                          // 현재 턴에 죽인 적을 처리
            kill_visited[index] = true;                         // 죽음 처리
            enemy_count--;                                      // 남은 적 수 -1
            kill_count++;                                       // 죽인 수 + 1
        }
    }
  
    static void Move() {                                        // 적을 움직이는 함수
            for(int i = 0; i < t_enemy.size(); i++) {           // 모든 적을 확인
                if(kill_visited[i]) continue;                   // 이미 죽어있다면 continue

                int next_enemy_x = t_enemy.get(i)[0] + 1;       // 적이 아래로 한칸 이동한 좌표를 생성
                int next_enemy_y = t_enemy.get(i)[1];

                if(next_enemy_x < n) {                          // 적 좌표를 수정
                    int[] next_enemy = {next_enemy_x, next_enemy_y};

                    t_enemy.set(i, next_enemy);
                } else {                                        // 성에 도착했다면
                    kill_visited[i] = true;                     // 적을 죽음 처리하고
                    enemy_count--;                              // 남은 적 수 -1
            }
        }
    }
}
