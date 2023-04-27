import java.util.*;
import java.io.*;


class Solution {
    
    public HashMap<String, Integer> map; // <보석의 이름, 현재 범위까지 나온 보석의 수>
    
    public HashSet<String> set; // 보석의 종류
    
    public int len; // gems[] 의 크기
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        map = new HashMap<>();
        
        set = new HashSet<>();
        
        len = gems.length;
        
        // 보석을 set에 담아준다.
        for(String gem : gems){
            set.add(gem);
        }
        
        int left = 0, right = 0; // 투 포인터 (left, right)
        
        int distance = Integer.MAX_VALUE;

        while(true){
            
            if(set.size() == map.size()){ // left와 right의 범위 사이에 모든 보석을 1개 이상 뽑은 경우 
                // 왼쪽에 존재하는 보석 1개를 map에서 삭제하고 left pointer를 이동시킨다.
                map.put(gems[left], map.get(gems[left])-1); 
                
                // map에 gems[left] 보석이 존재하지 않는 경우 map에서 삭제한다.
                if(map.get(gems[left]) == 0){
                    map.remove(gems[left]);
                }
                
                left++; // left pointer를 이동시킨다.
            }else if(right == len){
                break;
            }else{ // right pointer를 이동시키면서 보석을 map에 추가함
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            }
            
            // 위에서 보석을 추가했으므로 아래에서 전체 보석을 모두 뽑았을 경우에 거리를 비교한다.
            if(set.size() == map.size()){
                if(right-left < distance){
                    distance = right-left;
                    answer[0] = left+1;
                    answer[1] = right;
                }
            }
        }

        
        return answer;
    }
}
