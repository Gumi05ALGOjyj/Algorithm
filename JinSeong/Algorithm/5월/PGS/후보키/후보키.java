import java.util.*;
import java.lang.*;
class Solution {
    public static void main(String[] args) {
        String[][] relations = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };
        solution(relations);
    }

    static Set<String> set = new HashSet<>();
    static String[][] g_relation;

    public static int solution(String[][] relation) {
        g_relation = relation;
        int answer = 0;

        //1개를 선택하는 방법, 2개를 선택하는 방법,...
        for(int i=1; i<= relation[0].length; i++){
            boolean[] selected = new boolean[relation[0].length];
            dfs(0, 0,i,selected);
        }

        return set.size();
    }

    public static void dfs(int idx, int start, int cnt, boolean[] selected){
        if(start == cnt){

            StringBuilder sb = new StringBuilder();

            //선택된 컬럼들 string으로 합치기
            for(int i=0; i<selected.length; i++){
                if(selected[i]){
                    sb.append(i);
                }
            }

            //후보키로 사용가능한지
            if(isPossible(sb.toString(), selected)){
                set.add(sb.toString());
            }

            return;
        }

        //선택할 수 있는 수를 넘어서서 선택하면
        if(idx>=selected.length){
            return;
        }

        selected[idx] = true;
        dfs(idx+1,start+1, cnt, selected);

        selected[idx] = false;
        dfs(idx+1, start, cnt, selected);


    }

    public static boolean isPossible(String cols, boolean[] selected){
        //최소성
        for(String s : set){
            boolean flag = true;
            for(int i=0; i< s.length(); i++){
                if(!cols.contains(s.charAt(i)+"")){
                    flag = false;
                    break;
                }
            }

            if(flag){
                //모두 포함되어 있으면
                return false;
            }
        }

        //몇 번 컬럼을 확인해야하는지 처리
        Set<String> values = new HashSet<>();
        int[] col_name = new int[cols.length()];
        int idx = 0;
        for(int i=0; i<selected.length; i++){
            if(selected[i]){
                col_name[idx++] = i;
            }
        }

        //값에 중복되어 있는지 확인
        String value="";
        for(int i=0 ; i< g_relation.length; i++){
            value="";
            for(int j=0; j<col_name.length; j++){
                value += g_relation[i][col_name[j]];
            }
            if(values.contains(value)){
                return false;
            }else{
                values.add(value);
            }
        }
        return true;
    }
}
