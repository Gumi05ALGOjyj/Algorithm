package com.ssafy.solution;

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
	int vertex;
	int weight;
	int edgeName;
	
	public Node(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	public Node(int vertex, int weight, int edgeName) {
		this.vertex = vertex;
		this.weight = weight;
		this.edgeName = edgeName;
	}
}

class Edge{
	int v1;
	int v2;
	
	public Edge(int v1, int v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
}
	


public class Main {
	public static int N,M; // 정점의 수, 간선의 수
	
	public static HashMap<Integer, LinkedList<Node>> graph;
	
	public static HashMap<Integer, Edge> edgeList;
	
	public static List[] tempList;
	
	public static int[] minPath;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		graph = new HashMap<>();
		for(int i =0; i<N+1; i++)
			graph.put(i, new LinkedList<>());
		
		
		edgeList = new HashMap<>();
		int v1, v2, w;
		for(int i =0; i<M; i++) {
			str = br.readLine().split(" ");
			v1 = Integer.parseInt(str[0]);
			v2 = Integer.parseInt(str[1]);
			w = Integer.parseInt(str[2]);
			
			graph.get(v1).add(new Node(v2, w, i+1));
			
			graph.get(v2).add(new Node(v1, w, i+1));
			
			edgeList.put(i+1, new Edge(v1,v2));
		}
		
		minPath = new int[N+1];
		
		tempList = new LinkedList[N+1];
		for(int i =0; i<N+1; i++)
			tempList[i] = new LinkedList<Integer>();

		findNetwork();
		
		boolean[] edgeCheck = new boolean[M+1]; // 각 간선을 방문했는지 확인하는 배열
		
		Edge e;
		int count = 0;
		for(int i =1; i<N+1; i++) {
			 for(int j =0; j<tempList[i].size(); j++) {
					int num = (int)tempList[i].get(j); 
					if(!edgeCheck[num]) { // 방문한 적이 없는 간선인 경우
						count++;
						e = edgeList.get(num);
						sb.append(e.v1+" "+e.v2+"\n");
						edgeCheck[num] = true;
					}
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
		
		
	}
	
	@SuppressWarnings("unchecked")
	static void findNetwork() {
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});
		
		Arrays.fill(minPath, Integer.MAX_VALUE);
		
		minPath[1] = 0;
		
		pq.add(new Node(1, 0));
		
		Node node1;
		while(!pq.isEmpty()) {
			node1 = pq.poll();
			
			
			if(minPath[node1.vertex] < node1.weight)
				continue;

			
			for(Node node2 : graph.get(node1.vertex)) {
			
				if(minPath[node1.vertex]+node2.weight < minPath[node2.vertex]) {
					minPath[node2.vertex] = minPath[node1.vertex]+node2.weight;
					
					tempList[node2.vertex].clear(); // 이전까지 간선을 방문한 리스트를 지워준다.
					
					// 방문한 간선리스트를 새롭게 만들어준다.
					for(int i =0; i<tempList[node1.vertex].size(); i++) {
						int num = (int)tempList[node1.vertex].get(i);
						tempList[node2.vertex].add(num);
					}
					tempList[node2.vertex].add(node2.edgeName);

					pq.offer(new Node(node2.vertex, minPath[node2.vertex])); 

				}
			}
		}
		
	}
}
