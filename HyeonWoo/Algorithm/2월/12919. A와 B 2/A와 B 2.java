package baekjoon;

import java.io.*;
import java.util.*;

public class Solution_12919 {
    static String t;
    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder s = new StringBuilder(st.nextToken());
        st = new StringTokenizer(br.readLine());
        t = st.nextToken();

        boolean reverse = false;                                                    // 문자열이 뒤집혀있는지 아닌지를 boolean으로 확인
                                                                                    // -> 문자열을 일일히 직접 뒤집지 않는다는 의미
        solution(s, reverse);

        System.out.print(result);
    }
  
    static void solution(StringBuilder s, boolean reverse) {
        StringBuilder t_sb = new StringBuilder(s.toString());                       // bfs 탐색과 같은 방식으로 진행하기 때문에 문자열이 겹치지 않게 새로 생성
      
        if((!checkStr(t, t_sb.toString()) && !checkStr(t, t_sb.reverse().toString()) || result == 1)) {
            return;                                                                 // 현재 문자열이 목적의 문자열에 포함되어 있지 않거나 이미 결과를 확인한 경우 탈출
        }                                                                           // 포함을 검사하는 경우 뒤집은 경우도 검사

        if(s.length() == t.length()) {                                              // 목적 문자열과 현재 문자열의 길이가 같다면 검사
            if(reverse) s.reverse();                                                // reverse가 true라면 뒤집힌 상태이므로 reverse
            if(s.toString().equals(t)){                                             
                result = 1;
            }
            return;
        }
                                                                                    // ----- A를 추가하는 경우 -----
        t_sb = new StringBuilder(s.toString());
      
        if(reverse) t_sb.insert(0, "A");                                            // 뒤집혀 있는 상태라면 앞쪽에
        else t_sb.append("A");                                                      // 아니라면 뒤에 A를 추가
      
        solution(t_sb, reverse);                                                    // 재귀 호출
                                                                                    // ----- B를 추가하는 경우 -----
        t_sb = new StringBuilder(s.toString());
      
        if(reverse) {                                                               // 뒤집혀 있는 경우라면
            t_sb.insert(0, "B");                                                    // 앞쪽에 B를 추가하고 reverse 인자를 반대로 하여 넘겨줌
            solution(t_sb, false);            
        } else {
            t_sb.append("B");                                                       // 뒤집혀 있지 않은 경우라면
            solution(t_sb, true);                                                   // 뒤쪽에 B를 추가하고 reverse 인자를 반대로 하여 넘겨줌
        }
    }
  
    static boolean checkStr(String A, String B) {                                   // 현재 문자열이 목적 문자열에 포함되는지 확인하는 함수
        if(A.contains(B)) return true;
        else return false;
    }
}
