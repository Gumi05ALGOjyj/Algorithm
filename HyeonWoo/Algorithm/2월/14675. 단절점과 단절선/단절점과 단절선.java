package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_14675 {
    static int n;
    static int q;
    static int[] parents;
    static int[] children;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];                                       // 부모 방향, 자식 방향으로 연결된 간선의 개수를 확인하기 위한 배열
        children = new int[n + 1];                                      // 주의해야 할 점은 실제로 해당 트리의 방향을 알 수 없기 때문에 임의로 부모, 자식이라고 칭함
      
        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            parents[a] += 1;                                            // 해당 인덱스에 부모와 자식의 개수만큼 +1
            children[b] += 1;
        }

        st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(t == 2) sb.append("yes");                                // q가 2인 경우(단절선 검사) 순환이 없는 tree 특성 상 무조건 단절선
            else {                                                      // q가 1인 경우(단절점 검사)
                if(parents[k] > 0 && children[k] > 0) sb.append("yes"); // 부모와 자식 노드 둘다 1개 이상 있다면 단절점
                if(parents[k] == 0 && children[k] > 0) {                // 부모 노드가 없을 경우
                    if(children[k] > 1) sb.append("yes");               // 자식 노드가 2개 이상 있다면 단절점
                    else sb.append("no");                               // 자식 노드가 1개 있다면 단절점이 아님
                }
                if(parents[k] > 0 && children[k] == 0) {                // 자식 노드가 없을 경우
                    if (parents[k] > 1) sb.append("yes");               // 부모 노드가 2개 이상 있다면 단절점
                    else sb.append("no");                               // 부모 노드가 1개 있다면 단절점이 아님
                }
            }

            if(i < q - 1) sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
