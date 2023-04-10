import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m  = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		
		List<Integer> minus = new ArrayList<>();
		List<Integer> plus = new ArrayList<>();
		
	
		while(st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp>0)
				plus.add(tmp);
			else
				minus.add(tmp);
		}
		Collections.sort(minus);
//		Collections.reverse(minus);
		Collections.sort(plus);
		Collections.reverse(plus);
	
		
	
		int walk = 0;

		for(int i = 0 ; i < minus.size() ; i=i+m) {
			walk += Math.abs(minus.get(i))*2;

		}

		for(int i = 0 ; i < plus.size() ; i=i+m) {
			walk += plus.get(i)*2;

		}
		if(minus.size()>0 && plus.size()>0) {
		if(Math.abs(minus.get(0)) > plus.get(0)) {
			walk = walk - Math.abs(minus.get(0));

		}else {
			walk = walk - plus.get(0);

		}
		System.out.println(walk);
		}else {
			if(plus.size()>0)
				System.out.println(walk-plus.get(0));
			else
				System.out.println(walk-Math.abs(minus.get(0)));
		}
	}
}
