import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class num1261알고스팟 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int min;
    static class Point implements Comparable<Point>{
        int x,y,drill;

        public Point(int x, int y, int drill) {
            this.x = x;
            this.y = y;
            this.drill = drill;
        }

        @Override
        public int compareTo(Point p){
            return this.drill-p.drill;
        }
    }
    public static void main(String[] args) throws IOException {
        //swea 보급로 문제 참고하면 좋을듯!
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        min = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = s.charAt(j)- '0';
            }
        }

        bfs();

        System.out.println(min);

    }

    private static void bfs(){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0,0,0));
        boolean[][] visit = new boolean[N][M];

        visit[0][0] = true;
        while(!pq.isEmpty()){
            Point p = pq.poll();
            if(p.x== N-1 && p.y ==M-1){
                //끝점에 도착했을 때
                min = p.drill;
                return;
            }

            for(int i=0; i<4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(isInArea(nx,ny)){
                    if(!visit[nx][ny]){
                        if(map[nx][ny]==0){
                            //벽이 아닌 경우
                            visit[nx][ny] = true;
                            pq.add(new Point(nx,ny,p.drill));
                        }
                        else if(map[nx][ny]==1){
                            //벽
                            visit[nx][ny] = true;
                            pq.add(new Point(nx,ny,p.drill+1));
                        }
                    }
                }
            }
        }
    }

    private static boolean isInArea(int x, int y){
        return 0<=x && x<N && 0<=y && y<M;
    }

}
