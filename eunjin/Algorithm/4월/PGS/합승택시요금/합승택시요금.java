import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int result =100_000*200;
        int[][] dist = new int[n+1][n+1];
        for(int i=0;i<n+1;i++)
            Arrays.fill(dist[i],100_000*200);
        for(int i=0;i<n+1;i++)
            dist[i][i] = 0;
        
        //input받은 값 저장
        for(int i=0;i<fares.length;i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int adj = fares[i][2];
            
            dist[from][to] = adj;
            dist[to][from] = adj;
        }
        
        //플로이드 워샬 돌리기
         for(int k=1;k<n+1;k++){
            for(int i=1;i<n+1;i++){
                for(int j=1;j<n+1;j++){
                    if(j==i) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                   
                }
            }
        }
        
       
        int route1 = dist[s][a]+dist[a][b];
        int route2 = dist[s][b]+dist[b][a];
        int route3 = dist[s][a]+dist[s][b];
        
        result = Math.min(route1,Math.min(route2, route3));
        
        for(int k=1;k<n+1;k++){
            //둘은 s에서 같이 출발한다. 
            if(k==s||k==a||k==b) continue;
            result = Math.min(result, dist[s][k]+dist[k][a]+dist[k][b]);
        }
        
        return result;
    }
}
