
public class Solution {
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] map = new int[n][n];
        for(int i=0;i<fares.length;i++){
        	int from = fares[i][0]-1;
        	int to = fares[i][1]-1;
        	int fare = fares[i][2];
            map[from][to] = fare;
            map[to][from] = fare;
        } // 입력 끝
        
        //플로이드 워셜 진행
        for(int k=0;k<n;k++) {
        	for(int i=0;i<n;i++) {
        		for(int j=0;j<n;j++) {
        			if(i!=j && i!=k && j!=k) {
        				int A=map[i][j]==0?Integer.MAX_VALUE:map[i][j];
        				int B=(map[i][k]>0&&map[k][j]>0)?map[i][k]+map[k][j]:Integer.MAX_VALUE;
        				if(A==Integer.MAX_VALUE && B==Integer.MAX_VALUE) continue;
        				map[i][j] = Math.min(A, B);
        			}
        		}
        	}
        }

        //세 가지 CASE로 나누어 최소요금 계산. 
        int T,A,B; //T는 경유지를 가는 비용, A와 B는 경유지에서 해당 지점까지 가는 비용
        for(int i=0;i<n;i++) {
        	T = map[s-1][i];
        	A = map[i][a-1];
        	B = map[i][b-1];
        	//CASE 1. 경유지를 거쳐서 A와 B로 가는 경우. 이 경유지가 A나 B가 아닌 경우
        	if(i!=s-1 && i!=a-1 && i!=b-1) {
        		if(T==0 || A==0 || B==0) continue;
        		answer = Math.min(answer,T+A+B);
        	} 
        	//CASE 2. A를 경유하거나 B를 경유하는 경우
        	else if(i==a-1 || i==b-1) { 
        		if(T==0 || (A==0 && B==0)) continue; //A를 경유할 경우 A==0이다. 이 때 B로 가는 길은 있어야한다. 반대 또한 마찬가지.
        		answer = Math.min(answer,T+A+B);
        	}
        }
        //CASE 3. 합승하지 않고 따로 길을 가는 경우
        A=map[s-1][a-1];
        B=map[s-1][b-1];
        if(A!=0 && B!=0) answer = Math.min(answer, map[s-1][a-1]+map[s-1][b-1]);
        
        return answer;
    }
}
