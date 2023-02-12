import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] inputs = new int [26];
	static int[] teamName = new int[26];
	static String str;
	static long result=1L;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		for(int i=0;i<3;i++) {
			teamName[str.charAt(i)-'A']+=1;
		}
		
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			str = br.readLine();
			inputs[str.charAt(0)-'A']+=1;
		}
		
		for(int i=0;i<26;i++) {
			if(teamName[i]==0) continue;
			else {
				result*=func(inputs[i], teamName[i]);
			}
		}
		
		System.out.println(result);
	}
	
	private static long func(int input, int team) {
		if(team==1) return input;
		else if(team==2) {
			return (input*(input-1L))/2L;
		}else {
			return (input*(input-1L)*(input-2))/6L;
		}
	}
}
