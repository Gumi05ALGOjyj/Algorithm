import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
	public int solution(String str1, String str2) {
       		// 1. 대문자 변경
        	str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

      		// 2. 두 글자씩 끊기 - 두글자씩 합쳐서 합친 글자가 유효하지 않으면 추가하지않고 넘어감
		for (int i = 0; i < str1.length() - 1; i++) {
			String tmp = Character.toString(str1.charAt(i)) + Character.toString(str1.charAt(i + 1));
			if ((int) tmp.charAt(0) < 65 || (int) tmp.charAt(0) > 90 || tmp.charAt(0) == ' ' || (int) tmp.charAt(1) < 65
					|| (int) tmp.charAt(1) > 90 || tmp.charAt(1) == ' ')
				continue;
			list1.add(tmp);
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			String tmp = Character.toString(str2.charAt(i)) + Character.toString(str2.charAt(i + 1));
			if ((int) tmp.charAt(0) < 65 || (int) tmp.charAt(0) > 90 || tmp.charAt(0) == ' ' || (int) tmp.charAt(1) < 65
					|| (int) tmp.charAt(1) > 90 || tmp.charAt(1) == ' ')
				continue;
			list2.add(tmp);

		}

       		// 2-1. 공집합이면 유사도 1이니까 바로 65536 반환
       		if (list1.size() == 0 && list2.size() == 0)
			return 65536;
        

		List<String> intersection = new ArrayList<>();

		// 3. 교집합 개수
		for(String tmp : list1) {
			if(list2.contains(tmp)) {
				intersection.add(tmp);
				list2.remove(tmp);
			}
		}
		
        	for(String tmp : intersection) {
			if(list1.contains(tmp)) {
				list1.remove(tmp);
			}
		}
        
       		int intersize = intersection.size(); // 교집합 개수
		int list1size = list1.size();        // list1-교집합 한 개수
		int list2size = list2.size();        // list2-교집합 한 개수
        
        	// 4. 합집합 개수
		int unionsize = list1size + list2size + intersize;
		
		
		double answer = (double) intersize / (double) unionsize;
		answer = answer * 65536;
		return (int) answer;
	}
}
