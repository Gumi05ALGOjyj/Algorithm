import java.io.*;
import java.util.*;

// BFS를 사용함

class Solution {
	public class Dot implements Comparator<Dot> { // cost를 기준으로 오름차순
		int x;
		int y;
		int dir;
		int cost;

		public Dot(int x, int y, int dir, int cost) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}

		public int compare(Dot dot1, Dot dot2) {
			return dot1.cost - dot2.cost;
		}
	}

	public static int N, INF, answer;

	public static Queue<Dot> queue;

	public static boolean[][][] visited; // 각 방향으로의 방문을 체크하기 위한 배열

	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, -1, 0, 1 };

	public int solution(int[][] board) {

		N = board.length;

		INF = 25 * 25 * 500; // 가능한 금액의 최대

		visited = new boolean[4][N][N]; // x는 동서남북 방향으로의 방향, (y,z)는 지도에서의 좌표

		answer = bfs(board);

		return answer;
	}

	public int bfs(int[][] board) {
		queue = new ArrayDeque<Dot>();
		queue.add(new Dot(0, 0, -1, 0));

		int minCost = INF;

		Dot dot1;
		while (!queue.isEmpty()) {
			dot1 = queue.poll();

			if (dot1.x == N - 1 && dot1.y == N - 1) {
				minCost = Math.min(minCost, dot1.cost);
				continue;
			}

			int nx, ny;
			for (int i = 0; i < 4; i++) {
				nx = dot1.x + dx[i];
				ny = dot1.y + dy[i];

				// 범위를 벗어난 경우이거나 벽이 있는 경우
				if (!rangeCheck(nx, ny) || board[nx][ny] == 1) {
					continue;
				}

				int tempCost = dot1.cost;
				if (dot1.dir == -1 || dot1.dir == i) { // (0,0)에서 움직이는 경우이거나 같은 방향으로 이동하는 경우
					tempCost += 100;
				} else { // 다른 방향으로 이동하는 경우
					tempCost += 600;
				}

				if ((i + 2) % 4 == dot1.dir) // 이동했던 방향으로 다시 이동하는 경우
					continue;

				// i방향으로 처음 방문하는 경우이거나 크기가 더 작은 경우
				if (!visited[i][nx][ny] || board[nx][ny] >= tempCost) {
					board[nx][ny] = tempCost;
					visited[i][nx][ny] = true;
					queue.add(new Dot(nx, ny, i, board[nx][ny]));
				}
			}
		}
		return minCost;
	}
    
    
	public boolean rangeCheck(int x, int y) { // 지도의 범위에 x,y 좌표가 존재하는지 확인하는 메소드
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

}
