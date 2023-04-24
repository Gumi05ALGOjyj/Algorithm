package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_17471 {
    static int n;
    static int[] population;                                        // 구역 별 인구 수를 저장 할 배열
    static ArrayList<int []> linked = new ArrayList<>();            // 구역의 연결을 저장할 리스트
    static ArrayList<Integer> fields;                               // 특정 선거구를 저장할 리스트
    static boolean[] visited;                                       // 방문을 저장할 변수
    static int sumA;                                                // A 선거구 인구를 저장 할 변수
    static int sumB;                                                // B 선거구 인구를 저장 할 변수
    static int min = Integer.MAX_VALUE;                             // 인구수 차이의 최소 값을 저장 할 변수


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        population = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)                                 // 구역 별 인구 수 저장
          population[i] = Integer.parseInt(st.nextToken());

        linked.add(new int[0]);                                     // 구역은 1부터 시작하기 때문에 인덱스 0은 의미 없는 값으로 저장
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int[] link = new int[m];

            for(int j = 0; j < m; j++) 
              link[j] = Integer.parseInt(st.nextToken());           // 해당 구역에 연결된 구역을 저장

            linked.add(link);                                       // 연결된 구역을 해당 구역의 인덱스에 저장
        }

        for(int i = 1; i <= n; i++) {
            fields = new ArrayList<>();                             // 하나의 선거구에 포함된 구역을 저장할 리스트
            fields.add(i);
            solution(fields, i);                                    // 백트래킹 탐색 시작
        }

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);      // 값이 그대로라면 불가능한 경우이므로 -1, 아니라면 최소값을 출력
    }

    static void solution(ArrayList<Integer> fields, int index) {
        if(fields.size() < n) {                                     // solution 함수가 호출될 때마다 해당 선거구가 가능한지 확인
            check(fields);

            if(fields.size() == n - 1) return;                      // 모든 구역을 하나의 선거구가 포함한다면 불가능하기 때문에 종료
        }

        for(int i = index + 1; i <= n; i++) {                       // 백트래킹으로 재귀 호출
            fields.add(i);
            solution(fields, i);
            fields.remove(fields.size() - 1);
        }
    }

    static void check(ArrayList<Integer> fields) {                  // 현재 선거구가 가능한지 확인
        visited = new boolean[n + 1];
        visited[0] = true;                                          // 방문을 확인할 배열 생성 -> 구역이 1부터 시작하기 때문에 0은 방문 처리

        sumA = searchA(fields.get(0), 0);                           // 현재 선택한 구역이 가능한지 확인하며 인구수도 구함

        for(int current: fields) {                                  // 선택한 구역을 확인하며 전부 방문 했는지 확인
            if(!visited[current]) {                                 // 아직 방문하지 않은 구역이 있다면 선거구가 불가능한 경우이므로 종료
                return;
            }
        }

        for(int i = 1; i <= n; i++) {                               
            if(!visited[i]) {                                       // 아직 방문하지 않은 구역이 남은 선거구의 구역이므로 확인
                sumB = searchB(i, 0);                               // 남은 구역이 가능한지 확인하며 인구수도 구함
                break;                                              // 전부 연결됐다면 하나로 전부 방문할 수 있기 때문에 종료
            }
        }

        for(boolean v: visited) {                                   // 하나라도 방문하지 않았다면 선거구가 둘로 나뉠 수 없는 경우이므로 종료
            if(!v) return;
        }

        min = Math.min(min, Math.abs(sumA - sumB));                 // 인구 수 차이의 최소 값을 저장
    }

    static int searchA(int index, int sum) {                        // 현재 선택한 구역이 가능한지 확인하며 인구수도 구함
        visited[index] = true;                                      // 방문 처리
        int nSum = sum + population[index];                         // 인구 수 합을 저장

        for(int next: linked.get(index)) {                          // 연결되어 있는 다음 구역을 확인
            if(!visited[next] && fields.contains(next)) nSum = searchA(next, nSum);   // 방문하지 않았고, 선택한 구역 중에 있다면 재귀 호출
        }

        return nSum;
    }

    static int searchB(int index, int sum) {                        // 남은 구역이 가능한지 확인하며 인구수도 구함
        visited[index] = true;
        int nSum = sum + population[index];

        for(int next: linked.get(index)) {
            if(!visited[next]) nSum = searchB(next, nSum);
        }

        return nSum;
    }
}
