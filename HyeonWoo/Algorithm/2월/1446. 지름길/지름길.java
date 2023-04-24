import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[] dlist = new int[d + 1];                       // 다익스트라를 위한 배열
		for(int i = 0; i <= d; i++) dlist[i] = i;           // 초기 값은 순수 거리를 저장
		
		ArrayList<int[]> linked = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			int[] newLink = {s, e, len};
			linked.add(newLink);                              // 간선을 저장
		}
		
		Collections.sort(linked, (a, b) -> {                // 앞에서부터 검사하기 때문에 도착하는 위치 기준으로 정렬
			return a[1] - b[1];
		});
		
		for(int[] link: linked) {                           // 간선을 순회
			int s = link[0];
			int e = link[1];
			int len = link[2];
			
			if(e > d) continue;                               // 목적지보다 도착지점이 멀리있다면 넘어감
			
			int origin = dlist[e];                            // 기존의 값 저장
			dlist[e] = Math.min(dlist[e], dlist[s] + len);    // 지름길을 탔을 때의 거리와 현재 거리 중 작은 값을 저장
			
			if(origin == dlist[e]) continue;                  // 값이 변하지 않았다면 넘어감
			
			int cursor = 1;
			for(int j = e + 1; j <= d; j++) dlist[j] = dlist[e] + cursor++;   // 그 뒤의 값을 1을 더해가며 쭉 수정
		}
		
		System.out.print(dlist[d]);
	}
}
