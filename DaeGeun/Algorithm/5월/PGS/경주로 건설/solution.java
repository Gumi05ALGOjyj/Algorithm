import java.util.*;

class Solution {
    static int MAPSIZE;
    static int[][][] visited;
    static final int[] dx = {0,0,-1,1};
    static final int[] dy = {-1,1,0,0};
    static final int UP=0,DOWN=1,LEFT=2,RIGHT=3;
    
    public int solution(int[][] board) {
        MAPSIZE = board.length;
        visited = new int[MAPSIZE][MAPSIZE][4];
        for(int i=0;i<MAPSIZE;i++){
            for(int j=0;j<MAPSIZE;j++){
                Arrays.fill(visited[i][j],Integer.MAX_VALUE);
            }
        }
        Arrays.fill(visited[0][0],0);
        Arrays.fill(visited[1][0],100);
        Arrays.fill(visited[0][1],100);
        
        if(board[1][0]!=1){
            dfs(0,1,DOWN,board);
        }
        if(board[0][1]!=1){
            dfs(1,0,RIGHT,board);
        }
        
        int result=Integer.MAX_VALUE;
        for(int i=0;i<4;i++){
            result=Math.min(result,visited[MAPSIZE-1][MAPSIZE-1][i]);
        }
        return result;
    }
    
    private static void dfs(int x, int y, int DIR, int[][] board){
        int nCost;
        int nx,ny;
        for(int i=0;i<4;i++){
            nCost=0;
            nx = x+dx[i];
            ny = y+dy[i];
            if(nx>=0 && nx<MAPSIZE && ny>=0 && ny<MAPSIZE && board[ny][nx]!=1){
                nCost+=100;
                if(DIR!=i) nCost+=500;
                if(visited[ny][nx][i]>visited[y][x][DIR]+nCost){
                    visited[ny][nx][i]=visited[y][x][DIR]+nCost;
                    dfs(nx,ny,i,board);
                }
            }
        }
    }
}
