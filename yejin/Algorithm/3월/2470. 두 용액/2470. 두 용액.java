import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int N;

	public static int[] minus, plus, array;
	
	public static int minusSize, plusSize, number1, number2;
	
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		
		array = new int[N];
		
		for(int i =0; i<N; i++) {
			array[i] = Integer.parseInt(str[i]);
			if(array[i]<0)
				minusSize++;
			else
				plusSize++;
		}
		
		minus = new int[minusSize];
		
		plus = new int[plusSize];
		
		int idx1=0, idx2=0;
		for(int i =0; i<N; i++) {
			if(array[i]<0)
				minus[idx1++] = array[i];
			else
				plus[idx2++] = array[i];
		}
		
		Arrays.sort(minus);
	
		Arrays.sort(plus);
		
	
		if(minusSize == 0) {
			System.out.println(plus[0]+" "+plus[1]);
		}else if(plusSize == 0) {
			System.out.println(minus[minusSize-2]+" "+minus[minusSize-1]);
		}else {
			for(int i =0; i<minusSize; i++) 
				binarySearch(0, plusSize-1, minus[i], -1);
			
			// 음수에서만 추출한 값과 양수에서만 추출한 값을 비교한다.
			
			int temp;
			if(minusSize>=2) {
				temp = Math.abs(minus[minusSize-2]+minus[minusSize-1]);
				if(temp < result) {
					result = temp;
					number1 = minus[minusSize-2];
					number2 = minus[minusSize-1];
				}
			}
			
			if(plusSize>=2) {
				temp = plus[0]+plus[1];
				if(temp < result) {
					number1 = plus[0];
					number2 = plus[1];
				}
			}
			
			System.out.println(number1+" "+number2);
			
		}
		
		
		
	}
	
	static void binarySearch(int low, int high, int num1, int num2) {
		
		if(low>high)
			return;	
		
		int mid = (low+high)/2;
		
		int temp = num1+plus[mid];
		
		int sum = Math.abs(temp);
		
		if(temp < 0) { // 오른쪽으로 탐색
			if(sum < Math.abs(result)) {
				result = sum;
				number1 = num1;
				number2 = plus[mid];
			}
			binarySearch(mid+1, high, num1, plus[mid]);
		}else if(temp > 0) { // 왼쪽으로 탐색
			if(sum < Math.abs(result)) {
				result = sum;
				number1 = num1;
				number2 = plus[mid];
			}
			binarySearch(low, mid-1, num1, plus[mid]);
		}else { // temp == 0
			result = 0;
			number1 = num1;
			number2 = plus[mid];
			return;
		}

	}
}
