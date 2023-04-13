import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int SIZE = lock.length + (key.length-1)*2;
        int[][] bigLock = new int[SIZE][SIZE];
         for (int i = 0; i < SIZE; i++) {
            Arrays.fill(bigLock[i],-1);
        }
          for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                bigLock[key.length-1+i][key.length-1+j] = lock[i][j];
            }
        }

        boolean answer = false;
        for(int r=0;r<4;r++){
            if(answer) break;
            for(int i=0;i<bigLock.length-(key.length)+1;i++){
                if(answer) break;
                for(int j=0;j<bigLock.length-(key.length);j++){
                    if(!compare(key, bigLock, i,j)) continue;
                    answer =true;
                    break;
                }
                
            }
            key = rotationKey(key);
        }
        
        
        return answer;
    }
    
    public  boolean compare(int [][] key, int [][] bigLock,int startX, int startY){
        int purpose = lockCount(bigLock);
        int hitCount=0;
        
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                if(key[i][j]==1 && bigLock[startX+i][startY+j]==1) return false;
                if(key[i][j]==1 && bigLock[startX+i][startY+j]== 0) hitCount++;
            }
        }

        if(hitCount < purpose ) return false;
        return true;
    }
    
    
   public  int lockCount(int [][] lock){
        int result=0;
        for(int i=0;i<lock.length;i++){
            for(int j=0;j<lock.length;j++){
                if(lock[i][j]==0) result++;
            }
        }

        return result;
    }
    
   public int[][] rotationKey(int[][] key){
        int[][] newKey = new int[key.length][key.length];

        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                newKey[i][j] = key[key.length-1-j][i];
            }
        }
        return newKey;
    }
}
