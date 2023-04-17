import java.util.*;
class Solution {
    static final int X=0, Y=1;
    static ArrayList<int[]> dolgyList = new ArrayList<>();
    static int N,M;
    static int homeCount;
    public boolean solution(int[][] key, int[][] lock) {
        homeCount=0;
        N = lock.length;
        M = key.length;
        boolean answer = false;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(lock[i][j]==0) homeCount++;
            }
        }
        if(homeCount==0) return true;
        
        for(int i=0; i<4;i++){
            dolgyList.clear();
            for(int y=0;y<M;y++){
                for(int x=0;x<M;x++){
                    if(key[y][x]==1) dolgyList.add(new int[]{x,y});
                }
            }
            answer = moveAndMatch(lock);
            if(answer==true) break;
            key = rotate(key);
        }
        return answer;
    }
    
    static boolean moveAndMatch(int[][] lock){
        int[] dolgyPosition;
        int fillCount=0;
        //이동시켜가며 매칭. 제일 좌상단으로 이동시켜놓은 것 부터 비교.
        for(int presetY=1-M;presetY<N;presetY++){
            for(int presetX=1-M;presetX<N;presetX++){
                //좌측(상단)으로 이동하는 최댓값은 key의 사이즈-1이고
                //우측(하단)으로 이동하는 최댓값은 lock의 사이즈-1이다.
                for(int i=0;i<dolgyList.size();i++){
                    dolgyPosition = dolgyList.get(i);
                    int ny = dolgyPosition[Y]+presetY;
                    int nx = dolgyPosition[X]+presetX;
                    if(ny>=0 && ny<N && nx>=0 && nx<N){
                        if(lock[ny][nx]==0) fillCount++;
                        else break;//돌기끼리 만날 경우 break (preset 옮긴 후 다시 진행)
                    }
                }
                if(fillCount==homeCount) return true;
            }
        }
        return false;
    }
    
    static int[][] rotate(int[][] key){
        int[][] rotatedKey = new int[M][M];
            for(int y=0;y<M;y++){
                for(int x=0;x<M;x++){
                    rotatedKey[y][x] = key[M-1-x][y];
                }
            }
        return rotatedKey;
    }
}
