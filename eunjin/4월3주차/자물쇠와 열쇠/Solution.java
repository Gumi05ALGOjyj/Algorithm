class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        for(int r=0;r<4;r++){
            if(answer) break;
            for(int i=0;i<lock.length;i++){
                if(answer) break;
                for(int j=0;j<lock.length;j++){
                    if(!compare(key,lock,i,j)) continue;
                    answer = true;
                    //break;
                }
            }
            key = rotationKey(key);
        }
        
        
        return answer;
    }
    
    public  boolean compare(int [][] key, int[][] lock, int x, int y){
        int purpose = lockCount(lock);
        int hitCount=0;
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                int curX = x+i;
                int curY = y+j;
                if(curX>=key.length || curY>=key.length) continue;

                if(key[i][j]==1 && lock[curX][curY]==0) hitCount++;
                if(key[i][j]==1 && lock[curX][curY]==1) return false;



            }
        }

        if(hitCount== purpose ) return true;
        return false;
    }
    
    
   public  int lockCount(int [][] lock){
        int result=0;
        for(int i=0;i<lock.length;i++){
            for(int j=0;j<lock[0].length;j++){
                if(lock[i][j]==0) result++;
            }
        }

        return result;
    }
    
   public int[][] rotationKey(int[][] key){
        int[][] newKey = new int[key.length][key.length];

        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                newKey[i][j] = key[j][key.length-i-1];
            }
        }
        return newKey;
    }
}
