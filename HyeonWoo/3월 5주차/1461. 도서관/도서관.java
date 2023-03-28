import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> leftLibrary = new ArrayList<>();
		ArrayList<Integer> rightLibrary = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int posi = Integer.parseInt(st.nextToken());
			
			if(posi < 0) leftLibrary.add(-posi);
			else rightLibrary.add(posi);
		}
		
		Collections.sort(leftLibrary);
		Collections.sort(rightLibrary);
		
		int lCursor = leftLibrary.size() - 1;
		int rCursor = rightLibrary.size() - 1;
		
		int result = 0;
		
		if((lCursor >= 0 ? leftLibrary.get(lCursor) : -1) > (rCursor >= 0 ? rightLibrary.get(rCursor) : -1)) {
			result += leftLibrary.get(lCursor);
			lCursor -= m;
		} else {
			result += rightLibrary.get(rCursor);
			rCursor -= m;
		}
		
		while(lCursor >= 0) {
			result += leftLibrary.get(lCursor) * 2;
			lCursor -= m;
		}
		while(rCursor >= 0) {
			result += rightLibrary.get(rCursor) * 2;
			rCursor -= m;
		}
		
		System.out.print(result);
	}
}
