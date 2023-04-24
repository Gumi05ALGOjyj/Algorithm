// [1차] 뉴스 클러스터링

import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        
        // str1과 str2를 대문자로 바꿔주기
        str1 = str1.toUpperCase();
        
        str2 = str2.toUpperCase();
        
        int totalLen = 0, sameLen = 0; // 합집합의 수, 교집합의 수
        
        HashMap<String, Integer> map = new HashMap<>();
        
        String str;
        for(int i = 0 ; i<str1.length() - 1; i++){
            if('A' <= str1.charAt(i) && str1.charAt(i) <= 'Z'
              && 'A' <= str1.charAt(i+1) && str1.charAt(i+1) <= 'Z'){
                
                str = str1.substring(i, i+2);
                
                map.put(str, map.getOrDefault(str, 0) + 1);
                
                totalLen++;
            }
        }
        
         for(int i = 0 ; i<str2.length() - 1; i++){
            if('A' <= str2.charAt(i) && str2.charAt(i) <= 'Z'
              && 'A' <= str2.charAt(i+1) && str2.charAt(i+1) <= 'Z'){
               
                str = str2.substring(i, i+2);
        
                if(map.containsKey(str)){
                    map.put(str, map.get(str)-1);
                    
                    if(map.get(str) == 0)
                        map.remove(str);
                    
                    sameLen++;
                }
                totalLen++;
            }
        }
        if(totalLen == 0)
            return 65536;
        else{
            totalLen -= sameLen; // 전체 문자열의 수에서 교집합의 수를 빼면 합집합의 수가 나온다.   
            return (int)Math.floor((sameLen*1.0 / totalLen) * 65536);
        }

    }
}
