import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] selected;
	static boolean[] visited;
	static int[] sensors;
	static int n, k;
	static List<Integer> list, flist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 센서 개수
		k = Integer.parseInt(br.readLine()); // 집중국 개수

		sensors = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sensors);
		
		int[] diff = new int[n-1];
		for(int i = 0; i < n-1 ; i++) {
			diff[i] = sensors[i+1]-sensors[i];
		}
		Arrays.sort(diff);
		
		
		int sum = 0;
		for(int i = 0 ; i < n-k ; i++) {
			sum+=diff[i];
		}
		System.out.println(sum);
		
		
		
	}
}
