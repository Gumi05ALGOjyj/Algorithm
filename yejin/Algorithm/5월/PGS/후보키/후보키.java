import java.io.*;
import java.util.*;

class Solution {

	Set<String> set;

	Set<String> filter;

	boolean[] visited;

	int N, M, target;

	public int solution(String[][] relation) {

		N = relation.length; // 행
		M = relation[0].length; // 열

		set = new HashSet<String>();

		filter = new HashSet<>();

		// 1~M개로 target을 뽑는다.(조합)
		for (int i = 1; i <= M; i++) {
			visited = new boolean[M];

			target = i;
			choiceNumber(0, 0, relation);
		}

		return filter.size();
	}
    
	public void choiceNumber(int count, int start, String[][] relation) {

		if (count == target) {
			set = new HashSet<String>();

			String str = makeStr();
            
            
            // 이미 만들어진 문자열인 경우에는 메소드를 반환한다.
			if (filter.contains(str))
				return;
            
            // (1) 유일성을 만족하는지 확인한다.
			StringBuilder sb;
			for (int i = 0; i < N; i++) { // 행
				sb = new StringBuilder();
				for (int j = 0; j < M; j++) { // 열
					if (visited[j])
						sb.append(relation[i][j]+" ");
				}
				set.add(sb.toString());
			}
            // Ex) visited[1] = true, visited[2] = true로 이름과 전공이 선택된 경우라면
            // sb에는 이름+전공 형식으로 들어간다. ex) 이름이 ryan이고 전공이 music이라면? ryan+music

			if (set.size() == N) {
                String s;
				int sOneCount = 0, sameCount = 0;
                // (2) 최소성을 만족하는지 확인한다.
				Iterator<String> iter = filter.iterator();
				while (iter.hasNext()) {
					s = iter.next();
					sOneCount = 0;
					sameCount = 0;
					for (int i = 0; i < M; i++) {
						if (str.charAt(i) == s.charAt(i) && str.charAt(i) == '1') {
							sameCount++;
						}
						if (s.charAt(i) == '1')
							sOneCount++;
					}

					if (sameCount == sOneCount) // 즉, s가 1000이고 str이 1100 이면? sameCount = 1이고 sOneCount = 1이므로 메소드를 종료한다.
						return;
				}

				filter.add(str); // 최소성을 만족하기 때문에 filter에 str을 넣는다.

			}

			return;
		}
        
        // 조합
		for (int i = start; i < M; i++) {
			if (!visited[i]) {

				visited[i] = true;

				String str = makeStr();

				if (!filter.contains(str))
					choiceNumber(count + 1, start + 1, relation);

				visited[i] = false;
			}
		}
	}
    
    // 선택한 컬럼은 1, 선택하지 않은 컬럼은 0으로 문자열을 생성한다.
	public String makeStr() {
		String str = "";
		for (int j = 0; j < M; j++) {
			if (visited[j])
				str += "1";
			else
				str += "0";
		}
		return str;
	}

}
