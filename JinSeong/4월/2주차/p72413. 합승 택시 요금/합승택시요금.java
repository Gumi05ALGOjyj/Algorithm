class Solution {
    static final int INF = 100_000_000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dp = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j){
                    dp[i][j]=0;
                    continue;
                }
                dp[i][j] = INF;
            }
        }
        for(int i=0; i<fares.length; i++){
            int fare[] = fares[i];
            dp[fare[0]][fare[1]] = fare[2];
            dp[fare[1]][fare[0]] = fare[2];
            
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        int answer = dp[s][a] +dp[s][b];
        
        for(int i=1; i<=n; i++){
            answer = Math.min(answer, dp[s][i]+dp[i][a]+dp[i][b]);
        }
        
        return answer;
    }
}
