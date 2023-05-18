import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//32% / 38%에서 오답! 왜틀렸지!!
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int NONE = 25_000_001;
		
		StringTokenizer st;
		int N,M,W;
		int[][] map;
		TC:
		int T = Integer.parseInt(br.1readLine());
		for(int test_case = 1; test_case <= T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0;i<N;i++) Arrays.fill(map[i], NONE);
			
			{ // 입력처리 - map 초기화
				int from, to, cost;
				//다리 입력
				for(int i=0;i<M;i++) {
					st = new StringTokenizer(br.readLine());
					from = Integer.parseInt(st.nextToken())-1; //번호 to index
					to = Integer.parseInt(st.nextToken())-1; //번호 to index
					cost = Integer.parseInt(st.nextToken());
					
					//다리가 여러 개 있을 수도 있으니 최소값으로 입력
					if(cost<map[from][to]) {
						map[from][to]=cost;
						map[to][from]=cost;
					}
				}
				//웜홀 입력
				for(int i=0;i<W;i++) {
					st = new StringTokenizer(br.readLine());
					from = Integer.parseInt(st.nextToken())-1; //번호 to index
					to = Integer.parseInt(st.nextToken())-1; //번호 to index
					cost = Integer.parseInt(st.nextToken());
					
					map[from][to]= Math.min(map[from][to], cost*-1);
				}
			} //초기화 끝
			
			//플로이드 워셜 1차 진행
			for(int i=0;i<N;i++) {
				for(int from=0;from<N;from++) {
					for(int to=0;to<N;to++) {
						if(i!=from && i!=to && map[from][i]!=NONE && map[i][from]!=NONE) {
							map[from][to] = Math.min(map[from][to], map[from][i] + map[i][to]);
						}
					}
				}
			}
      			//2차진행. 한번이라도 갱신이 일어나면 YES출력            
			for(int i=0;i<N;i++) {
				for(int from=0;from<N;from++) {
					for(int to=0;to<N;to++) {
						if(i!=from && i!=to && map[from][i]!=NONE && map[i][from]!=NONE) {
							if(map[from][to] > map[from][i] + map[i][to]) {
								System.out.println("YES");
								continue TC;
							}
						}
					}
				}
			}
			System.out.println("NO");
		}
	}
}
