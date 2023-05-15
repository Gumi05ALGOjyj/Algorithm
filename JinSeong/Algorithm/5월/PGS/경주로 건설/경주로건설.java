import java.util.*;

class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    //board 길이
    int len;
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        len = board.length;
        //방문 배열 x,y,4방향
        int[][][] visited = new int[len][len][4];
        
        //max값으로 초기화
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        
        
        Queue<int []> q = new ArrayDeque<>();
        //이게 0,0에서 오른쪽으로 갈지 아래로 갈지 모르기 때문에
        //나중에 보면 이전 방향이랑 다른 방향으로 간다면 코너로 돈다는 판정을 하기 때문에
        //첨부터 걍 방향 없는값(-1) 주고, 
        //시작할때 오른쪽으로 가든 아래로 가든 코너돌아서 간다고 생각해버리기
        q.offer(new int[] {0,0,-500,-1});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            int prevD = cur[3];
            
            //끝점
            if(x == len-1 && y == len-1){
                answer = Integer.min(answer, cost);
                continue;
            }
            
            for(int d=0; d<4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(isInArea(nx,ny)){
                    //앞으로 갈 곳이 범위 안에 있어야 함
                    if(board[nx][ny]==1){
                        //벽이면 continue
                        continue;
                    }
                    if(d==prevD && visited[nx][ny][d] > cost + 100){
                        //이전 방향과 같다면 쭉 간다는거기 때문에 cost+100
                        //근데 앞으로 갈 곳이 이전 cost+ 100 보다 커야 새롭게 가면서 갱신
                        visited[nx][ny][d] = cost+100;
                        q.offer(new int[] {nx,ny,cost+100,d});
                    }
                    
                    else if(d!=prevD && visited[nx][ny][d] > cost+600){
                        //이전 방향과 다르다면 코너를 돈다는건데 코너돌고 또 나아가기 때문에 cost + 500 + 100
                        //갱신하는 방식은 위와 매한가지
                        visited[nx][ny][d] = cost+600;
                        q.offer(new int[] {nx, ny, cost+600, d});
                    }
                }
                
            }
        }
        
        return answer;
    }
    
    private boolean isInArea(int x, int y){
        //범위안에 있는지 검사
        return 0<=x && x<len && 0<=y && y<len;
    }
}
