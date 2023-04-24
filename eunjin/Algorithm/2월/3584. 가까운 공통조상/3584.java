
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B3584 {
	static ArrayList<Integer>[] lists;
	static int T;
	static int N;
	static int parent;
	static int child;
	static List<Integer> result1 = new ArrayList<>();
	static List<Integer> result2 = new ArrayList<>();
	static int num1, num2;
	static int result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			
			N = Integer.parseInt(br.readLine());
			lists = new ArrayList[N+1];
			
			
			for(int i=0;i<N+1;i++) {
				lists[i] = new ArrayList<>();
			}
			
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				parent = Integer.parseInt(st.nextToken());
				child = Integer.parseInt(st.nextToken());
				lists[child].add(parent);
				//System.out.println(i);
			}
			
			//System.out.println("입력받기 종료");

			st = new StringTokenizer(br.readLine());
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			
			result1.clear();
			result1.add(num1);
			findParent(num1, result1);
			
			//System.out.println(result1.toString());
			
			result2.clear();
			result2.add(num2);
			findParent(num2, result2);
			//System.out.println(result2.toString());
			
			
			for(int i : result1) {
				for(int j:result2) {
					if(i==j) {
						result = i;
						break;
					}
				}
				if(result==i) break;
			}
			
			sb.append(result+"\n");
		}
		System.out.println(sb);
	}

	public static void findParent(int num, List<Integer> list) {
		if (lists[num].isEmpty())
			return;

		int p = lists[num].get(0);
		list.add(p);
		findParent(p, list);
	}
}
