import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class num26084DPS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String S;
    static int N;
    static long cnt = 1;
//    static char[] handle;
//    static boolean[] visit;
//    static String[] arr;

    public static void main(String[] args) throws IOException {
//        DPS
//        7
//        DONGGAS
//        PICASSO
//        SEMTEO
//        DJS
//        WBCHO
//        RAARARAARA
//        WEASEL
        S = br.readLine();
        N = Integer.parseInt(br.readLine());

        //굳이 정렬안해도 될듯
//        char[] chars = S.toCharArray();
//        Arrays.sort(chars);
//        S = new String(chars);

        //0~25 A~Z 배열 생성
        //해당 알파벳 인덱스 증가

        //출력 부분으로 인해 long배열
        long[] dps = new long[26];
        //이후에 받을 7개 저장 할거
        long[] ddps = new long[26];

        //DPS => D, P, S 하나씩 넣는 구간
        for (int i = 0; i < 3; i++) {
            dps[S.charAt(i) - 'A']++;
        }

        //7개 첫글자 딴거
        for (int i = 0; i < N; i++) {
            ddps[br.readLine().charAt(0) - 'A']++;
        }

        //nCr * nCr * nCr
        for (int i = 0; i < dps.length; i++) {
            //안들어간 알파벳은 그냥 과감하게 패수
            if (dps[i] == 0) {
                continue;
            }


            cnt *= combination(ddps[i], dps[i]);
        }

        System.out.println(cnt);
    }

    //1. 실패 ㅡ HashMap 사용
//            char c = chrArr[i];
//            int mapc= map.get(c);
//            int map2c = map2.get(c);
////            if(map2c ==1){
////                cnt*=mapc;
////            }
//            if(map2c==1){
//                cnt*=mapc;
//            }
//            else if(map2c==mapc){
//                continue;
//            }
//            else{
//                cnt*=combination(mapc,map2c);
//            }
//        Map<Character,Integer> map2 = new HashMap<>();
//        char[] chrArr = new char[3];
//        for(int i=0; i<3; i++){
//            chrArr[i]= S.charAt(i);
//
//            map2.put(chrArr[i],1);
//
//        }

//        handle = new char[N];
//        for(int i=0; i<N; i++){
//            char k = br.readLine().charAt(0);
//            if(map2.containsKey(k)){
//                handle[i] = br.readLine().charAt(0);
//            }
//            else{
//                continue;
//            }
//        }


//        Arrays.sort(handle);
//        visit = new boolean[N];
//        arr = new String[S.length()]; //3 고정
//
//        dfs(0,0);
//        System.out.println(cnt);
//        Map<Character,Integer> map2 = new HashMap<>();
//        char[] chrArr = new char[3];
//        for(int i=0; i<3; i++){
//            chrArr[i]= S.charAt(i);
//            if(map2.containsKey(chrArr[i])){
//                map2.put(chrArr[i],map2.get(chrArr[i])+1);
//            }
//            else{
//                map2.put(chrArr[i],1);
//            }
//        }
//
//
//
//        Map<Character,Integer> map = new HashMap<>();
//        for(int i=0; i<N; i++){
//            char a = br.readLine().charAt(0);
//            if(!map2.containsKey(a)){
//                continue;
//            }
//            if(map.containsKey(a)){
//                map.put(a,map.get(a)+1);
//            }
//            else{
//                map.put(a,1);
//            }
//        }
    //        for(int i=0; i<map2.size(); i++){
//            char c = chrArr[i];
//            int mapc= map.get(c);
//            int map2c = map2.get(c);
////            if(map2c ==1){
////                cnt*=mapc;
////            }
//            if(map2c==1){
//                cnt*=mapc;
//            }
//            else if(map2c==mapc){
//                continue;
//            }
//            else{
//                cnt*=combination(mapc,map2c);
//            }
//        }
//        sb.append(cnt);
//        System.out.println(sb);
//    }

    //이건 id가 3으로 고정되기 때문에 nC1 , nC2, nC3밖에 나오지 않는다
    // 거기에 맞는 공식 적용
    private static long combination(long n, long r) {
        if (r == 1) {
            return n;
        } else if (r == 2) {
            return (n * (n - 1)) / 2;
        } else {
            return (n * (n - 1) * (n - 2)) / 6;
        }
    }
}

// 실패 ㅡ 백트래킹 사용
//    private static void dfs(int depth, int start){
//        if(depth == 3){
//
//            sb = new StringBuilder();
//            for(int k=0; k<3; k++){
//                sb.append(arr[k]);
//            }
//            if(sb.toString().equals(S)){
//                cnt++;
//            }
//            return;
//        }
//        for(int i=start; i<N; i++){
//            if(!visit[i]){
//                visit[i]=true;
//                arr[depth]=Character.toString(handle[i]);
//                dfs(depth+1,i);
//                visit[i]=false;
//            }
//        }
//    }

