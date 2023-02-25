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
                    for(int k=castle-1;k>=0;k--) {
                        int diff=Math.abs(castle-k)+Math.abs(archer.get(i)-j);

                        if(map2[k][j]==1 && diff<=d) {
                            if(diff<min) {
                                min=diff;
                                ii=k;
                                jj=j;
                            }
                            break;
                        }
                    }
                }
                if(min!=Integer.MAX_VALUE) {
                    monster.add(new Integer[]{ii,jj});
                }
            }
            for (int i = 0; i < monster.size(); i++) {
                if(map2[monster.get(i)[0]][monster.get(i)[1]]!=0) {
                    temp+=1;
                    map2[monster.get(i)[0]][monster.get(i)[1]]=0;
                }
            }
            castle-=1;

        }

        if(temp>ans)ans=temp;
    }

    private static void dfs(int cnt,int start) {
        if(cnt==3) {
            attack();
            return;
        }
        for (int i = start; i < m; i++) {
            archer.add(i);
            dfs(cnt+1,i+1);
            archer.remove(archer.size()-1);
        }
    }

}
