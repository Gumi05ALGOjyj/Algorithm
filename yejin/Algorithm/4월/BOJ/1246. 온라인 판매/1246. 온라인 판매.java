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


public class 1246_온라인판매 {
	public static int n, m; // 달걀의 수, 고객의 수
	
	public static int[] person;
	
	public static int money, maxMoney;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		person = new int[m];
		
		for(int i =0; i<m; i++)
			person[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(person); // 오름차순
		
		money = person[0];
		
		maxMoney = person[0] * m;
		
		for(int i = 1; i<m; i++) {
			int total = 0;
			
			if(person[i]-1 != person[i-1]) { // 이전에 계산한 값인 경우에는 skip하도록 만들기 위한 if문
				
				total = (person[i]-1) * possibleEggs(m-i-1);
				
				if(maxMoney < total) {
					maxMoney = total;
					money = person[i]-1;
				}
			}

			total = person[i] * possibleEggs(m-i);
			
			if(maxMoney < total) {
				maxMoney = total;
				money = person[i];
			}
		}
		
		System.out.println(money+" "+maxMoney);
		
	}
	
	static int possibleEggs(int len) {
		if(len > n) // 현재 달걀의 수보다 큰 경우
			return n;
		else // 현재 달걀의 수
			return len;
	}
	
	
}
