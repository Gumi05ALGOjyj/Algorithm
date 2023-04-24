import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class 1461_도 {
	public static int N, M; // N개의 책이 존재하고 M개의 책을 넣을 수 있는 가방을 가지고 다닌다.
	
	public static Deque<Integer> minus;

	public static Deque<Integer> plus;

	public static LinkedList<Integer> temp;

	public static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		str = br.readLine().split(" ");

		temp = new LinkedList<>();

		for (int i = 0; i < N; i++)
			temp.add(Integer.parseInt(str[i]));

		minus = new ArrayDeque<>();

		plus = new ArrayDeque<>();

		Collections.sort(temp); // 정렬

		int max = Integer.MIN_VALUE;

		for (int num : temp) {
			if (num < 0)
				minus.addFirst(num);
			else
				plus.addLast(num);
			max = Math.max(max, Math.abs(num));
		}
		
		// minus와 plus deque에 내림차순으로 데이터를 넣는다.

		if (!minus.isEmpty() && !plus.isEmpty()) {
			if (Math.abs(minus.peekLast()) == plus.peekLast()) { // 최대가 동일한 경우
				if (minus.size() >= plus.size()) // 크기가 큰 부분을 기준으로 탐색한다
					calMinus();
				else
					calPlus();
			}else { // 최대가 다른 경우
				if (max == Math.abs(minus.peekLast())) {
					calMinus();
				} else if (max == Math.abs(plus.peekLast())) {
					calPlus();
				}
			}
		} else { // 하나의 deque는 비어있는 경우
			if (!minus.isEmpty() && max == Math.abs(minus.peekLast())) {
				calMinus();
			} else if (!plus.isEmpty() && max == Math.abs(plus.peekLast())) {
				calPlus();
			}
		}

		removeBook();
		
		System.out.println(result);
	}
	
	static void calPlus() {
		for (int i = 0; i < M; i++) {
			if (!plus.isEmpty()) {
				int num = plus.pollLast();
				if (i == 0)
					result += num;
			}
		}
	}
	
	static void calMinus() {
		for (int i = 0; i < M; i++) {
			if (!minus.isEmpty()) {
				int num = minus.pollLast();
				if (i == 0)
					result += Math.abs(num);
			}
		}
	}

	static void removeBook() {

		int minusSize = minus.size();
		int rest = minusSize % M;

		int idx = 0;

		while (!minus.isEmpty()) {
			idx++;

			int num = minus.pollFirst();

			if (idx % M == rest)
				result += Math.abs(num) * 2;
			else
				continue;
		}

		idx = 0;

		int plusSize = plus.size();
		rest = plusSize % M;

		while (!plus.isEmpty()) {
			idx++;

			int num = plus.pollFirst();

			if (idx % M == rest) 
				result += Math.abs(num) * 2;
			else
				continue;
		}

	}

}
