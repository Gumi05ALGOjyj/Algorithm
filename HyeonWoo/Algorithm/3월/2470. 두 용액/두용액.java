import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] solutions = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			solutions[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(solutions);
		
		int left = 0;
		int right = n - 1;
		int[] result = {Integer.MAX_VALUE, 0, n - 1};
		
		while(left < right) {
			int answer = solutions[right] + solutions[left];
			
			if(result[0] > Math.abs(0 - answer)) {
				result[0] = Math.abs(0 - answer);
				result[1] = solutions[left];
				result[2] = solutions[right];
				
				if(result[0] == 0) break;
			}
			
			if(answer > 0) right--;
			else left++;
		}
		
		System.out.print(result[1] + " " + result[2]);
	}
}
