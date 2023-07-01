import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class num1865웜홀 {
    //벨만포드 기초문제...
    //시간이 되돌아 가는 경우도 있기 때문에
    //마이너스 경로가 이씀
    //그래서 벨만포드 하기로 결정해부렀음
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static class Node{
        int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    private static boolean bellmanford(){
        int[] distance = new int[N+1];
        //배열을 0으로 초기화하고
        //음수 사이클이 있는지 확인
        //n-1번 순회 돌려서 최단거리를 구한 후
        //한번더 돌렸을 때 또 갱신이 된다?
        //=> 음의 사이클이 있다는 것
        boolean update = false;
        for(int i=1; i<=N; i++){
            //N까지 돌려주는 이유는 N-1번 돌리고 음의 사이클이 있는지를 확인하기 위해서
            update = false;
            for(int cur=1; cur<=N; cur++){
                for(Node next : graph.get(cur)){
                    if(distance[next.node] > distance[cur]+ next.weight){
                        distance[next.node] = distance[cur] + next.weight;
                        update = true;
                    }
                }
            }

            if(!update){
                //음의 사이클이 없는 경우
                break;
            }
        }
        return update;
    }
    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for(int t=1; t<=TC; t++){
            String answer="";
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++){
                graph.add(new ArrayList<>());
            }

            for(int i=0; i< M; i++){
                //일반 도로
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                //도로는 양방향
                graph.get(S).add(new Node(E,T));
                graph.get(E).add(new Node(S,T));
            }

            for(int i=0; i<W; i++){
                //웖ㅁㅁㅁㅁㅁㅁㅁㅁㅁ홀ㄹㄹㄹ
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                //웜홀이기 때문에 value값 -1곱해주기ㅣ
                graph.get(S).add(new Node(E,-1*T));
            }

            if(bellmanford()){
                answer = "YES";
            }
            else{
                answer = "NO";
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
