import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class 3079_ {
	
	public static int N,M;
	
	public static int[] checkPoint;
	
	public static long high;
	
	public static long result = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		checkPoint = new int[N];
		
		for(int i =0; i<N; i++) {
			checkPoint[i] = Integer.parseInt(br.readLine());
			high = Math.max(high, checkPoint[i]);
		}
		
		Arrays.sort(checkPoint);
		
		binarySearch();
		
		System.out.println(result);
		
	}
	
	static void binarySearch() {
		
		long low = 1L;
		
		high = high * M;
		
		long mid;
		
		while(low <= high) {
			
			mid = (low + high)/2;
			
			long count = minTimeCheck(mid);

			if(count >= M) {
				result = Math.min(result, mid);
				high = mid - 1;
			}else if(count < M){
				low = mid + 1;
			}
				
		}
		
	}
	
	// target 시간에 총 result 명을 보낼 수 있다.
	static long minTimeCheck(long target) {

		long result = 0;
		
		for(int i =0; i<N; i++) {
			result += (target/checkPoint[i]);
			if(result >= M)
				break;
		}
		
		return result;
	}
}
