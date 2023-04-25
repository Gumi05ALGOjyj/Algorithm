import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class 16637_괄호추가하기 {
	public static int N;
	
	public static int[] number;// 숫자
	
	public static char[] operator; // 연산자
	
	public static int[] termSum; // 구간의 합
	
	public static int target;
	
	public static int max = Integer.MIN_VALUE;
	
	public static int number_len,operator_len,termSum_len;
    public static void main(String[] args) throws IOException {     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        number_len = N/2+1;
        number = new int[number_len]; // 숫자
        
        termSum_len = N/2;
        termSum = new int[termSum_len]; // 구간의 합
        
        operator_len = N/2;
        operator = new char[operator_len]; // 연산자
        
        
        String[] str = br.readLine().split("");
        
        int idx1=0, idx2=0, idx3=0;
        for(int i =0; i<N; i++) {
        	if(i%2 == 0) {
        		number[idx1] = Integer.parseInt(str[i]); 
        		if(i != 0) {
        			char c = operator[idx3-1];
        			switch(c) {
        			case '*':
        				termSum[idx2++] = number[idx1-1]*number[idx1];
        				break;
        			case '-':
        				termSum[idx2++] = number[idx1-1]-number[idx1];
        				break;
        			case '+':
        				termSum[idx2++] = number[idx1-1]+number[idx1];
        				break;
        			}
        		}
        		
        		idx1++;
        	}else if(i%2 != 0) {
        		operator[idx3++] = str[i].charAt(0);
        	}
        }

        
        // 부분집합을 형성한다.
        
        // 괄호가 0개인 경우
        int temp = number[0];
        for(int i =0; i<operator_len; i++) {
        	temp = calculator(operator[i], temp, number[i+1]);
        }
        max = Math.max(temp, max);
        
        
        // 괄호가 1개 이상 number_len/2 이하인 경우
        for(int i =1; i<=number_len/2; i++) {
        	target = i;
        	choiceSum(0, 0, new ArrayList<Integer>());
        }
        
        System.out.println(max);
    }
    
    static void choiceSum(int start, int count, ArrayList<Integer> tempList) {
    	if(target == count) {

    		Collections.sort(tempList);


    		int sum = 0;
    		
    		
    		int i;
    		if(tempList.contains(0)) { // 제일 앞에 괄호가 있는 경우
    			sum = termSum[0]; 
    			i = 1; // 다음 연산자부터 계산한다.
    		}else { // 제일 앞에 괄호가 없는 경우
    			sum = number[0];
    			i=0; // 첫번째 연산자부터 계산한다.
    		}
   
    		if(tempList.get(tempList.size()-1) == termSum_len-1) { // 괄호가 마지막에 있는 경우
    			for(; i<termSum_len-1; i++) {  // 괄호가 존재하는 경우까지만 체크하기		
    				if(tempList.contains(i+1)) { // 연산자 뒤의 값이 괄호인 경우 
    					sum = calculator(operator[i], sum, termSum[i+1]); // 이전에 계산한 값에 연산자를 통해 괄호를 계산한 값을 넣어준다. 
    					i++; // 괄호 안에 있는 연산자는 skip해도 괜찮으므로 i++;
    				}else
    					sum = calculator(operator[i], sum, number[i+1]);  // 연산자 뒤의 값이 괄호가 아닌 경우 단순 계산
    			}
    		}else { // 괄호가 마지막에 없는 경우
    			for(; i<operator_len; i++) { // 괄호 뒤에 일반 정수가 나오는 경우까지 체크하기
    				if(tempList.contains(i+1)) { // 연산자 뒤의 값이 괄호인 경우 
    					sum = calculator(operator[i], sum, termSum[i+1]); // 이전에 계산한 값에 연산자를 통해 괄호를 계산한 값을 넣어준다. 
    					i++; // 괄호 안에 있는 연산자는 skip해도 괜찮으므로 i++;
    				}else
    					sum = calculator(operator[i], sum, number[i+1]); // 연산자 뒤의 값이 괄호가 아닌 경우 단순 계산
    			}
    			
    		}

    		
    		max = Math.max(sum, max);

    		return;
    	}
    	
    	for(int i = start; i<termSum_len; i++) {
    		tempList.add(i);
    		choiceSum(i+2, count+1, tempList); // 바로 옆의 연산자는 같이 쓸 수 없으므로 start 가 i+2이다 → ((3+5)*2)를 할 수 있으므로
    		tempList.remove(tempList.size()-1);
    	}
    
    }
    
    static int calculator(char c,int num1, int num2) {
    	switch(c) {
		case '*':
			return num1*num2;
		case '-':
			return num1-num2;
		case '+':
			return num1+num2;
		default:
			return -1;
		}
    }

}
