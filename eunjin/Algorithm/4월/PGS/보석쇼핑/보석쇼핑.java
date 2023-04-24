import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
    
        int minDiff = 100_001;     
        int start =0;
        int end = 0;

        //보석종류 개수를 저장해 둘 것임!!
        Set<String> set = new HashSet<>();
        //현제까지 내가 살려고 작정한 보석이 뭔지 저장해줄거임
        Deque<String> que =  new ArrayDeque<>();
        
        //처음 보석의 개수를 세어 줄것임
        for(int i=0;i<gems.length;i++){
            set.add(gems[i]);
        }
        
        int resultSize = set.size();
        
        //이제 초기화 해줄 것임
        set.clear();
        
        //처음에는 start값과 end값이 동일아프로 그냥 해도 됨
        //이제 end포인트 값을 늘려 가면서 계산해줄 것이다.
        for(int i=0;i<gems.length;i++){
            que.add(gems[i]);
            set.add(gems[i]);
            
            if(set.size==resultSize){
                //개수가 같다? 답이 될 수 있는 후보이다.
                if(end-start < minDiff){
                    //차이가 더 작다는 것은 범위가 더 작다는 뜻이므로 답이 될 가능성이 가장 유력하다는 뜻이다!
                    minDiff = end-start;
                    answer[0] = start+1;
                    answer[1] = end+1;
                }
            }
            
            //개수가 다르면 아직 가야할 길이 멀다!
            //현재 가장 앞에 있는 보석을 빼도 set에 차이가 없다면? start를 한칸 앞으로 당길 수 있다.
           //근디..어케 구현을 해야할지 감이 안잡혀유...
            
        }
        
        
        return answer;
    }
}
