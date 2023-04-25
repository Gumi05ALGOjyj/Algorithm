import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class num17136색종이붙이기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;

    //각 색종이는 5장씩
    static int[] paper = {0,5,5,5,5,5};
    public static void main(String[] args) throws IOException {
        //10 X 10
        arr = new int[10][10];
        for(int i=0; i<10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++){
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0,0);
        //최소가 갱신되지 않았다면 -1
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(min);
        }
    }
    private static void dfs(int x, int y, int cnt){
        if(x>=9 && y>9){ //맨 끝점까지 봤으면 결과 값 반환
            min = Math.min(min,cnt);
            return;
        }
        if(y>9){
            //가로줄 다봤으면 아래줄 첫 인덱스부터 탐색
            dfs(x+1,0,cnt);
            return;
        }
        if(arr[x][y]==1){//색종이를 붙일 수 있는지
            //제일 큰 색종이부터 크기를 줄여가며 1까지 탐색
            for(int i=5; i>=1; i--){
                //5개 다 썼는지랑 정사각형인지 체크
                if(paper[i]>0 && square(x,y,i)){
                    attach(x,y,i,0);
                    paper[i]--;
                    dfs(x,y+1, cnt+1);
                    attach(x,y,i,1);
                    paper[i]++;
                }
            }
        }else{
            //0이면 다음꺼 탐색
            dfs(x,y+1,cnt);
        }
    }

    private static boolean square(int x, int y, int size){
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                if(i>=10 || j>=10){
                    //색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안됨
                    return false;
                }
                if(arr[i][j]==0) {
                    //정사각형이 아니었음...
                    return false;
                }
            }
        }
        return true;
    }

    private static void attach(int x, int y, int size, int state){
        //0이 붙이기 1이 떼기
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                arr[i][j]=state;
            }
        }
    }
}
