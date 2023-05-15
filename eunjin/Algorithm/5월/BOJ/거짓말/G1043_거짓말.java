package gold;    

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//union & find를 사용해서 풀이를 진행하였다.
public class G1043_거짓말 {
    static int[] parents;
    static int N;
    static int M;
    static boolean[] isKonw;    //해당 번호의 사람이 진실을 알고 있는지 확인하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //사람의 수
        M = Integer.parseInt(st.nextToken());   //파티의 수

        isKonw = new boolean[N + 1];  //사람의 번호는 1부터 시작
        parents = new int[N + 1];

        for (int i = 1; i < N+1; i++) {
            parents[i] = i;
        }

        //진실을 알고 있는 사람 정보 저장
        st = new StringTokenizer(br.readLine());
        int peopleNum = Integer.parseInt(st.nextToken());
        for (int i = 0; i < peopleNum; i++) {
            int p = Integer.parseInt(st.nextToken());
            isKonw[p] = true;
        }

        //파티 정보를 입력 받으면서 같은 파티에 있는 사람들을 union해준다.
        ArrayList<Integer>[] party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            peopleNum = Integer.parseInt(st.nextToken());
            //파티에 온 사람이 없다면 그냥 넘어간다.
            if (peopleNum == 0) continue;

            int a = Integer.parseInt(st.nextToken());
            party[i].add(a);
            for (int j = 1; j < peopleNum; j++) {
                int b = Integer.parseInt(st.nextToken());
                party[i].add(b);
                union(a, b);
                a = b;
            }
        }

        //만약 자식이 참여 했으면 부모도 알고 있다.
        for (int i = 1; i < isKonw.length; i++) {
            if (isKonw[i])
                isKonw[find(i)] = true;
        }

        int result = 0;
        //진실을 아는 사람과 파티 참여를 하지 않았으면 result++;
        for(int i=0;i<M;i++){
            if(party[i].size()==0) continue;
            boolean flag = true;
            for(int j=0; j<party[i].size(); j++){
                int p = party[i].get(j);
                int parent = find(p);
                if(isKonw[parent]){
                    flag = false;
                    break;
                }
            }
            if(flag)
                result++;
        }

        System.out.println(result);
    }

    public static int find(int x) {
        if (parents[x] == x) return parents[x] = x;
        else return find(parents[x]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a > b) parents[a] = b;
            else parents[b] = a;
            return true;
        } else return false;
    }

}
