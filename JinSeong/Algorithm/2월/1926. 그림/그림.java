import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class num1926그림 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n,m;
    static int[][] arr;
    static boolean[][] visited;
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static int result1 =0;
    static int result2=0;
    static int tempresult2=0;
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && arr[i][j]==1){
                    tempresult2 =1;
                    result1++;
//                    bfs(i,j);
                    dfs(i,j);
                    result2 = result2<tempresult2 ? tempresult2 : result2;
                }
            }
        }
        System.out.println(result1);
        System.out.println(result2);

    }
    private static void bfs(int x, int y){
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Point temp = q.poll();
            for(int k=0; k<4; k++){
                int xx = temp.x + dx[k];
                int yy = temp.y + dy[k];

                if(xx>=0 && yy>=0 && yy<m && xx<n && !visited[xx][yy] && arr[xx][yy]==1){
                    tempresult2++;
                    visited[xx][yy] = true;
                    q.add(new Point(xx,yy));
                }
            }
        }
    }
    private static void dfs(int x, int y){
        visited[x][y]= true;
        for(int k=0; k<4; k++){
            int xx = x + dx[k];
            int yy = y + dy[k];
            if(xx>=0 && yy>=0 && yy<m && xx<n && !visited[xx][yy] && arr[xx][yy]==1){
                tempresult2++;
                dfs(xx,yy);
            }
        }
    }
}
