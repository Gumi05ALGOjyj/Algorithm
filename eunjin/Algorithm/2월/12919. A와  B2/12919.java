import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B12919 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder S = new StringBuilder();
	static StringBuilder T = new StringBuilder();;
	static int result = 0;
	static int count;

	public static void main(String[] args) throws IOException {
		S.append(br.readLine());
		T.append(br.readLine());
		count = T.length() - S.length();
		make(0, T);
		System.out.println(result > 0 ? 1 : 0);
	}

	private static void make(int cnt, StringBuilder sb) {
		if (sb.length() == S.length()) {
			if (sb.toString().equals(S.toString()))
				result++;
			//System.out.println(sb);
			return;
		}

		if(sb.charAt(0) == 'B' && sb.charAt(sb.length()-1)=='A') {
			make(cnt + 1, subBReverse(sb));
			sb.append("B").reverse();
			make(cnt + 1, subA(sb));
			sb.append("A");
		}else if(sb.charAt(0) == 'B') {
			make(cnt + 1, subBReverse(sb));
			sb.append("B").reverse();
		}else if(sb.charAt(sb.length() - 1) == 'A') {
			make(cnt + 1, subA(sb));
			sb.append("A");
		}else {
			return;
		}
		
//		if (sb.charAt(0) == 'B') {
//			make(cnt + 1, func2(sb));
//			S.append("B").reverse();
//			//make(cnt, sb);
//		}
//		if (sb.charAt(sb.length() - 1) == 'A') {
//			make(cnt + 1, func1(sb));
//			S.append("A");
//			//make(cnt, sb);
//		}

	}

	private static StringBuilder subA(StringBuilder sb) {
		return sb.deleteCharAt(T.length() - 1);
	}

	private static StringBuilder subBReverse(StringBuilder sb) {
		sb.deleteCharAt(0);
		return sb.reverse();
	}
}
