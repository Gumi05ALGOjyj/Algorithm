package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_3584 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        for(int test_case = 0; test_case < t; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] parents = new int[n + 1];                               // 부모 방향으로만 검사하면 되기 때문에 부모를 저장할 배열 생성

            for(int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parents[b] = a;                                           // 자식 인덱스에 부모 노드 저장
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());                 // 시작 노드와 목적지 노드를 임의로 설정
            int goal = Integer.parseInt(st.nextToken());                  // -> 반대로 해도 지장 없음

            boolean[] visited = new boolean[n + 1];                       // 노드 방문을 체크 할 배열 생성

            int current = start;

            while(true) {                                                 // 시작 노드부터 방문을 체크하며 부모 노드가 없을 때까지 탐색
                visited[current] = true;                                  

                if(parents[current] != 0) current = parents[current];
                else break;
            }

            current = goal;
          
            while(true) {                                                 // 목적지 노드부터 올라가며 가장 처음으로 나온 방문 체크된 노드를 확인
                if(!visited[current]) current = parents[current];         // -> 해당 노드가 가장 가까운 공통 조상 
                else {
                    sb.append(Integer.toString(current));
                    break;
                }
            }

            if(test_case < t - 1) sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
