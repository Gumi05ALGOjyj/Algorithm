import java.io.*;
import java.util.*;


class Solution {

	int[][] bottom, left, right, eLock;

	boolean[][] visited;

	int m, n, emptyCount, len;

	public boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		m = key.length;
		n = lock.length;

		bottom = new int[m][m];
		left = new int[m][m];
		right = new int[m][m];

		// Left 채우기 / Right 채우기 / Bottom 채우기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				left[m - j - 1][i] = key[i][j]; // 오른쪽으로 270도 돌리기
				right[j][m - 1 - i] = key[i][j]; // 오른쪽으로 90도 돌리기
				bottom[m - 1 - i][m - 1 - j] = key[i][j]; // 오른쪽으로 180도 돌리기
			}
		}
		
		

		// lock 확장하기
    
		emptyCount = 0; // lock에 존재하는 0의 수(즉, 1로 채워야하는 칸)

		len = n + 2 * (m - 1);

		eLock = new int[len][len];
    
		visited = new boolean[len][len];

		int[] range = { m - 1, m - 1, n + m - 2, n + m - 2 };
		
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (range[0] <= i && i <= range[2] && range[1] <= j && j <= range[3]) {
					eLock[i][j] = lock[i - (m - 1)][j - (m - 1)];
					visited[i][j] = true;
					if (eLock[i][j] == 0) { // 1로 채워야하는 칸의 수
						emptyCount++;
					}
				} else 
					eLock[i][j] = 0;
			}
		}

		answer = rangeCheck(key) || rangeCheck(left) || rangeCheck(right) || rangeCheck(bottom);

		return answer;
	}

	boolean rangeCheck(int[][] rKey) {
		boolean answer = false;
		
		for (int i = 0; i <= len - m; i++) {
			for (int j = 0; j <= len - m; j++) {
				answer = possibleCheck(rKey, i, j);
				if (answer) {
					return answer;
				}
			}
		}

		return answer;
	}

	boolean possibleCheck(int[][] rKey, int sx, int sy) {
		boolean answer = false;

		int count = 0;

		// sx와 sy가 배열에 존재하는지?
		for (int i = 0; i < m; i++, sx++) {
			int tmp = sy;
			for (int j = 0; j < m; j++, tmp++) {
				if (visited[sx][tmp]) { // lock의 부분을 방문한 경우
					if (eLock[sx][tmp] + rKey[i][j] == 1) { 
						if (eLock[sx][tmp] == 0) // 자물쇠의 0인 부분을 채우는 경우
							count++;
					} else // 0이거나 2이면 false를 반환한다.
						return false;
				}
			}
		}
        
		if (count == emptyCount) // 자물쇠의 0인 부분을 열쇠의 1로 다 채운 경우
			return true;

		return answer;
	}

}
