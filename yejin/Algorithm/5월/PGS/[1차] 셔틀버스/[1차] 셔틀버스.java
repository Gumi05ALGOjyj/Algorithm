import java.util.*;
import java.io.*;


class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        // 셔틀 운행 횟수 n, 셔틀 운행 간격 t, 한 셔틀에 탈 수 있는 최대 크루 수 m, 크루가 대기열에 도착하는 시각을 모은 배열 timetable
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int[] keys;
        
        List[] person;
        
        String[] tempStr;
        for(String str : timetable){
            tempStr = str.split(":"); // 시간을 ":"을 기준으로 분리한다.
            int time = 60 * Integer.parseInt(tempStr[0]) + Integer.parseInt(tempStr[1]); // 시간을 정수형으로 만들어서 큐에 넣는다.
            pq.offer(time);
        }
        
        keys = new int[n]; // 버스의 시간표
        
        person = new ArrayList[n]; // keys[i]에 들어갈 수 있는 사람의 시간

        
        int total = 0, hour = 9, minute = 0;
        for(int i =0; i<n; i++){
            person[i] = new ArrayList<Integer>();
            
            hour = hour + minute/60; 
            
            minute = minute%60; 
            
            total = hour*60 + minute;
            
            keys[i] = total; 
            
            minute += t; 
        }
    
        
        while(!pq.isEmpty()){
            int num = pq.poll();

            for(int i = 0; i<n; i++){
                if(keys[i] >= num && person[i].size() < m){ // 버스의 시간표보다 크거나 같고 m보다 작아서 사람이 더 들어갈 수 있는 경우
                    person[i].add(num);
                    break;
                }
            }
        }


        if(person[n-1].size() < m){ // 마지막 버스의 시간에 사람이 더 들어갈 수 있다.
                return convertStr(keys[n-1]);
        }
            
        if(person[n-1].size() == 1) 
            return convertStr((int)person[n-1].get(0)-1);
            
        if((int)person[n-1].get(m-1) == (int)person[n-1].get(m-2)) // 마지막에 있는 사람들의 수가 같은 경우에는 그 사람의 시간보다 1분 먼저 콘이 도착해야한다.
            return convertStr((int)person[n-1].get(m-1)-1);

        return convertStr((int)person[n-1].get(m-2));   // 마지막에 있는 사람들의 수가 다른 경우에는 뒤에서 두번째에 존재하는 사람과 같은 시간에 콘이 도착해야한다.

    }
    
    // 시간을 문자열로 변환해서 반환한다.
    public String convertStr(int time){
        int hour = time/60;
        int minute = time%60;
        
        String answer = "";
        
        if(hour < 10)
            answer += "0";
        answer += hour;
        
        answer += ":";
        
        if(minute < 10)
            answer += "0";
        answer += minute;
        
        return answer;
    }
}
