package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17070_파이프옮기기 {
	static int N;
	static int result = 0;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// ----------입력완료------------------------------------------------------

		movePipe(0, 1, 1);
		System.out.println(result);
	}

	public static void movePipe(int endX, int endY, int state) {
		// state = 1(가로), 2(세로), 3(대각선)
		if (endX == N - 1 && endY == N - 1) {
			// 끝에 도달했다!!
			result++;
			return;
		} else if (endX < 0 && endX > N + 1 && endY < 0 && endY > N + 1) {
			return;
		}

		// int startX, startY;

		switch (state) {
		case 1: // 가로일 경우
			// 가로일 경우에는 2가지의 일을 할 수 있다. 옆으로 밀거나? 대각선으로 바꾸거나!
			if (endY + 1 < N && board[endX][endY + 1] == 0) {
				// 가로로 갈 수 있다.
				movePipe(endX, endY + 1, 1);
				// 이제 대각선으로 밀 수 있는지 나머지 범위를 검사해준다
			}
			break;

		case 2: // 세로일 경우 -> 밑으로 밀거나, 대각선으로!
			if (endX + 1 < N && board[endX + 1][endY] == 0) {
				movePipe(endX + 1, endY, 2);
				// 대각선으로 이동 가능한지 다시 검사해본다
			}
			break;
		case 3:
			// 대각선일 경우 -> 3가지 행동 모두 가능하다.
			if (endY + 1 < N && board[endX][endY + 1] == 0) {
				// 가로로 갈 수 있다.
				movePipe(endX, endY + 1, 1);
			}

			if (endX + 1 < N && board[endX + 1][endY] == 0) {
				movePipe(endX + 1, endY, 2);
			}
			break;
		}
		
		if(endX+1<N && endY+1<N&&board[endX+1][endY]==0 && board[endX+1][endY+1]==0 && board[endX][endY+1]==0)
			movePipe(endX+1, endY+1, 3);
	}
}
