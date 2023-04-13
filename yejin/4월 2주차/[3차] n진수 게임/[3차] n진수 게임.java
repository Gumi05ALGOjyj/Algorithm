import java.util.*;
import java.io.*;

// 진법 n, 미리 구할 숫자의 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p 가 주어진다.
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder convert = new StringBuilder();
        
        for(int i = 0; convert.length() <= t * m; i++) {
        	convert.append(Integer.toString(i, n)); // i라는 수를 n진법으로 나타내서 convert에 넣어준다.
        }
        
        for(int i = p - 1; answer.length() < t ; i += m) {
        	answer.append(convert.charAt(i));
        }
        
        return answer.toString().toUpperCase();
    }
}
