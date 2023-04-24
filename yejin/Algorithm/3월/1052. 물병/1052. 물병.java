import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;


class Data{
	int key;
	int value;
	
	public Data(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

public class Main {
	public static int N,K;
	
	public static Queue<Data> queue;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
	
		
		for(int i = N; ; i++) {
			if(calculator(i) <= K) {
				System.out.println(i-N);
				return;
			}
		}
		
	}
	
	static int calculator(int k) {
		queue = new ArrayDeque<>();
		
		queue.offer(new Data(1,k)); // 초기에 1을 세팅함
		
		int total = 0;
		
		while(!queue.isEmpty()) {
			
			int len = queue.size();

			for(int i = 0; i<len; i++) {
				Data data = queue.poll();
				if(data.value == 1) {
					total++;
				}else {
					queue.offer(new Data(data.key*2, data.value/2));
					if(data.value % 2 != 0) {
						queue.offer(new Data(data.key, data.value%2));
					}
				}
			}
		}
		
		return total;
		
	}
}
