import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		int N, C;
		int result=-1;
		int[] homes;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		homes = new int[N];
		for(int i=0;i<N;i++) {
			homes[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(homes);
		
		int left, right;
		left=1; 
		right=homes[N-1]-homes[0];
		
		while(left<=right) {
			int mid, cnt, index;
			cnt=1;
			mid=(right+left)/2;
			
			index=0;
			for(int i=1;i<N;i++) {
				if(homes[i]-homes[index]>=mid) {
					//설치
					index=i;
					cnt++;
					if(cnt>=C) {
						result=mid;
						left=mid+1;
						break;
					} 
				}
			}

			if(cnt<C) {
				right=mid-1;
			}
		}
		System.out.println(result);
	}

}
