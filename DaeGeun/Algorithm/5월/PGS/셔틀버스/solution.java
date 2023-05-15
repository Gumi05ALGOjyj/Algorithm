import java.util.*;
class Solution {
    static int arriveIndex;
    public String solution(int n, int t, int m, String[] timetable) {
        String[] busTimeTable;
        int[] canTakeOff;
        canTakeOff = new int[n];
        Arrays.fill(canTakeOff,m);
        arriveIndex=0;
        
        //1) 셔틀의 도착시간 배열 생성
        busTimeTable = new String[n];
        String busTime = "09:00";
        for(int i=0;i<n;i++){
            busTimeTable[i] = calcTime(busTime, i, t);
        }
        
        //2) 사람들을 실어보냄
        for(int i=0;i<n;i++){
            takeOff(t,i,busTimeTable[i],timetable,canTakeOff);
        }
        
        //3-1) 막차에 자리가 있다면 막차에 탑승
        if(canTakeOff[n-1]>0) return busTimeTable[n-1];
        
        //3-2) 자리가 없다면 마지막에 탄사람 -1분에 줄서기 (야비하노;)
        return calcTime(timetable[arriveIndex-1],1,-1);
    } //main 끝
    
    private static String calcTime(String time, int n, int t){
        int H,M;
        String resultH, resultM;
        H = Integer.parseInt(time.split(":")[0]);
        M = Integer.parseInt(time.split(":")[1]);

        M += t*n;
        H += M/60;
        M %= 60;
        
        //빼기 고려
        if(M<0){
            M+=60;
            H--;
        }
        
        resultM=String.valueOf(M);
        resultH=String.valueOf(H);

        if(M<10) resultM = "0" + resultM;
        if(H<10) resultH = "0" + resultH;
        return resultH+":"+resultM;
    } //calcTime 끝
    
    private static void takeOff(int t, int i, String busTime, String[] timetable, int[] canTakeOff){
        String crewArrival;
        Arrays.sort(timetable);
        while (canTakeOff[i]>0){
            if(arriveIndex==timetable.length) return;
            crewArrival = timetable[arriveIndex];
            //셔틀보다 일찍 오면 셔틀 탑승
            if(crewArrival.compareTo(busTime) <= 0){
                canTakeOff[i]--;
                arriveIndex++;
            } else{ 
                //제일 빠른 사람이 셔틀보다 늦게오면 아무도 못탐
                return;            
            }
        }
    } //takeOff 끝
}

/*
테스트 1 〉	통과 (9.12ms, 81.5MB)
테스트 2 〉	통과 (12.34ms, 77.2MB)
테스트 3 〉	통과 (8.85ms, 76.1MB)
테스트 4 〉	통과 (10.80ms, 73.9MB)
테스트 5 〉	통과 (11.58ms, 82.2MB)
테스트 6 〉	통과 (13.09ms, 71.3MB)
테스트 7 〉	통과 (12.62ms, 78MB)
테스트 8 〉	통과 (13.22ms, 76.2MB)
테스트 9 〉	통과 (10.48ms, 76.4MB)
테스트 10 〉	통과 (11.93ms, 94.3MB)
테스트 11 〉	통과 (8.77ms, 74.2MB)
테스트 12 〉	통과 (11.88ms, 83.5MB)
테스트 13 〉	통과 (14.84ms, 79.6MB)
테스트 14 〉	통과 (15.03ms, 89.9MB)
테스트 15 〉	통과 (16.84ms, 73.4MB)
테스트 16 〉	통과 (13.96ms, 75.8MB)
테스트 17 〉	통과 (10.07ms, 76.8MB)
테스트 18 〉	통과 (12.40ms, 78MB)
테스트 19 〉	통과 (10.54ms, 79.8MB)
테스트 20 〉	통과 (11.35ms, 82.5MB)
테스트 21 〉	통과 (15.84ms, 86.6MB)
테스트 22 〉	통과 (10.99ms, 76.5MB)
테스트 23 〉	통과 (9.71ms, 76.3MB)
테스트 24 〉	통과 (19.51ms, 81.2MB)
*/
