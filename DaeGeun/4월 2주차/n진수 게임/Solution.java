import java.util.*;

public class Solution {
    
    static final char[] ARR = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    static Queue<Character> charsToBeSpoken;
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int cnt=0; //튜브가 말할 리스트에 저장된 문자 수(n==t가 되면 solution 종료)
        int turn=-1; //튜브가 말할 차례인지 파악할 시간 변수. p가 1이고 turn이 0이면 튜브의 차례.
        int currentNumber=0; //n진법으로 변환하기 전 현재 수. 따로 정의한 함수를 통해 n진법으로 변환
        charsToBeSpoken = new LinkedList<>();
        
        //튜브가 말할 갯수 t를 다 채울 때까지 currentNumber를 증가시켜가며 반복.
        //바깥 반복문은 현재 수를 n진법으로 변환하여 다 말했을 경우 다음 수로 넘어가고 이 수를 큐에 나누어 넣는 반복문
        //내부 반복문은 번갈아가면서 말했을 때 튜브의 차례일 경우 결과값에 하나씩 추가하는 반복문
          
          //ex) currentNumber가 5이고 n==2이면 101의 1,0,1을 번갈아가면서 말해야함.
          //내부 반복문은 시간(turn)을 1씩 증가시켜가고, 튜브의 턴일 경우 말해야하는 목록에 추가하는 동작을 함
            //말하기 => c = charsToBeSpoken.poll() / 말할목록(리턴해줄 결과String)에 저장하기 => sb.append(c)
          //1,0,1을 번갈아가면서 다 말했다면 외부 반복문으로 빠져나감.
          //외부반복문은 currentNumber를 6으로 만들고 putCharacterInTheQueue함수를 통해 큐에 6의 이진수 1,1,0을 순서대로 삽입
      
        loop: //BF작시작
        while(true) {
            putCharactersInTheQueue(currentNumber, n);
            while(!charsToBeSpoken.isEmpty()) {
                //
                turn++;
                char c = charsToBeSpoken.poll();
                //튜브의 차례일 시 저장하고 cnt++.
                if((turn%m)+1==p) {
                    sb.append(c);
                    cnt++;
                    if(cnt==t) break loop;
                } 
            }
            currentNumber++;
        }
        return sb.toString();
    }

  
    private static void putCharactersInTheQueue(int num, int n) {
        //주어진 수 num을 n진법으로 변환해서 전역Queue에 순서대로 삽입하는 함수.
        //putCharacterInTheQueue(10,2)가 실행되면 Queue에는 1010이 {1,0,1,0}으로 삽입됨.
        //putCharacterInTheQueue(13,10)가 실행되면 Queue에는 13이 {1,3}으로 삽입됨.
        int division=num/n; //몫
        int rest=num%n; //나머지
        
        //재귀적 DFS
        //몫은 재귀함수로 다시 넘기고 나머지는 재귀함수가 다 처리된 후에 큐에 삽입
          //ex)num=130, 10진법인 경우 몫인 13은 재귀함수처리, 나머지인 0은 삽입 대기.
          //num=13, 10진법. 몫인 1은 재귀함수처리, 나머지인 3은 삽입 대기.
          //num=1, 10진법. 몫은 없고 나머지인 1은 큐에 삽입
          //대기중이던 나머지 3 삽입, 그 다음 대기중이던 0 삽입.
          //최종적으로 큐에 {1,3,0}이 저장되고 게임 참가자들은 순서대로 1, 3, 0을 말해야함
      
        if(num<n) {
            charsToBeSpoken.add(ARR[num]); //몫이 없을 경우 바로 삽입
        } else if(num>=n) {
            putCharactersInTheQueue(division, n); //몫이 있을 경우 재귀함수 호출
            charsToBeSpoken.add(ARR[rest]); //나머지는 재귀함수가 처리 된 후 삽입
        }
    }

}
