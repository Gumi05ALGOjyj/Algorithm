import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class 2212_센서 {
	public static int n,k;
	
	public static int[] sensor;
	
	public static int[] differ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		k = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		
		sensor = new int[n];
		
		for(int i =0; i<n; i++)
			sensor[i] = Integer.parseInt(str[i]);
		
		Arrays.sort(sensor);
		
		differ = new int[n-1];
		
		for(int i = 0; i<n-1; i++) {
			differ[i] = sensor[i+1] - sensor[i];
		}

		Arrays.sort(differ);
		
		//System.out.println(Arrays.toString(differ));
		
		int result = 0;
		for(int i =0; i< n-1 - (k-1) ; i++)
			result += differ[i];
		System.out.println(result);
	}
}
