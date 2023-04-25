import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[입력]
첫째 줄에 수열 S의 크기 N이 주어진다. (1 ≤ N ≤ 20)

둘째 줄에는 수열 S가 주어진다. S를 이루고있는 수는 100,000보다 작거나 같은 자연수이다. 
*/

public class Main {



	public static int N, target;
	
	public static int[] numbers;
	
	public static boolean[] array; 

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		
		numbers = new int[N];
		
		array = new boolean[2000001]; // 부분수열의 합 1이상 2,000,000이하로 값이 나올 수 있음 (20 * 100000 = 2000000)
		
		for(int i =0; i<N; i++) 
			numbers[i] = Integer.parseInt(str[i]);
		
		// 1부터 N개의 부분수열의 합을 구한다.
		for(int i =1; i<=N; i++) {
			target = i;
			Combination(0, 0, 0);
		}
		
		// 부분수열의 합이 나오지 않은 배열의 값은 false이므로 값의 유무를 체크한다.
		for(int i =1; i<=2000000; i++) {
			if(!array[i]) {
				System.out.println(i);
				return;
			}
		}
		
		
	}
	
	static void Combination(int count,int start, int sum) {
		
		if(count == target) { // target개의 부분수열의 구한 경우
			if(sum <= 2000000)
				array[sum] = true; // sum이라는 부분수열의 합이 존재하면 array[]에서 true로 바꿔준다.
			return;
		}
		
		for(int i = start; i<N; i++) 
			Combination(count+1, i+1, sum + numbers[i]);
	}

}
