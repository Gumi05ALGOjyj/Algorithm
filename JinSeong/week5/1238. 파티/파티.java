import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class num1238파티 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M,X;
    static ArrayList<ArrayList<Point>> graph, graph2;
    static int[]dist;
    static int[] dist2;
    static class Point implements Comparable<Point>{
        int end,value;

        public Point(int end, int value) {
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Point p){
            return this.value-p.value;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        graph2 = new ArrayList<>();

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Point(end,value));
            graph2.get(end).add(new Point(start,value));
        }

        dist = dijkstra(graph);
        dist2 = dijkstra(graph2);
        
        int res=0;
        for(int i=1; i<=N; i++){
            res = Math.max(res,dist[i]+dist2[i]);
        }
        System.out.println(res);
    }
    private static int[] dijkstra(ArrayList<ArrayList<Point>> list){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(X,0));

        boolean[] check = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        dist[X]=0;
        while(!pq.isEmpty()){
            Point p = pq.poll();
            int cur = p.end;
            if(!check[cur]){
                //방문하지 않은 곳이면
                check[cur]=true;
                for(Point pp : list.get(cur)){
                    if(!check[pp.end] && dist[pp.end]> dist[cur] + pp.value){
                        dist[pp.end] = dist[cur] + pp.value;
                        pq.add(new Point(pp.end,dist[pp.end]));
                    }
                }
            }
        }

        return dist;
    }
}
