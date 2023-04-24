import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long n,m;
	static long a[];
	static long person;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 입국심사대 수 (1<= <=100,000)
		m = Integer.parseInt(st.nextToken());	// 총 사람 수 (1<= <=1,000,000,000)



		a = new long[(int) (n + 1)];	// 입국심사대에서 걸리는 시간을 저장할 배열
		for (int i = 1; i < n+1; i++) { // 1부터 n까지 저장하기 위해 크기를 n+1로
			a[i] = Long.valueOf(br.readLine()); 
		}
		
		// 심사를 받는데 걸리는 "시간의 최솟값!"
		start();
		
	}

	private static void start() {
		// 이분탐색을 진행하기위해 정렬
		Arrays.sort(a);
		
		
		
		long ans = 0;
		long l = 1;	// 왼쪽
		long r= 1000000000000000000L; // 최악으로 걸리는 시간을 구하면 사람은 1,000,000,000명 , 심사대는 1개일 때, 그 심사대가 10^9의 시간이 걸릴때 -> 10^18
		while(l <= r) {
			
			long mid = (l + r) / 2;
			//System.out.println(mid);
			if(proc(mid)) { // 시간이 mid만큼일 때 몇명의 사람을 확인할수 있는가?
				ans = mid;
				r = mid - 1;
			}else {
				l = mid + 1;
			}
		}
		
		System.out.println(ans);
		
	}

	private static boolean proc(long t) {
		// 시간이 mid만큼일 때 몇명의 사람을 확인할수 있는가?
		// t초가 지났을 때 할 수 있는 명수 구하기

		person = 0;
		//System.out.println("T초가 지났을 때 할 수 있는 명수 구하기");
		for(int i = 1; i < n+1 ; i++) {	// 검색대 처음부터 끝까지 돌면서 각 검색대에서 몇명의 사람이 확인 가능한지 check
			if(a[i] <= t) {	// 검색대에서 걸리는 시간 <= t초 : 검색대에서 5초 걸리는데 3초가 주어지면 검사를 못함
				//System.out.println("a[i] : " + a[i]);
				long myung = t / a[i]; // t초동안 받을 수 있는 명수
				//System.out.println("myung : " + myung);
				person += myung;
                if(person>m)
                    break;
			}
		}
		//System.out.println(person>=m);
		//System.out.println("----------------");
		
		return person >= m;	// 우리가 구하는 명수랑 같거나 더 많은 사람을 t시간에 확인할 수 있으면 true
		
	}
}
