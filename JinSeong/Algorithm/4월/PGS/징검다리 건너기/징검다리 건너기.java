class Solution {
   public int solution(int[] stones, int k) {
        int min = 0;
        int max = 200000000;
        int result = 0;
        // 이분 탐색으로 풀기
        // 이분 탐색으로 건널 수 있는 값(mid) 찾기
        //예를 들어 k=3일때,
        //3 2 4 이렇게 있으면 얘네는 4번후에 못지나감
        // 이 횟수를 mid로 찾기
        while(min <= max) {
            int mid = (min + max) / 2;
            if(check(mid, k, stones)) {
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }
    
    public boolean check(int mid, int k, int[] stones) {
        int count = 0;
        for(int i = 0; i < stones.length; i++) {
            //mid 값 보다 작은 게 k만큼 있으면 실패
            //큰게 있다면 카운트 초기화
            if(stones[i] < mid) {
                count++;
                if(count >= k) return false;
            } else count = 0;
        }
        //안 걸렸다면 그냥 통과!
        return true;
    }
}
