import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class testdfs {
	static int max; // 마지막 곡의 볼륨 중 최댓값 출력하기 
	static int n, s, m;

	public static void main(String[] args) throws IOException {
		// 0보다 작은 값으로 볼륨을 바꾸거나 M보다 큰 값으로 볼륨을 바꿀 수 없다.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 3
		s = Integer.parseInt(st.nextToken()); // 5
		m = Integer.parseInt(st.nextToken()); // 10
		int[] vol = new int[n];				  // 소리  n개
		int[][] dp = new int[n][m + 1];		  // 합이 m 이하여야하니까 m+1사이즈로 만들어줌

		st = new StringTokenizer(br.readLine());

		// 각각 볼륨 입력받기
		for (int i = 0; i < n; i++) {
			vol[i] = Integer.parseInt(st.nextToken());		// 5 3 7
		}

		// 첫번째 곡은 초기볼륨 s를 이용해서 계산
		if (s + vol[0] <= m)			// 초기볼륨 s + vol[0]해서 최대볼륨 안넘으면 1
			dp[0][s + vol[0]] = 1;
		if (s - vol[0] >= 0)			// 초기볼륨s - vol[0]해서 0이랑 같거나 크면 1 
			dp[0][s - vol[0]] = 1;

		// 2차원배열에서 0행은 -> 초기볼륨에 첫번째 곡 계산해서 값 넣어둠
		// 2차원배열에서 1행 -> 두번째 노래
		// i는 1 즉 두번째 노래에서 n-1번째 노래까지
		for (int i = 1; i < n; i++) { // 두번째곡을 계산할때 첫번째곡에서 나올수 있는 값에 두번째볼륨을 더하거나 빼야됨
			// i = 1일때
			for (int j = 0; j < m+1; j++) { // 그래서 0부터 m까지 돌면서 1이 있는 곳을 찾음 -> 1이 있다는건 그 이전곡에서 나올 수 있는 볼륨을 나타내는 거니까
				if (dp[i - 1][j] == 1) { 	// i-1을 돌면서 1이 있는 곳을 찾음 (그 전곡에서 나올 수 있는 볼륨)
											// 그 값(나올수 있는 볼륨)에 i번째 즉 현재 볼륨을 더해봄
					int tmp = j + vol[i];		// 이 때 j는 앞곡까지의 볼륨임 / da[i-1][j]아닌 것에 주의
					if (tmp >= 0 && tmp <= m) {
						dp[i][tmp] = 1;
					}
					tmp = j - vol[i];			// 빼서도 가능한지 확인
					if (tmp >= 0 && tmp <= m) {
						dp[i][tmp] = 1;
					}
				}
			}
		}

//		그냥 전체 배열 확인
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m + 1; j++) {
//				System.out.print(dp[i][j]);
//			}
//			System.out.println();
//		}

		// 최종적으로 가장 마지막 노래까지 갔을 때 최대값을 구해야하므로!!
		// dp[n-1]일때 열 반복문을 돌려야함

		
		int maxj = -1;
		for(int j  = m ; j >= 0 ; j--) {
			if(dp[n-1][j] == 1) { // n-1일때 돌려야하는 이유는 
								// 가장 마지막 곡까지 들었을때의 최댓값을 구하는거니까
								// 마지막 곡을 연주할 수 있는 볼륨 중 최댓값을 구하는거니까
				maxj = j;
				break;			// 뒤에서부터 돌려서 큰 값 찾자마자 반복문 탈출
			}
		}
		
		if (max > -1)
			System.out.println(maxj);
		else
			System.out.println(-1);


	}

}
