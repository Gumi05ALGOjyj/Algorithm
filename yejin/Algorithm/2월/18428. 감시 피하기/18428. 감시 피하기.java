import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class Dot{
	int x;
	int y;
	
	public Dot() {}
	
	public Dot(int x,int y) {
		this.x=x;
		this.y=y;
	}
}

public class Main {
	
	public static int N;
	
	public static char[][] map;
	
	public static boolean[][] isSelected;
	
	public static ArrayList<Dot> teacher;
	
	public static int dx[] = {-1,1,0,0};
	public static int dy[] = {0,0,-1,1};
	
	public static boolean answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		isSelected = new boolean[N][N];
		
		teacher = new ArrayList<>();
		
		String[] str;
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<N; j++) {
				map[i][j] = str[j].charAt(0);
				if(map[i][j] == 'T') {
					teacher.add(new Dot(i,j));
				}
			}
		}
		
		obstacle(0, 0, 0);
		
		System.out.println(answer? "YES" : "NO");
		
	}
	
	// 3개의 장애물을 map[][] 에 설치하는 메소드
	static void obstacle(int count, int r, int c) {
		if(count == 3) { // 3개의 장애물을 설치함
			
			if(isCheck())  // 장애물을 설치할 수 있는 경우
				answer = true;

			return;
		}
		
		for(int i = r; i<N; i++) {
			int j;
			if(i == r) 
				j = c;
			else
				j = 0;
			for(; j<N; j++) {
				if(map[i][j] == 'X') {
					map[i][j] = 'O';
					obstacle(count+1, i, j);
					map[i][j] = 'X';
				}
			}
		}
	}
	
	// 선생님(T) 이 상,하,좌,우 모두 체크해서 장애물이 나타나기 전에 학생을 발견하는지 체크하는 메소드
	static boolean isCheck() {
		for(Dot dot : teacher) {
			for(int i =0; i<4; i++) {
				Dot temp = new Dot(dot.x,dot.y);
				
				temp.x += dx[i];
				temp.y += dy[i];
				
				while(rangeCheck(temp.x,temp.y)) {
					
					if(map[temp.x][temp.y] == 'O')
						break;
					else if(map[temp.x][temp.y] == 'S')
						return false;
					
					temp.x += dx[i];
					temp.y += dy[i];
				}
			}
		}
		return true;
	}
	
	// x와 y의 좌표가 배열의 범위 안에 존재하는지 체크하는 메소드
	static boolean rangeCheck(int x, int y) {
		if(0<=x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

}
