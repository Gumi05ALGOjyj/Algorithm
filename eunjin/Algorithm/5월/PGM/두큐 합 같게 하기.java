import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int count=0;   //count를 세어 저장해줄 변수
        
        long sum1=0;
        long sum2=0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        //sum1에 큐1의 합을 저장해주고 q1으로 진짜 큐로 만들어준다.
        for(int i=0;i<queue1.length;i++){
            sum1+=queue1[i];
            q1.add(queue1[i]);
        }
        
        for(int i=0;i<queue2.length;i++){
            sum2+=queue2[i];
            q2.add(queue2[i]);
        }
        
        long target = (sum1+sum2)/2; //두 큐가 만들어야 할 값을 정해준다
        
        int maxCount =( queue1.length + queue2.length)*3;
        
        while(sum1!=target){
            //종료조건 : 둘중의 하나라도 해당 값을 만족한 경우.
            
            if(count>maxCount) return -1;
            
            if(sum1>sum2){
                //q1의 값을 하나 뽑아서 q2에 넣어준다.
                int num = q1.poll();
                sum1-=num;
                sum2+=num;
                q2.add(num);
            }
            else{
                int num = q2.poll();
                sum2-=num;
                sum1+=num;
                q1.add(num);
            }
            
            count++;
        }
        
        return count;
        
    }
}
