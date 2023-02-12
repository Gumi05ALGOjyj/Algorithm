import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static long n;
	public static long r;
	public static long result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine(); // 팀 이름, 영문 대문자 3글자!
		int N = Integer.parseInt(br.readLine()); // 사람 수 (3이상 50000이하명)
		long alpha[] = new long[91];

		// n명의 이름이 주어짐
		// n명의 이름의 알파벳 첫글자를 몇 개 있는지 확인하는 배열 생성
		String[] names = new String[N];
		for (int i = 0; i < N; i++) {
			names[i] = br.readLine();
			alpha[(int) names[i].charAt(0)] += 1;
		}

		// S를 돌면서 알파벳이 뭐뭐있는지 확인함
		char[] c = S.toCharArray(); // c[0]='D', c[1]='P', c[2]='S'


		if (c[0] == c[1] && c[1] == c[2]) {// 세자리 알파벳이 모두 같은 경우
			// n명의 이름 첫번째글자를 돌면서 c[0]알파벳이랑 동일한 알파벳 개수 C 3
			// c[0]알파벳이랑 동일한 알파벳 개수 : alpha[(int)'A'] -> alpha[(int)c[0]]
			result = (alpha[(int) c[0]]*(alpha[(int) c[0]]-1)*(alpha[(int) c[0]]-2))/6;

		} else if (c[0] == c[1] && c[1] != c[2]) { // 두 글자가 서로 같고 한글자가 다른 경우
			// c[0]알파벳이랑 동일한 알파벳 개수 C 2 + c[2]알파벳이랑 동일한 알파벳 개수 C 1
			result = (alpha[(int) c[0]]*(alpha[(int) c[0]]-1)/2) * alpha[(int) c[2]];
	
		} else if (c[0] == c[2] && c[1] != c[2]) {
			result = (alpha[(int) c[0]]*(alpha[(int) c[0]]-1)/2) * alpha[(int) c[1]];

		} else if (c[1] == c[2] && c[0] != c[2]) {
			result = (alpha[(int) c[1]]*(alpha[(int) c[1]]-1)/2) * alpha[(int) c[0]];
		} else { // 셋 다 다른 개수
				    // 각각알파벳개수 C 1
			result = alpha[(int) c[0]] * alpha[(int) c[1]] * alpha[(int) c[2]];
		}

		System.out.println(result);

	}

}
