import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 1309_동물원 {
	
	public static int N;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		int tempZero, tempOne, zero = 1, one = 2;
		
		for(int i = 2; i<N+1; i++) {
			tempZero = zero;
			tempOne = one;

			zero = tempZero + one;
			one = tempZero*2 + one;
			
			// 9901을 넘어서는 경우 다시 값을 바꿔준다.
			if(zero>=9901)
				zero = zero%9901;
			if(one>=9901)
				one = one%9901;
		
		}
		System.out.println((zero+one)%9901);

	}

}
