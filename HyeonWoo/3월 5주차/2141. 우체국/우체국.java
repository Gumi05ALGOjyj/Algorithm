import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		ArrayList<long[]> town = new ArrayList<>();
		
		int total = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Integer.parseInt(st.nextToken());
			long a = Integer.parseInt(st.nextToken());
			
			total += a;
			
			long[] newTown = {x, a};
			
			town.add(newTown);
		}
		
		Collections.sort(town, (o1, o2) -> {
				if(o1[0] == o2[0]) {
					if(o1[1] - o2[1] > 0) return 1;
					else if(o1[1] - o2[1] < 0) return -1;
					else return 0;
				} else {
					if(o1[0] - o2[0] > 0) return 1;
					else if(o1[0] - o2[0] < 0) return -1;
					else return 0;
				}
			}
		);

		for(int i = 1; i < n; i++) town.get(i)[1] += town.get(i - 1)[1];
		
		int left = 0;
		int right = n - 1;
		
		long min = 1_000_000_001;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			long leftCount = town.get(mid)[1];
			long rightCount = town.get(n - 1)[1] - leftCount;
			
			if(leftCount >= rightCount) {
				right = mid - 1;
				
				min = Math.min(min, town.get(mid)[0]);
			} else {
				left = mid + 1;
			}
		}
		
		System.out.print(min);
	}
}
