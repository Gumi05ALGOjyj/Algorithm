import java.io.*;
import java.util.*;

class Solution {
    
	
	public int solution(int cacheSize, String[] cities) {

        int answer = 0;
        
        if(cacheSize == 0) return cities.length*5;
        
        Deque<String> dq = new ArrayDeque<String>();
        
        String city;
        for(int i = 0; i<cities.length; i++) {
        	city = cities[i].toLowerCase();
        	
        	if(dq.remove(city)) {
        		dq.addLast(city);
        		answer += 1;
        	}else {
        		int size = dq.size();
        		
        		if(size == cacheSize) {
        			dq.removeFirst();
        		}
        		
        		dq.addLast(city);
        		answer += 5;
        	}
        }
        
        
        return answer;
    }
}
