import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Main {
	public static int TC, N, M, W; // 테스트 케이스의 개수 TC, 지점의 수 N, 도로의 개수 M, 웜홀의 개수 W
	public static int S, E, T; // S와 E는 연결된 지점의 번호, T는 이 도로를 통해 이동하는데 걸리는 시간
	public static StringBuilder sb;
	public static int[] minCost;
	public static List[] edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());

		String[] str;
		while ((TC--) != 0) {
			str = br.readLine().split(" ");

			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			W = Integer.parseInt(str[2]);

			minCost = new int[N + 1];

			edge = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				edge[i] = new ArrayList<int[]>();
			}

			for (int i = 0; i < M + W; i++) {
				str = br.readLine().split(" ");
				S = Integer.parseInt(str[0]);
				E = Integer.parseInt(str[1]);
				T = Integer.parseInt(str[2]);

				if (i < M) { // 도로의 정보가 주어진 경우
					edge[S].add(new int[] { E, T });
					edge[E].add(new int[] { S, T });
				} else { // 웜홀의 정보가 주어진 경우
					edge[S].add(new int[] { E, (-1) * T });
				}
			}

			boolean ischeck = false;
			for (int i = 1; i < N + 1; i++) {
				if (bellmanFord(i)) { // 1~N개의 정점에서 시작하는 모든 경우를 시도해본다.
					ischeck = true;
					sb.append("YES\n");
					break;
				}
			}
			
			if (!ischeck)
				sb.append("NO\n");

		}

		System.out.println(sb.toString());
	}

	static boolean bellmanFord(int start) {
		// 벨만포드 알고리즘
		// 시간복잡도 : O(VE) => 간선은 정점의 제곱과 크기가 같으므로 O(V^3)이라고 볼 수 있다.
		// 벨만포드 알고리즘은 (정점의 개수 - 1)번까지만 최단거리 업데이트 작업을 하기 때문에 (정점의 개수)번까지도 업데이트를 실행하면
		// , 음의 사이클이 발생했다는 것을 알 수 있다.

		Arrays.fill(minCost, Integer.MAX_VALUE);
		minCost[start] = 0; // 시작하는 정점을 0으로 세팅한다.
		boolean update = false;

		for (int k = 1; k < N; k++) { // N-1 번 반복한다.
			update = false;
			
			// 그래프에 있는 모든 간선을 방문한다.
			for (int i = 1; i < N + 1; i++) {
				for (int j = 0; j < edge[i].size(); j++) {
					int[] dot = (int[]) edge[i].get(j);
					int vertex = dot[0];
					int weight = dot[1];
					
					// 최단 거리로 업데이트 작업을 진행한다.
					if (minCost[i] != Integer.MAX_VALUE && minCost[i] + weight < minCost[vertex]) {
						minCost[vertex] = minCost[i] + weight;
						update = true;
					}
				}
			}

			if (!update) // 더 이상 값이 바뀌지 않았다면 계속 진행해도 값이 동일하므로 for문을 나간다. => 음수사이클이 발생하지 않음
				break;
		}

		if (update) { // (정점의 개수 - 1) 번 반복하는 동안 계속 최단거리를 업데이트 한 경우에는 1번더 업데이트를 진행해서 음수사이클이 존재했는지 확인한다.
			for (int i = 1; i < N + 1; i++) {
				for (int j = 0; j < edge[i].size(); j++) {
					int[] dot = (int[]) edge[i].get(j);
					int vertex = dot[0];
					int weight = dot[1];

					if (minCost[i] != Integer.MAX_VALUE && minCost[i] + weight < minCost[vertex]) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

}
