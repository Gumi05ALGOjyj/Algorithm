import java.util.*;
class Solution {
    public int solution(String str1, String str2) {

        String st1 = str1.toUpperCase();
        String st2 = str2.toUpperCase();
        
        int[][] arr = new int[27][27];
        int[][] arr2 = new int[27][27];
        
        for(int i =0; i<st1.length()-1;i++){
            if('A'<=st1.charAt(i) && st1.charAt(i)<='Z' && 'A'<=st1.charAt(i+1) && st1.charAt(i+1)<='Z'){
                arr[st1.charAt(i)-65][st1.charAt(i+1)-65]++;
            }
        }
        
        for(int i=0; i<st2.length()-1; i++){
            if('A'<=st2.charAt(i) && st2.charAt(i)<='Z' && 'A'<=st2.charAt(i+1) && st2.charAt(i+1)<='Z'){
                arr2[st2.charAt(i)-65][st2.charAt(i+1)-65]++;
            }
        }
        
        int union = 0;
        int inter = 0;
        for(int i=0; i<27; i++){
            for(int j=0; j<27; j++){
                union+=Math.max(arr[i][j], arr2[i][j]);
                inter+= Math.min(arr[i][j], arr2[i][j]);
            }
        }
        if(union==0){
            return 65536;
        }
        return (int)(65536.0*inter/union);
        
        
    }
    
    
}
