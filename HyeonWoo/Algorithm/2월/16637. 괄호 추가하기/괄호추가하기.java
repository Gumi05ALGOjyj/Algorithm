import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static String[] formula;              // 수식을 저장할 배열
	static String[] t_formula;            // 순서의 경우에 따라 계산을 위해 사용 될 수식 배열
	static boolean[] visited;             // 연산자를 선택했음을 체크하기 위한 배열
	static boolean[] formula_visited;     // 계산한 연산자를 체크하기 위한 
	
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		n /= 2;                                             // 연산자를 선택하기 위해선 1부터 홀수 인덱스를 봐야하기 때문에 2로 나누어 사용
		                                                    // -> 1, 3, 5, ...
		visited = new boolean[n + 1];
		
		st = new StringTokenizer(br.readLine());
		formula = st.nextToken().split("");
		
		if(n == 0) {
			System.out.print(formula[0]);                     // 연산자가 없는 경우 종료
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			ArrayList<Integer> temp = new ArrayList<>();      // 괄호로 묶을 연산자를 저장 할 배열
			temp.add(i);
			
			visited[i] = true;                                // 백트래킹으로 모든 경우를 구함
			search(i, temp);
			visited[i] = false;
		}
		
		System.out.print(max);                              // 결과를 출력
	}
	
	static void search(int index, ArrayList<Integer> temp) {  // 백트래킹으로 모든 경우를 구하는 함수
		calculate(temp);                                        // 괄호를 수식에 최대로 채워서 확인할 필요는 없으므로
		                                                        // 함수가 호출 될 때마다 계산하는 함수를 호출
		for(int i = index + 2; i <= n; i++) {                   
			if(visited[i]) continue;
			
			temp.add(i);                                          // 백트래킹 탐색
			visited[i] = true;
			search(i, temp);
			visited[i] = false;
			temp.remove(temp.size() - 1);
		}
	}
	
	static void calculate(ArrayList<Integer> temp) {          // 해당 경우로 계산을 해보는 함수
		int answer = 0;
		t_formula = formula.clone();                            // 초기 수식을 건들지 않고 계산하기 위해 새로운 배열 생성
		formula_visited = new boolean[(n * 2) + 2];             // 계산한 연산자를 체크하기 위해 배열 생성
		
		for(int index: temp) {                                  // 괄호를 적용할 연산자를 순회
			index = (index * 2) - 1;                              // 연산자의 실제 인덱스
			formula_visited[index] = true;                        
			
			String current = t_formula[index];                    // 연산자 확인
			int left = Integer.parseInt(t_formula[index - 1]);    // 연산자 양 옆의 숫자 확인
			int right = Integer.parseInt(t_formula[index + 1]);
			
			if(current.equals("+")) answer = left + right;        // 계산 시 답을 확인
			if(current.equals("-")) answer = left - right;
			if(current.equals("*")) answer = left * right;
			
			check(index, index, Integer.toString(answer));        // 양옆의 숫자 중 하나라도 이미 계산 후의 수라면
		}                                                       // 양옆으로 계산하지 않은 수가 나올 때 까지 수정
		
		for(int i = 1; i < (n * 2) + 1; i += 2) {               // 남은 연산을 왼쪽부터 계산
			if(formula_visited[i]) continue;                      // 이미 계산 했다면 continue
			formula_visited[i] = true;                            // line 62 ~ 72와 동일
			
			String current = t_formula[i];
			int left = Integer.parseInt(t_formula[i - 1]);
			int right = Integer.parseInt(t_formula[i + 1]);
			
			if(current.equals("+")) answer = left + right;
			if(current.equals("-")) answer = left - right;
			if(current.equals("*")) answer = left * right;
			
			check(i, i, Integer.toString(answer));
		}
		
		max = Math.max(max, answer);                            // 최대 값 
	}
	
	static void check(int index, int pre, String answer) {    // 계산된 값으로 최신화하는 함수
		if(formula_visited[index]) {                            // 이미 계산된 연산자라면
			t_formula[index - 1] = answer;                        // 양 옆의 계산된 수를 최신화
			t_formula[index + 1] = answer;
			
			if(index < (n * 2) - 2 && index + 2 != pre) {         // 좌우로 더 갈 수 있고 이미 방문했던 곳이 아니라면
				check(index + 2, index, answer);                    // 재귀 호출
			}
			if(index > 2 && index - 2 != pre) {
				check(index - 2, index, answer);
			}
		}
	}
}
