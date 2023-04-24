import java.util.*;
class Solution {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		HashMap<String, Integer> selects = new HashMap<String, Integer>();
		HashSet<String> allGems = new HashSet<String>();

		int length = gems.length;
		answer[0] = 1;
		answer[1] = length;

        allGems.addAll(Arrays.asList(gems));
        
		int size = allGems.size();

		int start = 0;
		for (int end = 0; end < length; end++) {
			selects.put(gems[end], selects.getOrDefault(gems[end], 0) + 1);

			while (selects.getOrDefault(gems[start], 0) > 1) {
				selects.replace(gems[start], selects.get(gems[start]) - 1);
                System.out.println(selects.get(gems[start]));
				start++;
			}

			if (selects.size() == size && answer[1] - answer[0] + 1 > end - start + 1) {
				answer[1] = end + 1;
				answer[0] = start + 1;
			}
		}

		return answer;
	}
}
