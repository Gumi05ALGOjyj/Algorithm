import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[입력]
DPS
7
DONGGAS
PICASSO
SEMTEO
DJS
WBCHO
RAARARAARA
WEASEL

[출력]
2
*/
public class Main {

	public static String teamName;

	public static int N;
	
	// 알파벳이 방문한 횟수를 체크한다.
	public static long[] alpha1, alpha2;

	public static long result = 1L;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		teamName = br.readLine();

		N = Integer.parseInt(br.readLine());

		alpha1 = new long[26];
		alpha2 = new long[26];
		
		// alpha1[]은 찾을 문자열에서 알파벳의 수를 체크한다.
		char c;
		for (int i = 0; i < 3; i++) {
			c = teamName.charAt(i);
			alpha1[c-'A']++;
		}

		// alpha2[]는 키보드에서 입력 받은 문자열에서 첫번째 문자의 수를 체크한다.
		for (int i = 0; i < N; i++) {
			c = br.readLine().charAt(0);	
			alpha2[c-'A']++;
		}
		
		
		for(int i =0; i<26; i++) {
			if(alpha1[i] > 0)
				result *= collaboration(alpha2[i], alpha1[i]);
		}

		System.out.println(result);

	}
	
	static long collaboration(long n, long r) {
		if (r == 1L) {
			return n;
		} else if (r == 2L) {
			return (n * (n - 1)) / 2;
		} else {
			return (n * (n - 1) * (n - 2)) / 6;
		}
	}

}
