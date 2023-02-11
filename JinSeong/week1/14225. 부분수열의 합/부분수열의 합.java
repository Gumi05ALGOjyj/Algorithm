import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class num14225부분수열합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] input;
    static int[] arr;
    static Set<Integer> set = new HashSet<>();
    static int a;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        visit = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        for(a=1; a<=N; a++){
            arr=new int[a];
            Arrays.fill(visit,false);
            dfs(0,0);
        }

        int cnt=1;
        while(true){
            if(!set.contains(cnt)){
                System.out.println(cnt);
                return;
            }
            cnt++;
        }

    }
    private static void dfs(int depth, int start){
        if(depth==a){
            int sum=0;
            for(int i=0; i<a; i++){
                sum += arr[i];
            }
            set.add(sum);
            return;
        }
        for(int i=start; i<N; i++){
            if(!visit[i]){
                visit[i] = true;
                arr[depth] = input[i];
                dfs(depth+1,i);
                visit[i]=false;
            }
        }
    }

}
