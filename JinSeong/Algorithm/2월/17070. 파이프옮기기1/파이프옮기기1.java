import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] arr;
    static int result;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        result=0;
        dfs(0,1,0);

        System.out.println(result);

    }
    private static void dfs(int x, int y, int direction){
        if(x==N-1 && y==N-1){
            result++;
            return;
        }
        switch(direction){
            case 0://가로
                if(y+1<N && arr[x][y+1]==0){
                    dfs(x,y+1,0);
                }
                break;
            case 1: //세로
                if(x+1<N && arr[x+1][y]==0){
                    dfs(x+1,y,1);
                }
                break;
            case 2: //대각선
                if(y+1<N && arr[x][y+1]==0){
                    dfs(x,y+1,0);
                }
                if(x+1<N && arr[x+1][y]==0){
                    dfs(x+1,y,1);
                }
                break;
        }
        if(y+1<N && x+1<N && arr[x+1][y+1]==0 && arr[x][y+1]==0 && arr[x+1][y]==0){
            dfs(x+1,y+1,2);
        }
    }

}
