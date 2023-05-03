import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        
        //정답을 찾아가는 이분탐색
        int left=0, right=200_000_000, mid=(left+right)/2;
        while(left<=right){
            if(canGo(mid,stones,k)){
                left=mid+1;
                mid=(left+right)/2;
            } else{
                right=mid-1;
                mid=(left+right)/2;
            }
        }
        return mid;
    }
    
    //이분탐색의 특정값 mid가 건너게 할 수 있는 값인지 계산하는 메소드
    //(징검다리의 숫자-mid)<0 이면 streak++한다.
    //streak가 k를 넘으면 mid명의 미미즈를 건너게 할 수 없다.
    public static boolean canGo(int num, int[] stones,int k){
        int streak = 0;
        for(int i=0;i<stones.length;i++){
            if(stones[i]-num<0) {
                streak++;
                if(streak==k) return false;
            } else{
                streak=0;
            }
        }
        return true;
    }
}
