import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
	public static int N, M; // 사람의 수 N, 파티의 수 M

	public static Map<Integer, ArrayList<Integer>> people, room;

	public static Queue<Integer> queue;

	public static boolean[] roomVisited, peopleVisited;

	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		result = M;
		queue = new ArrayDeque<>();
		
		// key번의 사람은 value에 존재하는 리스트의 숫자들의 방에 존재한다.
		people = new HashMap<>();
		
		// key번의 방에 value에 존재하는 리스트의 숫자의 사람들이 방에 존재한다.
		room = new HashMap<>();
		
		// 큐에 방문한 적이 있는 사람들을 체크하기 위한 배열
		peopleVisited = new boolean[N + 1];
		
		// 방에 방문한 적이 있는지 체크하기 위한 배열
		roomVisited = new boolean[M + 1];

		for (int i = 0; i < N + 1; i++) {
			people.put(i, new ArrayList<>());
		}

		for (int i = 0; i < M + 1; i++) {
			room.put(i, new ArrayList<>());
		}

		str = br.readLine().split(" "); 

		// 초기에 큐를 세팅한다.
		int num;
		for (int i = 1; i < str.length; i++) {
			num = Integer.parseInt(str[i]);
			queue.add(num);
			peopleVisited[num] = true; // 큐를 방문했으므로 true로 설정한다.
		}

		for (int i = 1; i < M + 1; i++) {
			str = br.readLine().split(" ");
			for (int j = 1; j < str.length; j++) {
				num = Integer.parseInt(str[j]);
				people.get(num).add(i); // num번의 사람은 i번째 방에 존재한다.
				room.get(i).add(num);
			}
		}

		int number;
		while (!queue.isEmpty()) {
			number = queue.poll();
			for (int num1 : people.get(number)) {
				if (!roomVisited[num1]) { // 방문한 적이 없는 방인 경우
					roomVisited[num1] = true;
					result--;
					for (int num2 : room.get(num1)) {
						if (!peopleVisited[num2]) { // 큐에 들어간 적이 없는 사람인 경우
							peopleVisited[num2] = true;
							queue.offer(num2);
						}
					}
				}
			}
		}

		System.out.println(result);

	}
}
