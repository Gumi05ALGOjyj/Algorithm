import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class 2110_공유기 {
	
	public static int N,C;
	
	public static int[] number;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		String[] str =  br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		
		C = Integer.parseInt(str[1]);
		
		number = new int[N];
		
		for(int i =0; i<N; i++)
			number[i] = Integer.parseInt(br.readLine());

		Arrays.sort(number);
		
		System.out.println(binarySearch());
		
	}
	
	static int binarySearch() {
		
		int low = 1;
		
		int high = number[N-1] - number[0] + 1;
		
		int mid;
		while(low < high) {
			
			mid = (low+high)/2;
			
			if(maxDistance(mid) < C) {
				high = mid;
			}else {
				low = mid + 1;
			}
			
		}
		
		
		return low - 1;
	}
	
	static int maxDistance(int target) {
		int before = number[0];
		int count = 1;
		for(int i = 1; i<N; i++) {
			int value = number[i];
			
			if(value - before >= target) {
				before = value;
				count++;
			}
		}
		return count;
	}
}
