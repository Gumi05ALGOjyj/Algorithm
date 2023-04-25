import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class OnlineSelling_bj1246 {

	public static void main(String[] args) throws IOException {
		int N, M, resultSum=0, resultPrice=0;
		Integer[] p;
		int[] pCnt=new int[1000001];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new Integer[M];
		
		{
			int tmp;
			for(int i=0;i<M;i++) {
				tmp = Integer.parseInt(br.readLine());
				p[i] = tmp;
				pCnt[tmp]++;
			}
		}
		
		Arrays.sort(p,Collections.reverseOrder());
//		System.out.println(Arrays.toString(p));
		{
			int cntAcc=0;
			int curP, prevP;
			prevP = 1000001;
			for(int i=0;i<N&&i<M;i++) {
				curP=p[i];
				if(curP==prevP) continue;
				cntAcc+=pCnt[curP];
				if(resultSum<cntAcc*curP) {
					resultSum = cntAcc*curP;
					resultPrice = curP;
				}
				prevP=curP;
			}
		}
		
		System.out.println(resultPrice+" "+resultSum);
		
	}

}
