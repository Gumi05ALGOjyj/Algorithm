import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {

        //------------크르원들의 정보를 분으로 환산해서 list에 저장 -----------
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<timetable.length;i++){
            list.add(hourToMin(timetable[i]));
        }
        
        Collections.sort(list);

        //9시는 540분이다!!
        int suttleTime = 540;   //현재 버스 시간을 저장해줄 변수
        int index=0;    //현재 탈 놈
        int lastTime =0;    //가장 끝 시간
        int count =0 ;
        for(int i=0;i<n;i++){
            //셔틀버스는 n대 있으니까 이렇게 해쥼
            count=0;
            
            if(index>=list.size()) break;
            
            while(count<m){
                if(suttleTime>=list.get(index)){
                    lastTime = list.get(index);
                    index++;
                    count++;
                    
                    if(index>=list.size()) break;
                }else{
                    break;
                }
                
              
            }
            
            suttleTime +=t;
        }
        
        //마지막 버스에 자리가 있다면?
        if(count<m){
            return intToString(suttleTime-t);
        }else{
            //마지막 버스에 자리 없음ㅋ 한놈보다는 일찍와ㅑ앟
            return intToString(lastTime-1);
        }
      
    }
    
    //String형태의 시간을 분 형태로 바꿔서 리턴 시켜주는 함수
    public int hourToMin(String timetable){
        //흠...일단 split으로 시와 분을 분리해준다.
        String [] time = timetable.split(":");
        String hour = time[0];
        String min = time[1];
        
        int h=Integer.parseInt(hour);
        int m = Integer.parseInt(min);
        
        int result =0;
        result+=(h*60);
        result+=(m);
        
        return result;
    }
    
    public String intToString(int time){
        String hour = String.valueOf(time/60);
        String min = String.valueOf(time%60);
        
        if(hour.length()<2) hour = "0"+hour;
        if(min.length()<2) min = "0"+min;
        
        return hour+":"+min;
        
    }
}
