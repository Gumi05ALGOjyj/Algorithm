import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SensorIns_bj2122 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N, K;
		ArrayList<Integer> sensors = new ArrayList<>();;
		ArrayList<Integer> dis = new ArrayList<>();;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		if(K>=N) {
			System.out.println(0);
			return;
		}
		st = new StringTokenizer(br.readLine());
		
		{
			int n;
			for(int i=0;i<N;i++) {
				n = Integer.parseInt(st.nextToken());
				sensors.add(n);
			}
		}
		sensors.sort((o1,o2)->o1-o2);

		for(int i=0;i<sensors.size()-1;i++) {
			dis.add(sensors.get(i+1)-sensors.get(i));
		}
		
		dis.sort((o1,o2)->o1-o2);

		int sum=0;
		for(int i=0;i<dis.size()-K+1;i++) {
			sum+=dis.get(i);
		}
		
		System.out.println(sum);
	}

}
