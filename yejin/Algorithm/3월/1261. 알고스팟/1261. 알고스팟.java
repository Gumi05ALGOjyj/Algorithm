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

class Dot{
	int x;
	int y;
	int w;
	
	public Dot(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}
}

public class Main {
	
	public static int M,N; // 가로 크기 : M, 세로 크기 : N
	
	public static int[][] map;
	
	public static int[][] minPath;
	
	public static boolean[][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	
	public static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		
		map = new int[N][M]; // 0또는 1로 이루어진 미로
		
		minPath = new int[N][M]; // 각 좌표까지의 최단 거리를 저장할 2차원 배열
		
		visited = new boolean[N][M]; // 각 좌표를 방문했는지 확인할 2차원 배열
		
		for(int i =0; i<N; i++) {
			str = br.readLine().split("");
			for(int j =0; j<M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		bfs();
		
		System.out.println(minPath[N-1][M-1]);
		
	}
	
	static void bfs() {
		PriorityQueue<Dot> pq = new PriorityQueue<>(new Comparator<Dot>() {

			@Override
			public int compare(Dot o1, Dot o2) {
				return o1.w - o2.w;
			}
		});
		
		pq.offer(new Dot(0, 0, 0)); // (0,0) 에서 시작하고 벽을 0개 부순 상태에서 시작한다.
		visited[0][0] = true;
		minPath[0][0] = 0;
		
		Dot dot;
		while(!pq.isEmpty()) {
			dot = pq.poll();
			
			if(minPath[dot.x][dot.y] > dot.w)
				continue;
			
			int nx,ny;
			for(int i = 0; i<4; i++) {
				nx = dot.x + dx[i];
				ny = dot.y + dy[i];
				
				if(rangeCheck(nx, ny)) {
					if(map[nx][ny] == 0) { // 빈 방을 만난 경우
						if(!visited[nx][ny]) { // 처음 방문하는 경우
							visited[nx][ny] = true;
							minPath[nx][ny] = minPath[dot.x][dot.y];
							pq.offer(new Dot(nx, ny, minPath[nx][ny]));
						}else { // 방문한 적이 있는 경우(최단 거리만 비교한다)
							if(minPath[dot.x][dot.y] < minPath[nx][ny]) {
								minPath[nx][ny] = minPath[dot.x][dot.y];
								pq.offer(new Dot(nx, ny, minPath[nx][ny]));
							}
						}
					}else { // 벽을 만난 경우
						if(!visited[nx][ny]) { // 처음 방문하는 경우
							visited[nx][ny] = true;
							minPath[nx][ny] = minPath[dot.x][dot.y] + 1;
							pq.offer(new Dot(nx, ny, minPath[nx][ny]));
						}else { // 방문한 적이 있는 경우(최단 거리만 비교한다)
							if(minPath[dot.x][dot.y]+1 < minPath[nx][ny]) {
								minPath[nx][ny] = minPath[dot.x][dot.y]+1;
								pq.offer(new Dot(nx, ny, minPath[nx][ny]));
							}
						}
					}
				}
			}
		}
		
		
	}
	static boolean rangeCheck(int x, int y) {
		if(0<=x && x <N && 0<= y && y <M)
			return true;
		return false;
	}
}
