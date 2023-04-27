import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class Solution {
  // 정확성 모두 통과, 효율성 0
	static int size = Integer.MAX_VALUE;
	static int x = 0 , y = 0;
    public int[] solution(String[] gems) {    	
    	Set<String> gemlist = gemlists(gems); // 입력받은 보석 리스트 
    	int glSize = gemlist.size();
    	
    	for(int i = 0 ; i < gems.length; i++) {
    		List<String> buy = new ArrayList<>();
    		gemlist = gemlists(gems);
    		for(int j = i ; j < gems.length ; j++) {
    			buy.add(gems[j]);
    			gemlist.remove(gems[j]);
    			if(gemlist.size()==0) {
    				if(size > j-i) {
    					size = j-i;
    					x = i+1;
    					y = j+1;
    				}
    				break;
    			}
    			
    		}
    	}
    	
    	
        int[] answer = {x, y};
        return answer;
    }
    
    static Set<String> gemlists(String[] gems){
    	// gemlist에 보석의 종류 저장
    	Set<String> gemlist = new HashSet<>();
    	for(String tmp : gems) {
    		gemlist.add(tmp);
    	}
    	return gemlist;
    }
}
