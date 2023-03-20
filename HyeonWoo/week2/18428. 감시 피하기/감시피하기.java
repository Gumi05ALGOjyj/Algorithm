import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static String[][] board;
	static ArrayList<int[]> teacher = new ArrayList<>();
	static boolean result = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		board = new String[n][n];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; j++) {
				String temp = st.nextToken();
				board[i][j] = temp;
				
				if(temp.equals("T")) {                            // 선생님의 위치를 따로 저장
					int[] position = {i, j};
					teacher.add(position);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j].equals("X")) {                    // board를 순회하며 "X"일 경우 백트래킹 시작
					board[i][j] = "O";
					solution(i, j, 1);
					
					if(result) {                                   // 되는 경우를 찾았다면 종료
						System.out.print("YES");
						return;
					}
					
					board[i][j] = "X";
				}
			}
		}
		System.out.print("NO");                              // 끝까지 못찾았다면 NO 출력
	}
	
	static void solution(int x, int y, int count) {
		if(count == 3) {                                     // 장애물 3개를 세웠다면 결과 확인 후 종료
			if(check()) result = true;
			return;
		}
		
		if(result || (x == n - 1 && y == n - 1)) return;     // 결과가 이미 나왔거나 더이상 볼 수 없을 때 return
		
		int ty = y < n - 1 ? y + 1 : 0;                      // 다음 좌표 계산
		int tx = ty == 0 ? x + 1 : x;
		
		for(int i = tx; i < n; i++) {
			for(int j = ty; j < n; j++) {
				if(board[i][j].equals("X")) {                    // 백트래킹으로 재귀 호출
					board[i][j] = "O";
					solution(i, j, count + 1);
					board[i][j] = "X";
				}
				
				if(j == n - 1) ty = 0;                           // y값이 행 끝까지 왔다면 0으로 초기화
			}                                                  // -> for문의 초기값으로 할당되어 있기 때문에 직접 값을 변경해줘야 함
		}
	}
	
	static boolean check() {		                           // 선생님의 좌표를 순회하며 상하좌우를 확인
		for(int[] position : teacher) {
			int tx = position[0];
			int ty = position[1];
			
			for(int i = ty - 1; i > -1; i--) {                                  
				if(board[tx][i].equals("O") || board[tx][i].equals("T")) break;   // 좌
				if(board[tx][i].equals("S")) return false;
			}
			for(int i = tx - 1; i > -1; i--) {
				if(board[i][ty].equals("O") || board[i][ty].equals("T")) break;   // 상
				if(board[i][ty].equals("S")) return false;
			}
			for(int i = ty + 1; i < n; i++) {
				if(board[tx][i].equals("O") || board[tx][i].equals("T")) break;   // 우
				if(board[tx][i].equals("S")) return false;
			}
			for(int i = tx + 1; i < n; i++) {
				if(board[i][ty].equals("O") || board[i][ty].equals("T")) break;   // 하
				if(board[i][ty].equals("S")) return false;
			}
		}
		return true;                                                          // 마지막까지 문제 없다면 true 
	}
}
