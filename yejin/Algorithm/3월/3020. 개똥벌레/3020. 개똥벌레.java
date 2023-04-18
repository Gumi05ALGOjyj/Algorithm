import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class 3020_ {
	
	public static int N,H; // 세로 : H, 가로 : N
	
	public static int[] down, up; // 바닥에 있는 장애물 , 위에 있는 장애물
	
	public static int[] downCnt, upCnt;
	
	public static int min = Integer.MAX_VALUE;
	
	public static int[] result1, result2;
	
	public static HashMap<Integer, Integer> total;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]); // 가로
		
		H = Integer.parseInt(str[1]); // 세로
		
		up = new int[N/2];
		down = new int[N/2];
		
		downCnt = new int[500001];
		upCnt = new int[500001];
		
		result1 = new int[H+1]; // 1 ~ H
		result2 = new int[H+1]; // 1 ~ H
		
		
		int idx1 = 0, idx2 = 0;
		int num;
		for(int i =0; i<N; i++) {
			num = Integer.parseInt(br.readLine());
			if(i%2 == 0) {
				down[idx1++] = num;
				downCnt[num]++;
			}else {
				up[idx2++] =  num;
				upCnt[H-num+1]++;
			}
		}
		Arrays.sort(up);
		Arrays.sort(down);
		
		// down 체크
		result1[H] = downCnt[H];
		for(int i = H-1; i>0; i--) {
			result1[i] = result1[i+1] + downCnt[i];
		}
		
		total = new HashMap<>();
		
		// up체크
		result2[1] = upCnt[1];
		
		total.put(result1[1]+result2[1], 1);
		
		min = Math.min(min, result1[1]+result2[1]);
		
		int sum;
		for(int i = 2; i<H+1; i++) {
			result2[i] = result2[i-1] + upCnt[i];
			
			sum = result1[i] + result2[i];
			
			if(total.containsKey(sum))
				total.put(sum, total.get(sum)+1);
			else
				total.put(sum, 1);
			
			min = Math.min(min,sum);
		}
		
		System.out.println(min+" "+total.get(min));

	}
}
