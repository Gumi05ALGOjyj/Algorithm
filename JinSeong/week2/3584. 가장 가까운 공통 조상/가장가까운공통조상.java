import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class num3584가장가까운공통조상 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<ArrayList<Integer>> list;
    static int[] parent;
    //트리의 루트가 고정되어 있으면 dfs를 통해 탐색할 수 있다.
    //하지만 그렇지 않기 때문에 아래에서 위로 올라가는 방식을 이용해야 한다.

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            list = new ArrayList<ArrayList<Integer>>();

            for(int j=0; j<N+1; j++){
                list.add(new ArrayList<>());
            }


            parent = new int[N+1];

            for(int j=0; j<N-1; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b]=a;
                list.get(a).add(b);

            }


            //마지막 두 정점
            st = new StringTokenizer(br.readLine());
            int last_a = Integer.parseInt(st.nextToken());
            int last_b = Integer.parseInt(st.nextToken());

            int a_depth = get_depth(last_a);
            int b_depth = get_depth(last_b);

            int same_depth = same_depth(last_a, a_depth, last_b, b_depth);
            sb.append(same_depth).append("\n");
        }
        System.out.println(sb);
    }
    private static int same_depth(int a, int a_depth, int b, int b_depth){
        if(a_depth>b_depth){
            while(a_depth != b_depth){
                a_depth --;
                a = parent[a];
            }
        }
        else if(a_depth<b_depth){
            while(a_depth != b_depth){
                b_depth --;
                b = parent[b];
            }
        }

        while(a!=b){
            a=parent[a];
            b= parent[b];
        }
        return a;
    }

    private static int get_depth(int a){
        int ret = 0;
        int cur = a;
        while(cur!=0){
            ret++;
            cur = parent[cur];
        }
        return ret-1;
    }
}
