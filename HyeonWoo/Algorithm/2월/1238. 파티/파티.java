import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int x;

    static ArrayList<int[]>[] linked;           // 간선의 방향과 가중치를 저장할 3차원 배열
    static ArrayList<int[]>[] linked_reverse;   // 반대 방향의 간선과 가중치를 저장할 3차원 배열
    static int[] total_time;                    // 인원마다 걸린 시간을 저장할 배열
    static boolean[] visited;                   // 방문체크를 관리할 배열
    static PriorityQueue<Node> queue;           // 다익스트라의 우선순위를 관리하기 위한 큐

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        total_time = new int[n + 1];

        linked = new ArrayList[n + 1];
        linked_reverse = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) linked[i] = new ArrayList<>();
        for(int i = 0; i <= n; i++) linked_reverse[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            int[] new_info = {b, t};
            int[] new_info_reverse = {a, t};

            linked[a].add(new_info);                                        // 목적지까지 가는 최단거리와 돌아오는 최단거리를 구해야 하기 때문에
            linked_reverse[b].add(new_info_reverse);                        // 정방향과 반대 방향 각각 가중치와 함께 저장
        }

        solution(linked);                                                   // 정방향과 반대방향을 모두 확인
        solution(linked_reverse);

        System.out.println(Arrays.stream(total_time).max().getAsInt());     // 걸린 시간 중 가장 큰 값을 출력
    }

    static void solution(ArrayList<int[]>[] linked) {
        int[] temp_time = new int[n + 1];                                                         // 다익스트라 탐색을 위한 배열
        Arrays.fill(temp_time, Integer.MAX_VALUE);                                                // 최소 값을 알기 위해 최대 값으로 초기화
        temp_time[x] = 0;                                                                         // 출발지(문제에서는 목적지)를 0으로 저장

        visited = new boolean[n + 1];                                                 

        queue = new PriorityQueue<>();
        queue.add(new Node(x, 0));

        while(!queue.isEmpty()) {
            Node nq = queue.poll();                                                               // 저장된 값 중 가장 가중치가 작은 순으로 poll

            int nb = nq.num;

            if (!visited[nb]) {                                                                   // 해당 마을을 방문하지 않았다면
                visited[nb] = true;                                                               // 방문처리

                for(int[] next: linked[nb]) {                                                     // 간선을 저장할 배열을 통해 다음 마을을 탐색
                    if(!visited[next[0]] && temp_time[next[0]] > temp_time[nb] + next[1]) {       // 방문하지 않았고 해당 마을의 걸린 시간이 전 마을의 시간 + 해당 마을 까지 오는 시간보다 작다면
                        temp_time[next[0]] = temp_time[nb] + next[1];                             // 걸린 시간 최신화
                        queue.add(new Node(next[0], temp_time[next[0]]));                         // 큐에 
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            total_time[i] += temp_time[i];
        }
    }

    static class Node implements Comparable<Node> {         // 우선순위 큐의 정렬을 위한 Node
        public int num;
        public int time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            if(this.time > node.time) return 1;
            else if(this.time < node.time) return -1;
            return 0;
        }
    }

}
