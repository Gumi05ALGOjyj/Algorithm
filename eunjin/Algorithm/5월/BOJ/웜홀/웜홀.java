import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    //static int dist[];
    static class Point {
        int v, adj;

        public Point(int v, int adj) {
            this.v = v;
            this.adj = adj;
        }
    }

    static int T;
    static int N;   //지점의 개수
    static int M;   //도로의 개수
    static int W;   //웜홀의 개수
    static ArrayList<Point>[] list;   //간선 정보를 저장해줄것이다!
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++) {  //테이트케이스만큼 반복해준다.
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            list = new ArrayList[N + 1];  //0번 인덱스는 사용하지 않을 것이다.
            for (int i = 0; i < N + 1; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int adj = Integer.parseInt(st.nextToken());

                list[v1].add(new Point(v2, adj));
                list[v2].add(new Point(v1, adj));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int adj = Integer.parseInt(st.nextToken());

                list[from].add(new Point(to, -adj));
            }

            //출발했던 지점으로 다시 돌아와야 한다!! 음..뭘꺼.....ㅋ
            //dist배열을 만들고 거기서 dp돌려서 다시 돌아왔을떄? 그 거리값이 음수인지 확인해본다...?

            sb.append(bellmanFord() ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }

    //음수를 가진 간선이 존재한다? -> 밸만폳,
    static boolean bellmanFord() {
        boolean flag = false;

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 1; i <= N; i++) {
            flag=false;
            for (int j = 1; j < N + 1; j++) {
                for (Point point : list[j]) {
                    if (dist[j] == INF) continue;
                    if (dist[point.v] <= dist[j] + point.adj) continue;
                    dist[point.v] = dist[j] + point.adj;
                    flag = true;
                }
            }

           if(!flag) break;
        }

        return flag;

    }


}
