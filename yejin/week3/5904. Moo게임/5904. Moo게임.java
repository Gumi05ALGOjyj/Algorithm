import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Main {
	
	public static int N;
	public static LinkedList<Integer> middle;
	public static LinkedList<Integer> number;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        middle = new LinkedList<>();
        number = new LinkedList<>();
        
        
        middle.add(3);
        number.add(0);

 
        int idx = 1;
        
        while(true) {
        	middle.add(middle.get(idx-1)+1);
        	number.add(number.get(idx-1)*2 + middle.get(idx-1));
        	
        	if(number.get(idx)*2 + middle.get(idx) >= N) // N번째 이상의 수까지 찾은 경우
        		break;

        	idx++;
        }
        
        int find = N-1;// 문자열은 0번 인덱스에서 시작하므로 실제로 N번째의 문제는 문자열에서 N-1번째의 문자와 같다.
        while(idx>=0) { // S(0) 까지 반복한다.

        	int n = number.get(idx);
        	int m = middle.get(idx);
        	
        	// 첫번째 구간의 문자인 경우는 스킵
        	
        	if(n <= find && find < n + m) { // 두번째 구간의 문자열인 경우
        		if(number.get(idx) == find) { // 두번째 구간의 제일 앞인 경우
        			System.out.println('m');
        		}else // 두번째 구간의 제일 앞이 아닌 경우
        			System.out.println('o'); 
        		break;
        	}else if(n+m <= find) { // 세번째 구간의 문자인 경우 -> 이때는 첫번째 구간의 문자열과 동일하므로 첫번째 구간의 인덱스로 find를 바꿔준다.
        		find = find - (n + m);
        	}

        	idx--;
        }
        
    }
  
}
