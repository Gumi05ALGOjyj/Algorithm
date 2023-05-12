import java.util.*;
import java.io.*;

class Solution {
	String answer = "";

	public String solution(String p) {
		answer = splitStr(p);
		return answer;
	}

	public String splitStr(String str) {
		String result = "";
		if (str.length() == 0) // 빈 문자열인 경우 그냥 반환한다.
			return "";

		String u = "";
		String v = "";

		char c;
		int idx = -1, count = 0;
		boolean ischeck = true; // 처음 세팅을 올바른 괄호 문자열이다.

		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c == '(') {
				count++;
			} else if (c == ')') {
				if (count == 0) {// 균형잡힌 괄호 문자열인 경우
					ischeck = false; // 균형잡힌 문자열이므로 false로 만들어준다.
                }
				count--;
			}

			if (i != 0 && count == 0) {
				idx = i;
				break;
			}
		}

		u = str.substring(0, idx + 1);

		v = str.substring(idx + 1);

		if (!ischeck) { // u가 균형잡힌 문자열인 경우
			result = makeStr(u, v); // u를 올바른 문자열로 바꿔서 반환
		} else if (ischeck) { // u가 올바른 문자열인 경우
			result = u + splitStr(v); // v를 올바른 문자열로 바꿔서 반환
		}
		return result;

	}
    
    
	String makeStr(String u, String v) { // u를 올바른 문자열로 바꿔서 반환하는 메소드
		String result = "(" + splitStr(v) + ")";

		// 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다.
		char c;
		for (int i = 1; i < u.length() - 1; i++) {
			c = u.charAt(i);
			if (c == '(')
				result += ')';
			else if (c == ')')
				result += '(';
		}
		return result;
	}

}
