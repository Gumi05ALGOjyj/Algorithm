import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        int keyLen = key.length;
        int lockLen = lock.length;
        
        int mapLen = keyLen*2+lockLen-2;
        int[][] map = new int[mapLen][mapLen];
        
        for(int i=keyLen-1; i<keyLen+lockLen-1; i++){
            for(int j=keyLen-1; j<keyLen+lockLen-1; j++){
                map[i][j] = lock[i-(keyLen-1)][j-(keyLen-1)];
            }
        }
        
        //4방향 90도씩 로테이트해가면서 검사
        for(int i=0; i<4; i++){
            if(check(map,key,lockLen)){
                return true;
            }
            rotate(key);
        }
        
        
        return false;
    }
    public boolean check(int[][] map, int[][] key, int lockLen){
        int keyLen = key.length;
        int mapLen = map.length;
        for(int i=0; i<mapLen-keyLen+1; i++){
            for(int j=0; j<mapLen-keyLen+1; j++){
                
                for(int k=0; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        map[i+k][j+l] +=key[k][l];
                    }
                }
                
                boolean flag = true;
                //lock이 다 1됐으면 true 아니면 다시 for문
                for(int k=keyLen-1; k<keyLen+lockLen-1; k++){
                    for(int l = keyLen-1; l<keyLen+lockLen-1; l++){
                        if(map[k][l]!=1){
                            flag=false;
                            break;
                        }
                    }
                    if(!flag) break;
                }
                
                if(flag) return true;
                
                //map 원상 복귀
                for(int k=0; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        map[i+k][j+l] -= key[k][l];
                    }
                }
            }
        }
        return false;
    }
    
    public void rotate(int[][] key){
        int keyLen = key.length;
        int[][] temp = new int[keyLen][keyLen];
        
        for(int i=0; i<keyLen; i++){
            for(int j=0; j<keyLen; j++){
                temp[i][j] = key[keyLen-j-1][i];
            }
        }
        
        for(int i=0; i<keyLen; i++){
            for(int j=0; j<keyLen; j++){
                key[i][j] = temp[i][j];
            }
        }
    }
}
