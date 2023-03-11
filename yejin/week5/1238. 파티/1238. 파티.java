import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Node{
	int v;
	int w;
	
	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}
}

public class Main {
	
	public static int N,M,X;
	
	public static int[] totalPath; 
	
	public static int[] minPath;
	
	public static HashMap<Integer,LinkedList<Node>> graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		X = Integer.parseInt(str[2]);
		
		minPath = new int[N+1];
		
		totalPath = new int[N+1];
		
		graph = new HashMap<>();
		for(int i =0; i<N+1; i++)
			graph.put(i, new LinkedList<>());
		
		int v1, v2, w;
		for(int i =0; i<M; i++) {
			str = br.readLine().split(" ");
			v1 = Integer.parseInt(str[0]);
			v2 = Integer.parseInt(str[1]);
			w = Integer.parseInt(str[2]);
			
			graph.get(v1).add(new Node(v2, w));
		}
		
		Arrays.fill(totalPath, Integer.MIN_VALUE);
		totalPath[X] = 0;
		
		// 만약 X가 2라면? 1→2, 3→2, 4→2 의 최소 경로를 구해서 totalPath[i]에 저장한다.
		for(int i =1; i<N+1; i++) {
			if(i != X) {
				Dijikstra(i);
				totalPath[i] = minPath[X];
			}
		}
		
		
		Dijikstra(X); // 만약 X가 2라면? 2→1, 2→3, 2→4 까지의 경로의 최단 경로가 저장되어있다.
		
		// 1→2와 2→1 , 3→2와 2→3, 4→2와 2→4 까지 최소경로에서 가장 큰 값을 출력한다.
		int result = totalPath[1]+minPath[1];
		
		for(int i=2; i<N+1; i++) {
			result = Math.max(result, totalPath[i]+minPath[i]);
		}

		System.out.println(result);
	}
	
	static void Dijikstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			}
		});
		
		Arrays.fill(minPath,Integer.MAX_VALUE);
		minPath[start] = 0;
		
		pq.offer(new Node(start, 0));
		
		Node node1;
		while(!pq.isEmpty()) {
			node1 = pq.poll();
			
			if(minPath[node1.v] < node1.w)
				continue;
			
			for(Node node2 : graph.get(node1.v)) {
				if(minPath[node1.v] + node2.w < minPath[node2.v]) {
					minPath[node2.v] = minPath[node1.v] + node2.w;
					pq.offer(new Node(node2.v, minPath[node2.v]));
				}
			}
		}

		return;
		
	}

}
