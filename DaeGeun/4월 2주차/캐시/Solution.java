import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
// 	public static void main(String[] args) {
// 		Scanner sc = new Scanner(System.in);
// 		String[] cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};
// 		int result = solution(sc.nextInt(),cities);
// 		System.out.println(result);
// 		sc.close();
// 	}
	
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0) return(5*cities.length);
        
        Queue<String> window = new LinkedList<>();
        
        for(int i=0;i<cities.length;i++) {
        	String item = cities[i].toLowerCase();
    		//cache hit : 순서갱신
    		if(window.contains(item)) {
    			System.out.println("hit");
    			window.remove(item);
    			window.offer(item);
    			answer+=1;
    		} else {
			//cache miss : 가장 나중에 쓴 것 제거
    			System.out.println("miss");
    			if(window.size()==cacheSize) window.poll();
    			window.offer(item);
    			answer+=5;
    		}
    }
        return answer;
    }
}
