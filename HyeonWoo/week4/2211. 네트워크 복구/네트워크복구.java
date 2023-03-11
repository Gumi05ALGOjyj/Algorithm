import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<int []>[] linked = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) linked[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[] link1 = {a, c};                                   // 간선은 양방향이기 때문에 반대도 저장해준다.
            int[] link2 = {b, c};

            linked[a].add(link2);
            linked[b].add(link1);
        }

        int[] dlist = new int[n + 1];                               // dlist를 생성하고 Infinity 값으로 채워줌
        Arrays.fill(dlist, Integer.MAX_VALUE);
        dlist[1] = 0;                                               // 시작값은 0으로 초기화

        PriorityQueue<Node> queue = new PriorityQueue<>();          // 우선순위 큐 생성 및 초기 값 삽입
        queue.add(new Node(1, 0));

        int[][] result = new int[n + 1][2];                         // 경로를 저장하기 위한 이차원 배열

        while(!queue.isEmpty()) {
            Node current = queue.poll();
            int num = current.num;
            int time = current.time;

            if(time > dlist[num]) continue;                         // 최신화 할 수 없다면 continue

            for(int[] next: linked[num]) {                          // 연결된 컴퓨터를 순회
                if(dlist[next[0]] > time + next[1]) {               // 더 작은 값으로 최신화 할 수 있다면
                    dlist[next[0]] = time + next[1];                // 최신화 하고

                    int[] newLine = {next[0], num};                 // 최신화 됐다면 경로도 최신화
                    result[next[0]] = newLine;

                    queue.add(new Node(next[0], dlist[next[0]]));   // queue에 삽임
                }
            }
        }

        StringBuilder sb = new StringBuilder(n - 1 + "\n");         // 선의 총 수는 모두 연결된다는 가정 하에 n - 1
        
        for(int i = 2; i <= n; i++) {
            sb.append(result[i][0] + " " + result[i][1] + "\n");
        }

        System.out.print(sb.toString());
    }

    static class Node implements Comparable<Node> {
        public int num;
        public int time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            return this.time - n.time;
        }
    }
}
