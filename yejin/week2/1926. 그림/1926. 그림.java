import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
6 5
1 1 0 1 1
0 1 1 0 0
0 0 0 0 0
1 0 1 1 1
0 0 1 1 1
0 0 1 1 1 
*/

class Dot{
	int x;
	int y;

	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	public static int n,m;
	
	public static int[][] map;
	
	public static boolean[][] visited;
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	public static int count = 0;
	
	public static int S = Integer.MIN_VALUE;
	
	public static int cnt = 0;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
       
        String[] str = br.readLine().split(" ");
        
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        
        map = new int[n][m];
        visited = new boolean[n][m];
        
        for(int i =0; i<n; i++) {
        	str = br.readLine().split(" ");
        	for(int j =0; j<m; j++) 
        		map[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i =0; i<n; i++) {
        	for(int j = 0; j<m; j++) {
        		if(map[i][j] != 0 && !visited[i][j]) {
        			cnt = 0;
        			visited[i][j] = true;
        			
            		DFS(new Dot(i,j));
            		
            		count++;
        		}
        	}
        }
        
        if(S == Integer.MIN_VALUE)
        	System.out.println(0+"\n"+0);
        else
        	System.out.println(count+"\n"+S);
        


    }
    
    static void DFS(Dot dot) {
    	
    	for(int i = 0; i<4; i++) {
    		int nextX = dot.x + dx[i];
    		int nextY = dot.y + dy[i];
    		
    		if(0<= nextX && nextX < n && 0 <= nextY && nextY < m) { // map[][] 안에 존재하는 x,y 좌표인 경우
    			if(!visited[nextX][nextY] && map[nextX][nextY] == 1) {
    				visited[nextX][nextY] = true;
    				cnt++;
    				DFS(new Dot(nextX, nextY));
    			}
    		}
    	}

    	S = Math.max(S, (cnt+1));

    }
    
  
    
}
