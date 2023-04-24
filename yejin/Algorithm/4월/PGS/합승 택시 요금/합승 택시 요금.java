import java.io.*;
import java.util.*;

class Solution {
	class Dot {
		int vertex;
		int cost;

		public Dot(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}
	
	List[] graph; 
	
	boolean[][] abCheck;
	
	int[] minCost;
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		
		graph = new ArrayList[n+1];
		
		for (int i = 0; i < n + 1; i++)
			graph[i] = new ArrayList<Dot>();

		int v1, v2, c;
		for (int i = 0; i < fares.length; i++) {
			v1 = fares[i][0];
			v2 = fares[i][1];
			c = fares[i][2];
			
			graph[v1].add(new Dot(v2, c));
			graph[v2].add(new Dot(v1, c));

		}

		Dijikstra(n, s, a, b);
		
		boolean[][] vertexCheck = new boolean[n+1][2];
		
		int[] minResult = new int[n+1];
		
		for(int i = 1; i<n+1; i++) {
			minResult[i] = minCost[i];
			vertexCheck[i][0] = abCheck[i][0];
			vertexCheck[i][1] = abCheck[i][1];
		}
		
		
		for(int i = 1; i<n+1; i++) {
			
			if(minResult[i] > answer)
				continue;
			
			if(!vertexCheck[i][0] && !vertexCheck[i][1]) { // A와 B 모두 방문하지 않은 경우
				Dijikstra(n, i, a, b);
				minResult[i] += (minCost[a]+minCost[b]);
			}else if(vertexCheck[i][0] && !vertexCheck[i][1]) { // A만 방문한 경우
				Dijikstra(n, i, a, b);
				minResult[i] += (minCost[b]);
			}else if(!vertexCheck[i][0] && vertexCheck[i][1]) { // B만 방문한 경우
				Dijikstra(n, i, a, b);
				minResult[i] += (minCost[a]);
			}	
			
			answer = Math.min(answer, minResult[i]);
		}
		
		return answer;
	}

	void Dijikstra(int n, int s, int a, int b) {
		abCheck = new boolean[n + 1][2]; // 0열에는 A노드를 방문했는지 확인하고 1열에서는 B노드를 방문했는지 확인한다.
		
		minCost = new int[n + 1]; // 최단거리를 저장하는 배열
		
	
		PriorityQueue<Dot> pq = new PriorityQueue<>(new Comparator<Dot>() {
			@Override
			public int compare(Dot o1, Dot o2) { // 거리를 기준으로 오름차순 정렬
				return o1.cost - o2.cost;
			}
		});

		Arrays.fill(minCost, Integer.MAX_VALUE);

		minCost[s] = 0;

		pq.add(new Dot(s, 0));

		Dot dot1, dot2;
		while (!pq.isEmpty()) {
			dot1 = pq.poll();

			if (minCost[dot1.vertex] < dot1.cost)
				continue;

			int pathCost;

			for(int i =0; i<graph[dot1.vertex].size(); i++) {
				dot2 = (Dot) graph[dot1.vertex].get(i);
				
				pathCost = minCost[dot1.vertex] + dot2.cost;

				if (pathCost < minCost[dot2.vertex]) {
					minCost[dot2.vertex] = pathCost;
					
					// 이전에 A 또는 B 노드를 방문했는지 확인한다.
					abCheck[dot2.vertex][0] = abCheck[dot1.vertex][0];
					abCheck[dot2.vertex][1] = abCheck[dot1.vertex][1];
					
					// 이번에 방문하는 노드가 A노드인 경우
					if (dot2.vertex == a)
						abCheck[dot2.vertex][0] = true;
					
					// 이번에 방문하는 노드가 B노드인 경우
					if (dot2.vertex == b)
						abCheck[dot2.vertex][1] = true;
					
					pq.add(new Dot(dot2.vertex, minCost[dot2.vertex]));
				}
			}

		}
	}
}
