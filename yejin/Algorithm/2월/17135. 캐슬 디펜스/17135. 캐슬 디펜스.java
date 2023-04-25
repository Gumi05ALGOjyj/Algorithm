import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Dot{
	int x;
	int y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class 17135_캐슬디펜스 {
    public static int N, M, D;

    public static int[][] map;

    public static int[][] copy_map;
    
    public static int archer_x1;
    
    public static List<Dot> enemy;
    
    public static List<Dot> copy_enemy;
    
    public static int[] archer; // 궁수
    
    public static boolean[] visited;
    
    public static int sum = 0, answer = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        D = Integer.parseInt(str[2]);
        
       
        map = new int[N][M];
        
        archer = new int[3]; // 궁수의 y좌표를 넣을 배열
        
        visited = new boolean[M]; // 3명의 궁수를 뽑는 경우에 어떤 자리에 있는 궁수를 뽑을 것인지
        
        enemy = new ArrayList<>(); // 적의 좌표를 저장할 리스트
        
        for(int i =0; i<N; i++) {
        	str = br.readLine().split(" ");
        	for(int j =0; j<M; j++) {
        		map[i][j] = Integer.parseInt(str[j]);
        		if(map[i][j] == 1)
        			enemy.add(new Dot(i, j));
        	}
        }
        
        
        archerChoice(0,0); // 조합으로 3명의 궁수를 뽑는다.
        
        System.out.println(answer);
        

    }
    
    // 궁수의 좌표보다 큰 경우에는 적이 성에 도착한 경우이므로 리스트에서 데이터를 삭제한다.
    static void moveEnemy() {
    	
    	for(int i =0; i<copy_enemy.size(); i++) {
    		Dot dot = copy_enemy.get(i);
    		if(archer_x1 <= dot.x)
    			copy_enemy.remove(i--);
    	}
    	
    }
    
    // 3명의 궁수가 적을 공격하는 경우
    static void attach() {
    	
    	List<Dot> tempList = new ArrayList<>();
    	
    	for(int i =0; i<3; i++) {
    		int archer_y1 = archer[i]; // 궁수의 y좌표
    		
    		Dot temp = new Dot(-1,-1);
    		
    		int deeper = Integer.MAX_VALUE;
    		
    		for(int j =0; j<copy_enemy.size(); j++) {
    			Dot dot2 = copy_enemy.get(j);
    			int differ = Math.abs(archer_x1 - dot2.x) + Math.abs(archer_y1 - dot2.y); // 적과 궁수 사이의 거리 
    			if(differ <= D) {
    				if(differ < deeper) { // 이전까지의 적과 궁수의 거리보다 더 작은 거리가 나온 경우
    					deeper = differ;
    					temp.x = dot2.x;
    					temp.y = dot2.y;
    				}else if(differ == deeper) { // 이전까지 적과 궁수의 거리에서의 최소 거리와 같은 경우
    					if(dot2.y < temp.y){ // 현재의 y값보다 작은 y값이 있다면?(왼쪽)
    						temp.x = dot2.x;
    						temp.y = dot2.y;
    					}
    				}
    			}
    		}
    		
    		// 가장 가까운 적을 찾은 경우(적을 삭제한다)
    		if(deeper != Integer.MAX_VALUE) {
    			if(copy_map[temp.x][temp.y] == 1) {
    				tempList.add(new Dot(temp.x,temp.y));
    				copy_map[temp.x][temp.y] = 0;
    				sum++;
    			} // 이전에 적을 삭제한 경우는 skip
    		}
    		
    	}
    	
    	
    	boolean ischeck = true;
    	for(int i =0; i<copy_enemy.size(); i++) {
    		Dot d1 = copy_enemy.get(i);
    		ischeck = true;
    		for(int j =0; j<tempList.size(); j++) {
    			Dot d2 = tempList.get(j);
    			if(d1.x == d2.x && d1.y == d2.y) {
    				ischeck = false;
    				break;
    			}
    		}
    		if(!ischeck) {
    			copy_enemy.remove(i--);
    		}
    	}   	
    }
    
    static void copyData() {
    	// copy_map에 map 배열의 데이터를 복사하고
    	// copy_enemy에 enemy 리스트의 데이터를 복사한다.
    	
    	copy_map = new int[N][M];
    	copy_enemy = new LinkedList<>();
    	
    	for(int i =0; i<N; i++) {
    		copy_map[i] = Arrays.copyOf(map[i],M);
    	}
    	
    	for(Dot dot : enemy) 
    		copy_enemy.add(dot);
    }
    
    
    static void archerChoice(int count,int start) {

    	if(count == 3) {
    		
    		// 3명의 궁수를 뽑았다.
    		sum = 0;
    
    		copyData(); // copy_map[][] 과 copy_enemy[][] 을 복사한다.
    		
    		archer_x1 = N; // 궁수가 움직일 x좌표
    		
    		while(!copy_enemy.isEmpty()) {

    			attach(); // 궁수가 적을 죽인다.
    			
    			archer_x1--; // 적의 자리가 아닌 궁수의 자리를 올린다.

        		moveEnemy(); // 적이 아닌 궁수가 자리를 움직이는 방식으로 표현
    		}

    		answer = Math.max(answer, sum); 

    		return;
    	}
    	
    	
    	// 조합으로 3명의 궁수를 뽑는다.
    	for(int i = start; i<M; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			archer[count] = i;
    			archerChoice(count+1, i);
    			visited[i] = false;
    		}
    	}
    }

}
