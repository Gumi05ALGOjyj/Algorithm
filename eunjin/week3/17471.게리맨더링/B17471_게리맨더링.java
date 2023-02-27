package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17471_게리맨더링 {
	static int N;
	static boolean[] isSelected;
	static int[] populations;
	static List<Integer> list[];
	static int result = Integer.MAX_VALUE; // 가장 최솟값을 저장해 줄 것이다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		populations = new int[N];
		isSelected = new boolean[N];
		list = new ArrayList[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}

		int n;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				list[i].add(Integer.parseInt(st.nextToken())-1);
			}
		}

		divide(0);

		System.out.println(result==Integer.MAX_VALUE ? -1 : result);

	}

	public static void divide(int idx) {
		if (idx == N) {
			// 종료 조건
			if (isLinked()) {
				int sumA = 0;
				int sumB = 0;
				// 인구 수 합을 구해준다.
				for (int i = 0; i < N; i++) {
					if (isSelected[i])
						sumA += populations[i];
					else
						sumB += populations[i];
				}

				result = Math.min(result, Math.abs(sumA - sumB));
			}
			return;
		}

		// 지금의 구역이 A구역에 들어 갔을 경우
		isSelected[idx] = true;
		divide(idx + 1);
		// 들어가지 않았을 경우
		isSelected[idx] = false;
		divide(idx + 1);
	}

	public static boolean isLinked() {
		boolean isChecked[] = new boolean[N];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int inA = -1;
		int inB = -1;

		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				inA = i;
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				inB = i;
				break;
			}
		}
		// 일단 A와 B에 연결된 한 점을 찾는다 한 점만 찾아주는 이유는 다 연결 되어있다면 다 순회 가능할 것이기 때문이다
		if (inA == -1 || inB == -1)
			return false; // 둘중 1개가 -1이라는 소리는 한 구역에는 아무것도 없다는 뜻이기 때문에 false를 return해준다.

		// A구역과 연결된 곳을 순회힌다.
		queue.add(inA);
		while (!queue.isEmpty()) {
			int a = queue.poll();
			isChecked[a] = true;
			for (int i = 0; i < list[a].size(); i++) {
				if (isChecked[list[a].get(i)])
					continue;

				if (!isSelected[list[a].get(i)])
					continue;
				isChecked[list[a].get(i)] = true;
				queue.add(list[a].get(i));
			}
		}

		// B구역과 연결된 곳을 순회한다.
		// A구역과 연결된 곳을 순회힌다.
		queue.clear();
		queue.add(inB);
		while (!queue.isEmpty()) {
			int a = queue.poll();
			isChecked[a] = true;
			for (int i = 0; i < list[a].size(); i++) {
				if (isChecked[list[a].get(i)])
					continue;
				
				if (isSelected[list[a].get(i)])
					continue;
				isChecked[list[a].get(i)] = true;
				queue.add(list[a].get(i));
			}
		}

		for (int i = 0; i < N; i++) {
			if (!isChecked[i])
				return false;
		}

		return true;
	}
}
