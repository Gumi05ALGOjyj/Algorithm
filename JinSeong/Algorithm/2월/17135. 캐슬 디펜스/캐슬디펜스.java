import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class num17135캐슬디펜스 {
    static int[][] map;
    static int n,m,d;
    static int ans=0;
    static ArrayList<Integer> archer=new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        d=Integer.parseInt(st.nextToken());
        map=new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
        System.out.println(ans);
    }

    private static void attack() {
        int temp=0;
        //세로 길이
        int castle=n;
        int[][] map2=new int[n][m];
        
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, map2[i], 0, m);
        }

        while(castle>0) {
            ArrayList<Integer[]> monster=new ArrayList<>();
            for (int i = 0; i < archer.size(); i++) {
                int min=Integer.MAX_VALUE;
                int ii=0;
                int jj=0;
                for (int j = 0; j < m; j++) {
                    for(int k=castle-1; k>=0; k--) {
                        //적들이 내려오는 방식을
                        //위로 올라가면서 적을 찾는 방법으로 전환
                        int diff=Math.abs(castle-k)+Math.abs(archer.get(i)-j);

                        //적이 사거리 안에 있으면
                        if(map2[k][j]==1 && diff<=d) {
                            //최소 거리보다 적으면
                            //왼쪽에서 오른쪽으로 탐색하는 방향이기 때문에
                            //왼쪽에서 min을 설정해버리면
                            //가운데나 오른쪽이 같은거리에 있어도 방문X
                            if(diff<min) {
                                min=diff;
                                ii=k;
                                jj=j;
                            }
                            break;
                        }
                    }
                }
                //사거리내의 적을 찾았다면 monster list에 추가
                if(min!=Integer.MAX_VALUE) {
                    monster.add(new Integer[]{ii,jj});
                }
            }
            for (int i = 0; i < monster.size(); i++) {
                if(map2[monster.get(i)[0]][monster.get(i)[1]]!=0) {
                    //배열이 0이 아닌경우만 방문하기 때문에 
                    //다른 궁수가 같은 적을 죽여도 이미 0인걸 찾아서
                    //temp가 추가되지 않음
                    
                    temp+=1;
                    map2[monster.get(i)[0]][monster.get(i)[1]]=0;
                }
            }
            //궁수를 위로 올리기
            castle-=1;

        }

        if(temp>ans)ans=temp;
    }

    private static void dfs(int cnt,int start) {
        if(cnt==3) {
            //궁수의 위치가 다 정해졌으면
            attack();
            return;
        }
        for (int i = start; i < m; i++) {
            archer.add(i);
            dfs(cnt+1,i+1);
            archer.remove(archer.size()-1);
            //최근에 추가 했던 archer 제거
        }
    }

}
