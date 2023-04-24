import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	
	public static String S,T;

	public static boolean result = false;
	
	public static HashMap<String , Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s="";
		
		S = br.readLine();
		T = br.readLine();
		

		minusWord(T.length(), T);
		
		System.out.println(result ? 1 : 0);
	}
	
	static void minusWord(int count, String str) {
		
		if(count == S.length()) { // 기저조건 : T문자열에서 문자를 하나씩 지웠을 때 S의 문자열 길이와 같아진 경우
			if(str.equals(S)) { 
				result = true;
			}
			return;
		}
		
		// 문자열 뒤에 'A'가 있으면 뒤에서 'A'를 지운다.
		String s;
		if(str.charAt(str.length()-1) == 'A') {
			s = str.substring(0, str.length()-1);
			if(!map.containsKey(s)) { // 이전에 이미 나왔던 문자열인 경우는 제외한다.
				map.put(s,1);
				minusWord(count-1, s);
			}
		}
		
		// 문자열 앞에 'B'가 있으면 앞에 'B'를 지우고 문자열을 거꾸로 반환한다.
		s = "";
		if(str.charAt(0) == 'B') {
			for(int i = str.length()-1; i>0; i--) 
				s += str.charAt(i);
			if(!map.containsKey(s)) { // 이전에 이미 나왔던 문자열인 경우는 제외한다.
				map.put(s, 1);
				minusWord(count-1, s);
			}
		}
		
	}
}
