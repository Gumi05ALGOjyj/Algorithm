import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        //오는시간이 아주 지들 맘대로야 정렬을 해버려야겠어
        Arrays.sort(timetable);
        
        //크루애들을 시간->INT로 변환해서 다 큐에 저장할꺼야ㅏ
        Queue<Integer> crew = new ArrayDeque<>();
        for(String s : timetable){
            int H = Integer.parseInt(s.substring(0,2));
            int M = Integer.parseInt(s.substring(3,5));
            //크루 도착시간 숫자로 변환해서 저장
            crew.add(H* 60 +M);
        }
        //버스타임 09:00 부터 늘려나갈 거임 
        int bustime = 9 * 60;
        
        int answertime = 0;
        
        for(int i=0; i< n; i++){ //버스는 n회 운행함
            int men=0;//i번째 버스에 현재까지 탄 사람 수
            while(men<m-1 && crew.size()!=0 && crew.peek()<=bustime){
                //i번째 버스에서
                //한 자리 빼고 태울 수 있을 때까지 태우기
                crew.poll();
                men++;
            }
            if(men == m-1){// 진짜 한 자리 빼고 다 차버림 ㄷㄷ
                if(crew.size() != 0 && crew.peek() <= bustime){
                    //아직 사람 남았는데
                    //그 사람이 이번 버스시간안에 탈 수 있는 사람임
                    //그럼 그 사람 자리를 뺏어야 되니께
                    //그 녀석 시간보다 1분 빨리 와버리기
                    answertime = crew.peek() -1;
                    crew.poll();
                }
                else{
                    //한 자리 남아 있는 상황에서
                    //남은 사람이 엄슴
                    //아니면 사람이 남았는데 
                    //이번 버스시간에 탈 수 있는 사람이 아님
                    //그럼 그냥 버스시간에 와버리기
                    //최대한 늦게 와야하구
                    //버스시간에 딱 맞게 와도 탈 수 있다고 했으니까까까
                    answertime = bustime;
                }
            } else{
                //한 자리보다 많이 남았으면 그 버스시간에 타기
                answertime = bustime;
            }
            //여기까지 오는데 answertime을 찾아도 break를 안해주는건
            //나중 시간에도 탈 수 있는 시간이 나올 수 있기 때문에
            //그냥 갈 수 있는 시간 다 돌려봐야함ㅁ
          
          
            //버스는 t분 간격으로 옴
            bustime += t;
        }
        
        //마지막까지 다 돌렸으면 다시 H랑 M찾기
        int H = answertime / 60;
        int M = answertime % 60;
        
        
        String answer = "";
        //10보다 작으면 0붙여줘야 함.. 개귀찮
        answer += H/10 == 0? "0" + H : H;
        answer+= ":";
        answer += M/10 ==0 ? "0" + M : M;
        return answer;
    }
}
