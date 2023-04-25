package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 브루트포스 알고리즘
 * 백트래킹
 */
class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class B18428_감시피하기 {
	static int N;
	static char[][] board;
	static boolean isPossible = true;
	static List<Point> teachers = new ArrayList<Point>();
	static List<Point> students = new ArrayList<Point>();
	static int hideNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			int idx = 0;
			for (int j = 0; j < input.length(); j += 2) {
				board[i][idx] = input.charAt(j);
				if (board[i][idx] == 'T') {
					teachers.add(new Point(i, idx));
				}
				idx++;
			}
		}
		// ------입력 완료---------
		putObject(0);

		System.out.println("NO");
	}

	public static void putObject(int cnt) { // 장애물을 놓는 행위를 한다! 조하입보이~
		if (cnt == 3) {
			// 가능한지 검사해준다.
			for (int i = 0; i < teachers.size(); i++) {
				if (inTeacherSight(teachers.get(i))) {
					isPossible = false;
					return;
				}
			}
//			return;
			System.out.println("YES");
			System.exit(0);

		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 'X')
					continue;
				board[i][j] = 'O';
				putObject(cnt + 1);
				board[i][j] = 'X';
			}
		}

	}

	private static boolean inTeacherSight(Point point) {
		// TODO Auto-generated method stub
		// 선생님 입장에서 학생이 보이는지 검사를 해줄 것이다.
		int X = point.x;
		int Y = point.y;

		// 위를 훑어본다.
		int x = X;
		while (x < N - 1) {
			x++;
			if (board[x][Y] == 'O')
				break;
			if (board[x][Y] == 'S')
				return true;
		}

		// 아래를 훑어본다.
		x = X;
		while (x > 0) {
			x--;
			if (board[x][Y] == 'O')
				break;
			if (board[x][Y] == 'S')
				return true;
		}

		// 왼쪽을 훑어본다.
		int y = Y;
		while (y > 0) {
			y--;
			if (board[X][y] == 'O')
				break;
			if (board[X][y] == 'S')
				return true;
		}

		// 오른쪽을을 훑어본다.
		y = Y;
		while (y < N - 1) {
			y++;
			if (board[X][y] == 'O')
				break;
			if (board[X][y] == 'S')
				return true;
		}

		return false;
	}

}
