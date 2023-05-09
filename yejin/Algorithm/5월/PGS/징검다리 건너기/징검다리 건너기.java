import java.util.*;
import java.io.*;

class Solution {
	// 징검다리를 건널 수 있는 사람 수를 기준으로 이분탐색을 진행한다.

	public int solution(int[] stones, int k) {
		int answer = 0, min = 0, max = 200000000, mid = 0; // max는 stones[] 배열 원소의 최대, mid는 징검다리를 건널 수 있는 사람의 수

		while (min <= max) {
			mid = (min + max) / 2;
			if (rangeCheck(mid, stones, k)) { // 현재 징검다리에서 mid명의 사람이 건널 수 있는지 확인한다.
				min = mid + 1; // mid명의 사람들이 징검다리를 건널 수 있으므로 min을 늘려준다.
				answer = mid;
			} else {
				max = mid - 1;
			}
		}
		return answer;
	}

	public boolean rangeCheck(int target, int[] stones, int k) {
		int count = 0;
		for (int i = 0; i < stones.length; i++) {
			if (stones[i] < target) {
				count++;
				if (count >= k) // k개 이상의 칸을 지나는 경우에 target명의 사람은 건널 수 없으므로 false를 반환한다.
					return false;
			} else if (stones[i] >= target) {
				count = 0;
			}
		}
		return true;
	}

}
