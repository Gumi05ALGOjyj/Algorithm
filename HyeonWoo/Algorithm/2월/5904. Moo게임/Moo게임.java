import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder("moo");
		
		int n = Integer.parseInt(st.nextToken());
		
		if(n <= 3) {                                          // 3보다 아래일 경우 바로 결과 출력
			System.out.print(sb.charAt(n - 1));
			return;
		}
		
		int k = 0;
		int s_len = 3;
		
		while(s_len < n) {                                    // 문자열 길이를 규칙에 맞게 늘려가며 k를 구함
			k++;
			s_len = (k + 3) + (s_len * 2);
		}
		
		while(k >= 0) {
			int possible_s = ((s_len - (k + 3)) / 2) + 1;       // 값을 구할 수 있는 범위, 즉 가운데 범위를 선언
			int possible_e = possible_s + (k + 2);
			
			if(possible_s <= n && possible_e >= n) {            // n이 범위 안에 있다면
				for(int i = 0; i < k; i++) sb.append("o");        // 가운데 문자열을 생성
				
				System.out.print(sb.charAt(n - possible_s));      // 해당 문자를 출력하고 종료
				return;
			}
			
			if(n > possible_e) n -= possible_e;                 // 범위보다 큰 경우면 인덱스를 맞춰주기 위해 0 ~ 범위끝 만큼의 수를 빼줌 
			                                                    
			k--;                                                // k -1 케이스로 세팅
			s_len = possible_s - 1;
		}
	}
}
