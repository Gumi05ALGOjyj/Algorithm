import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    //이게 파티 같이 간 사람들은 전부 이어진다는 판정을 해서 union-find로 풀어야 한다고 생각했슴둥
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    //파티수, 사람수
    static int party, people;
    static int[] parent;
    //진실을 아는 사람
    static boolean[] truth = new boolean[51];
    static int answer;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        people = Integer.parseInt(st.nextToken());
        party = Integer.parseInt(st.nextToken());

        //유니온-파인드 초기화
        parent = new int[people+1];
        for(int i=1; i<=people; i++){
            parent[i]=i;
        }

        //진실을 아는 사람들
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            truth[Integer.parseInt(st.nextToken())] = true;
        }

        //같은 파릐에 있는 사람들 union
        ArrayList<Integer>[] peoples = new ArrayList[party];
        for(int i=0; i<party; i++){
            peoples[i] = new ArrayList<>();
        }

        int value, pre=0;
        for(int i=0; i<party; i++){
            st = new StringTokenizer(br.readLine());
            //파티 안 사람 수
            n = Integer.parseInt(st.nextToken());
            
            //미리 pre 설정
            pre = Integer.parseInt(st.nextToken());
            peoples[i].add(pre);
            
            //같은 파티 인원들 한 부모로 합치기
            for(int j=1; j<n; j++){
                value = Integer.parseInt(st.nextToken());
                peoples[i].add(value);
                union(pre,value);
                pre=value;
            }
        }

        //진실을 아는 사람들이랑 파릐 참여함
        for(int i=1; i<truth.length; i++){
            if(truth[i]){
                truth[find(i)] = true;
            }
        }

        //같이 참여안했으면 answer++;
        int parent;
        for(int i=0; i<party; i++){
            if(peoples[i].size()>0){
                parent = find(peoples[i].get(0));
                if(!truth[parent]) answer++;
            }
        }
        System.out.println(answer);

    }

    private static int find(int x){
        if(parent[x]==x){
            return parent[x]=x;
        }
        else{
            return find(parent[x]);
        }
    }

    private static boolean union(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            if(a>b){
                parent[a]=b;
            }
            else{
                parent[b]=a;
            }
            return true;
        }
        return false;
    }
}
