import java.util.*;
import java.io.*;

class Solution {

	Queue<Long> q1, q2;

	long sum1, sum2, target;

	public int solution(int[] queue1, int[] queue2) {
		
		q1 = new ArrayDeque<>();
		q2 = new ArrayDeque<>();
		
		Long num;
		for (int i = 0; i < queue1.length; i++) {
			num = Long.parseLong(queue1[i] + "");
			sum1 += num;
			q1.offer(num);
		}

		for (int i = 0; i < queue2.length; i++) {
			num = Long.parseLong(queue2[i] + "");
			sum2 += num;
			q2.offer(num);
		}

		if ((sum1 + sum2) % 2 != 0) // 홀수라면 정확하게 반으로 나눌 수 없으므로 -1을 반환한다.
			return -1;

		target = (sum1 + sum2) / 2; // 각 큐에 들어가는 숫자의 합이 target이 나와야한다.

		int count = 0, len = (q1.size() + q2.size()) * 2;

		while (len-- != 0) {
			if (sum1 == target) { // 각 큐의 합이 target인 경우
				return count;
			}

			if (sum1 > target) { // q1에 들어있는 원소의 합이 q2에 들어있는 원소의 합보다 큰 경우
				num = q1.poll(); 
				q2.offer(num);
				sum1 -= num;
				sum2 += num;
			} else if (sum2 > target) {  // q2에 들어있는 원소의 합이 q1에 들어있는 원소의 합보다 큰 경우
				num = q2.poll();
				q1.offer(num);
				sum1 += num;
				sum2 -= num;
			}
			
			count++; // 큐에서 값을 빼고 넣는 작업을 했으므로 count를 1증가시킴

		}
		
		return -1; // len만큼 반복했지만 각 큐에 들어있는 원소의 합이 target으로 나올 수 없는 경우
	}
}
