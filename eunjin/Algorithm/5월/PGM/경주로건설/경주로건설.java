import java.util.*;
class Solution {
    static final int INF = (int)2e9;
    //방향 번호 : 상 = 0, 좌=1, 하 = 2, 우 = 3
    
    public int solution(int[][] board) {
        //해당 좌표까지 갈 수 있는 최단거리를 저장해주는 2차원 배열이다.

        
        return bfs(board);

        //return cost[board.length-1][board.length-1];
    }
    
    public int bfs(int[][] board){

        int[] dx = {-1, 0, 1,0};
        int[] dy = {0,-1,0,1};
        int[][][] cost;
        
        cost = new int[board.length][board.length][4];
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++)
                for(int k=0;k<4;k++)
                    cost[i][j][k] = INF;
        }
        
        Queue<Point> q = new LinkedList<>();
        
        //처음 시작점은 0,0이므로 넣어준다.
        // cost[0][0]=0;   //시작점은 아무 비용없이 갈 수있으므로 0으로 설정해준다.
        for(int i=0;i<4;i++)
            cost[0][0][i] = 0;
        q.add(new Point(0,0,-1,0));
        
        //큐가 빌때까지 반복해준다 -> 다 봐준다.
        while(!q.isEmpty()){
            
            Point point = q.poll();
            
            int curX = point.x;
            int curY = point.y;
            int curDir = point.dir;
            int curAdj = point.adj;
            
            if(curDir!=-1){
                cost[curX][curY][curDir] = Math.min(cost[curX][curY][curDir], curAdj);
                if(curAdj>cost[curX][curY][curDir]) continue;
            }
           
            
            //if(curX==board.length-1 && curY == board.length-1) continue;
            
            //4방향 탐색을 하면서 갈수 있는 곳이면 간다!!
            for(int d =0;d<4;d++){
                int nextX = curX + dx[d];
                int nextY = curY + dy[d];
                int addCost=0;
                
                // 큐에 들어갈 수 있는 조건 1. 범위 확인하기 , 2. 벽인지 확인해주기
                if(!inRange(nextX, nextY,board)) continue;

                if(curDir==-1) addCost += 100;  //초기 상태라는 뜻이며, 직진만 한다, 걍 100임
                else if(curDir == d) addCost += 100; //방향이 동일하다는 뜻이며 비용은 100임
                else if(curDir != d) addCost = 600;  //직진도 해야하고 방향도 틀어야함 600
                // //else nextCost += 600;
                
                //if(curDir!= -1 && d!= curDir) addCost+=500;
                
                int nextCost = curAdj + addCost;
                
                // 큐에 들어갈 수 있는 조건2. 현재 cost배열의 값이 나보다 작은가? 작으면 가볼 필요X
                // 한번도 갱신되지 않았다? 그냥 넣어준다.
                if(cost[nextX][nextY][d]>=INF) {
                    q.add(new Point(nextX, nextY, d, nextCost));
                    continue;
                }
                
                //현재와 이전 값과 더한 값이 cost배열에 있는 값보다 큰 값이면 넘어간다.
                // 최대한 depth가 깊어지지 않게 하기위해서 이런 식으로 코드를 작성했음
                if(nextCost > cost[nextX][nextY][d]) continue;
                
                q.add(new Point(nextX, nextY, d, nextCost));
                
                
            }
            
        }
        
        int result = (int)1e9;
        for(int i=0;i<4;i++){
            result = Math.min(cost[board.length-1][board.length-1][i], result);
        }
        
        return result;
    }
    
    public boolean inRange(int x, int y, int[][] board){
        if(x<0 || y<0 || x> board.length-1 || y> board.length-1) return false;
        if(board[x][y]>0) return false;
        return true;
    }
}

class Point{
    int x, y, dir, adj;
    
    Point(int x, int y, int dir, int adj){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.adj = adj;
    }
}
