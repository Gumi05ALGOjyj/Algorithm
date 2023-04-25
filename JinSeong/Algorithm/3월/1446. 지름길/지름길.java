import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class num1446지름길 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,D, distance[];
    static ArrayList<ArrayList<Point>> graph;
    static class Point{
        int end, value;

        public Point(int end, int value) {
            this.end = end;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        //10001까지의 거리 저장배열
        distance = new int[10001];
        graph = new ArrayList<>();

        for(int i=0; i<10001; i++){
            //일단 지름길 없이 단순히 가는 거리 저장
            distance[i]=i;
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int shortcutLen = Integer.parseInt(st.nextToken());
            //지름길 저장
            graph.get(start).add(new Point(end,shortcutLen));

        }
        dijkstra(0);
        System.out.println(distance[D]);
    }
    private static void dijkstra(int start){
        if(start>D){
            //도착지보다 시작지가 더 넘어서면
            return;
        }
        if(distance[start+1]>distance[start]+1){
            //0~시작점+1 까지의 거리가
            //0~시작점 까지의 거리보다 크면
            //0~시작점+1까지의 거리를 0~ 시작점까지의 거리 +1로 갱신
            distance[start+1] = distance[start]+1;
        }
        for(int i=0; i<graph.get(start).size(); i++){
            //지름길 검사
            //같은 시작점 지름길이 여러개일 수도 있으니 for문
            if(distance[start]+graph.get(start).get(i).value
                    < distance[graph.get(start).get(i).end]){
                //시작 점까지의 거리 + 시작점에서 지름길 탄 거 거리가
                // 지름길 탄 후까지의 거리보다 크면
                // 거리 갱신
                distance[graph.get(start).get(i).end]
                        = distance[start]+graph.get(start).get(i).value;
            }
        }
        dijkstra(start+1);
    }
}
