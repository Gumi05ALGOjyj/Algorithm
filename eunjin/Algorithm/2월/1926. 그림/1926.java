package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1926 {
	static int N, M;
	static int[][] board;
	static boolean[][] isChecked;
	static int MAX = 0;
	static int cnt = 0;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int size=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		isChecked = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// ---저장완료--------

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				size=0;
				int m = findPicture(i, j);
				if(m!=0) cnt++;
				System.out.println("사이즈 확인중! : "+m);
				MAX = Math.max(MAX, m);
				
			}
		}
		
		
		//System.out.println(cnt);
		//System.out.println(findPicture(3, 2, 0));
	}

	public static int findPicture(int x, int y) {
//		if (isChecked[x][y])
//			return 0;
//		if (board[x][y] == 0)
//			return 0;
//		if((x>N && x<0) || (y>M && y<0)) return size;

		for (int i = 0; i < 4; i++) {
			int curX = x + dx[i];
			int curY = y + dy[i];
//			System.out.println("x="+curX+" y="+curY);
			if (curX >= 0 && curX < N && curY >= 0 && curY < M) {
				if (isChecked[curX][curY])
					continue;
				if (board[curX][curY] == 1) {
					// size++;
					System.out.println("x="+curX+" y="+curY);
					isChecked[curX][curY] = true;
					
					return findPicture(curX, curY)+1;
				}else {
					continue;
				}
			}
		}
		
		System.out.println(111111111);
		return 1;
	}
}
