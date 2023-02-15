import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
	
	public static int N;
	
	public static int[] parent;
	
	public static List<Integer> findList;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		String[] str;
		
		int v1,v2;
		for(int t = 0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			
			findList = new ArrayList<>();
			
			for(int i =0; i<N-1; i++) {
				str = br.readLine().split(" ");
				
				parent[Integer.parseInt(str[1])] = Integer.parseInt(str[0]);
			}
			
			str = br.readLine().split(" ");
			
			v1 = Integer.parseInt(str[0]);
			
			v2 = Integer.parseInt(str[1]);

			sb.append(findParent(v1,v2)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int findParent(int v1, int v2) {
		int result = 0;
		findList.add(v1);
		while(true) {
			if(parent[v1] == 0) { // v1의 조상이 존재하지 않는 경우 -> 즉, root 노드인 경우
				findList.add(v1);
				break;
			}else {
				findList.add(parent[v1]);
				v1 = parent[v1];
			}
		}
		
		while(true) {
			if(findList.contains(v2)) { // v2의 노드가 v1에서 탐색한 리스트에 존재한다면 해당 노드를 결과로 반환한다.
				result = v2;
				return result;
			}else
				v2 = parent[v2];
		}
	}

}
