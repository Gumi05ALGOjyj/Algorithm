/*
    1. 각 큐가 늘고 줄어드는것을 배열로 구현.(시간이 더 소요되는 가변길이 큐 대신 넉넉한 크기의 배열 두개 선언)
    2. 합이 큰 큐에서 pop해서 합이 작은 큐에 삽입. answer++
    3. 큐 공간을 다 쓰거나 목표하는 값이 될 때까지 반복
*/

import java.util.*;

class Solution {
   
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        int[] que1, que2;
        int left1, left2, right1, right2;
        
        //변수 선언·초기화
        que1 = new int[(queue1.length+queue2.length)*2]; //더하기만 하면 충분할줄 알았는데 2를 곱해줘야 오답이 줄음
        que2 = new int[(queue1.length+queue2.length)*2];

        left1 = 0;
        left2 = 0;
        right1 = queue1.length;
        right2 = queue2.length;
        double sum1=0, sum2=0;
        
        //sum1,2를 구하면서 que1,2기채우기
        for(int i=0;i<queue1.length;i++){
            sum1+=queue1[i];
            que1[i]=queue1[i];
        }
        for(int i=0;i<queue2.length;i++){
            sum2+=queue2[i];
            que2[i]=queue2[i];
        }
      
      
        // if((sum1+sum2)%2>=1){ //합이 홀수인경우 불가능판정. 필요할줄 알았는데 결과에 영향 X
        //     return -1;
        // } 
        //초기화 끝

        int tmp;
        while(right1<que1.length && right2<que2.length){ //큐를 다 채울때까지 반복
            answer++; //연산 횟수 증가
            if(sum1>sum2){ //que1의 합이 더 크면 que1의 첫번째 원소를 빼서 que2의 마지막에 삽입
                tmp = que1[left1];;
                
                que1[left1]=0;
                sum1-=tmp;
                left1++;
                
                que2[right2]=tmp;
                sum2+=tmp;
                right2++;
            } else if(sum2>sum1){ //반대.
                tmp = que2[left2];
                
                que1[right1]=tmp;
                sum1+=tmp;
                right1++;
                
                que2[left2]=0;
                sum2-=tmp;
                left2++;
            } else {
                return answer;
            }
        }
        return -1;
    }
}



