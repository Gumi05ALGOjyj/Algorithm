import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 달걀개수
		m = Integer.parseInt(st.nextToken()); // 사람

		int[] eggsMoney = new int[m];
		for (int i = 0; i < m; i++) {
			eggsMoney[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(eggsMoney);

		List<Integer> list = new ArrayList<>();

		if (n > m || n == m) {
			for (int i = 0; i < m; i++) {
				list.add(eggsMoney[i] * (m - i));
			}

			int max = Collections.max(list);
			for (int i = 0; i < m; i++) {
				if (list.get(i) == max)
					System.out.print(eggsMoney[i] + " ");
			}

			System.out.println(max);
		}

		if (n < m) { // 계란 < 사람
			for (int i = m - 1; i >= m - n; i--) {
				list.add(eggsMoney[i] * (m - i));
			}

			int max = Collections.max(list);
			for (int i = 0; i < m; i++) {
				if (list.get(i) == max) {
					System.out.print(eggsMoney[m - (i + 1)] + " ");
					break;
				}

			}

			System.out.println(max);

		}
	}
}
