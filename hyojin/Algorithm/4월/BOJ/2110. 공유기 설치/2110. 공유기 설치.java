import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n,c;
	static int[] a;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		a = new int[n+1];
		for(int i = 1 ; i < n+1; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		
		pro();
		
		
	}
	static boolean determination(int d) {
		// d만큼의 거리 차이를 둔다면 c개 만큼의 공유기를 설치할 수 있는가?
		
		// 제일 왼쪽 집부터 가능한 많이 설치해보자!
		// d만큼의 거리를 두면서 최대로 설치한 개수와 c를 비교하자
		int cnt = 1, last = a[1];
		for(int i = 2; i <= n ; i++){
			if(a[i] - last >= d) {
				cnt++;
				last = a[i];
			}
		}
		return cnt>=c;
	}
	
	static void pro() {
		// determination을 빠르게 하기 위해서 정렬해주기
		Arrays.sort(a,1,n+1);
		
		int l = 1, r = 1000000000, ans =0;
		// [L..R]범위 안에 정답이 존재한다!
		// 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자
	
		while(l<=r) {
			int mid = (l + r) / 2;
			if(determination(mid)) {
				ans = mid;
				l = mid+1;
			}else {
				r = mid -1;
			}
		}
		System.out.println(ans);
	}
}
