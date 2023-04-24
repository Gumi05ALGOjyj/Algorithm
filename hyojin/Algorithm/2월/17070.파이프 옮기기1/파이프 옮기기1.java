import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int cnt;
	static int dir;
	static int[][] arr;
	static int n;
	static int[] dx = { 0, 1, 1 }; // →, ↘, ↓
	static int[] dy = { 1, 1, 0 };

	public static void dfs(int x, int y, int start, int last) {
		if(x==n-1 && y==n-1) {
			cnt = cnt + 1;
			return;
		}
		for (int i = start; i < last; i++) {

			int nx = x + dx[i];
			int ny = y + dy[i];


			if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
				if (arr[nx][ny] != 1) {
					if(i==1) { // 대각선으로 갈 때
						if(arr[nx-1][ny] == 0 && arr[nx][ny-1]==0)
							dfs(nx,ny,0,3);
						
					}
					else if(i==0) // 가로로 갈 때
						dfs(nx, ny, 0, 2);
					else // 세로로 갈 때
						dfs(nx, ny, 1,3);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0, 2);
		System.out.println(cnt);
	}
}
