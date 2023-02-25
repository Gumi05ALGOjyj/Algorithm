import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class 17471_게리맨더링 {
	
	public static int N;
	
	public static int[] population; // 각 vertex의 인구를 저장할 배열
	
	public static List<Integer>[] graph; 
	
	public static int target;
	
	public static int locationTotal; // 전체 인구의 수
	
	public static boolean[] visited;
	
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N+1];
		visited = new boolean[N+1];
		
		graph = new List[N+1];
		
		for(int i =0; i<N+1; i++)
			graph[i] = new ArrayList<Integer>();
		
		String[] str = br.readLine().split(" ");
		
		for(int i =0; i<N; i++) {
			population[i+1] = Integer.parseInt(str[i]);
			locationTotal += population[i+1];
		}

		for(int i = 1; i<N+1; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<Integer.parseInt(str[0]); j++) {
				graph[i].add(Integer.parseInt(str[j+1])); 
			}
		}
		
	
		// 1 ~ N-1 개의 부분수열의 합을 만든다.
		for(int i = 1; i<N; i++) {
			Arrays.fill(visited, false);
			target = i;
			Combination(0, 1, 0, new ArrayList<Integer>());

		}
		
		System.out.println(result == Integer.MAX_VALUE? -1 : result);
	

	}
	
	// 조합
	static void Combination(int count, int start, int sum, ArrayList<Integer> tempList) {
		if(count == target) {
		
			if(connectedCheck(tempList)) { // tempList의 vertex가 연결그래프라면?
				
				int value = else_connectedCheck(); // tempList의 vertex들을 제외한 나머지 vertex들이 연결되었는지 확인한다. 반환하는 값은 남은 vertex에서 연결된 vertex의 수

				if(value + tempList.size() == N) { // tempList의 vertex들이 연결되었고 tempList의 vertex를 제외한 나머지 vertex들이 연결된 경우는 전체 vertex를 모두 체크한 경우이다.
					result = Math.min(result, Math.abs(sum - Math.abs((locationTotal-sum))));
				}
				
			}

			return;
		}
		
		for(int i = start; i<N+1; i++) {

			 if(!visited[i]) {
				 
				 visited[i] = true;
				 
				 tempList.add(i);
				 
				 Combination(count+1, i, sum + population[i], tempList);
				 
				 tempList.remove(tempList.size()-1);
				 
				 visited[i] = false;
			 }
		}
		
	}
	
	static boolean connectedCheck(ArrayList<Integer> tempList) {
		Queue<Integer> queue = new LinkedList<>();
		
		boolean[] isSelected = new boolean[N+1];
		
		queue.add(tempList.get(0));
		
		isSelected[tempList.get(0)] = true;

		int cnt = 0;
		while(!queue.isEmpty()) {
			
			int num = queue.poll();
			
			cnt++;
			
			for(int value : graph[num]) {
				if(!isSelected[value] && visited[value]) { // visited[value] 의 True인 부분인 경우를 확인한다.
					isSelected[value] = true;
					queue.offer(value);
				}
			}
		}

		if(cnt == tempList.size()) { // 연결된 그래프인 경우
			return true;
		}
		return false;
	}
	
	// tempList를 제외한 나머지 vertex들이 연결되었는지 확인한다.
	static int else_connectedCheck() {
		boolean[] tempVisited = Arrays.copyOf(visited, N+1); // 깊은 복사
		
		int count = 0;
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i<N+1; i++) {
			if(!visited[i]) {
				queue.offer(i);
				
				tempVisited[i] = true;
				
				break;
			}
		}
		
		int num;
		while(!queue.isEmpty()) {
			
			num = queue.poll();
			
			count++;
			
			for(int value : graph[num]) {
				if(!tempVisited[value]) {
					tempVisited[value] = true;
					queue.offer(value);
				}
			}
		}
	
		return count;
	}
	
	
	
}
