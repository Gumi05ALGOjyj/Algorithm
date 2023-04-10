import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
	Queue<String> queue = new ArrayDeque<String>();

	public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        for(String city : cities) {
        	String reCity = city.toLowerCase();
        	
        	if(queue.contains(reCity))
        		answer += 1;
        	else
        		answer += 5;
        	
        	if(queue.size() < cacheSize)
        		queue.offer(reCity);
        	else {
        		queue.poll();
        		queue.offer(reCity);
        	} 	
        }
        return answer;
    }
}
