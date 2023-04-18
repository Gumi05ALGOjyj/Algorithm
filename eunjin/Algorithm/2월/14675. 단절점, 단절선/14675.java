import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 단절점과 단절선
 * 2023.02.20
 */
public class B14675 {
	static int N;
	static int q;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> list[];
	static int input1;
	static int input2;
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			list[n1].add(n2);
			list[n2].add(n1);
		}
		
		q = Integer.parseInt(br.readLine());
		for(int i=0;i<q;i++) {
			st = new StringTokenizer(br.readLine());
			input1 = Integer.parseInt(st.nextToken());
			input2 = Integer.parseInt(st.nextToken());
			
			if(input1==2) sb.append("yes\n");
			else {
				int cnt=0;
				//리프이거나 루트이면 단절점이 아니다 -> 이외는 단절점
				for(int tmp : list[input2]) {
					cnt++;
				}
				if(cnt>1) sb.append("yes\n");
				else
					sb.append("no\n");
			}
		}
		
		System.out.println(sb);
	}
}
