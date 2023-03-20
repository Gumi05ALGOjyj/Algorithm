import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class num15666NM12{
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static boolean[] visit;
    static int[] input;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        visit = new boolean[N];
        arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            input[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);
        dfs(0,0);
        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int start) {
        if(depth == M){
            for(int val : arr){
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        int before = -1;
        for(int i= start; i<N; i++){
            int now = input[i];

            if(!visit[i]){
                if(before==now){
                    continue;
                }
//                visit[i]= true;
                before=now;
                arr[depth]= input[i];

                dfs(depth+1,i);
                visit[i]= false;
            }
        }
    }
}
