import java.util.*;

class Solution {
    static boolean[] visited;
    static Set<String> set;
    static int answer;
    
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        
        visited = new boolean[user_id.length];
        set = new HashSet<>();
        
        // 모든 경우의 수에 대한 조합을 구하기 위해 DFS로 진행
        dfs(0,user_id,banned_id);
        
        return answer;
    }
    
  
/* 코드진행
* 1. banned_id[step]과 user_id가 일치할 수 있는지 check.
*   user_id가 visited이거나 길이가 banned_id와 다르다면 확인하지하지 않는다.
* 2. 일치할 수 있으면 Set에 저장하고 dfs(step+1) 재귀호출. -> 모든 경우의 수 탐색
*   +필요한 작업
*     list에 인덱스를 추가
*     visited배열에 표시
* 3. 해당되는 user_id가 없으면 return; (더이상 조합을 구성하지 않음)
*/
    public static void dfs(int step,String[] user_id, String[] banned_id){
        
        if(step==banned_id.length){//조합 구성 완성 시. (dfs종료부분)
            if(set.contains(Arrays.toString(visited))) {return;}
            else {
                set.add(Arrays.toString(visited));
                answer++;
                return;
            }
        }
        
        //dfs반복수행부분
        //banned_id와 user_id 비교를 위해 banned_id에서 *의 위치 기록
        List<Integer> starIndexList = new ArrayList<>();
        for(int i=0;i<banned_id[step].length();i++){
            if(banned_id[step].charAt(i)=='*') {
                starIndexList.add(i);
            }
        }
        //banned_id[step]과 user_id전체 비교. 매칭될 시 다음스텝 dfs 진행
        for(int i=0;i<user_id.length;i++){
            if(visited[i]) continue;
            if(user_id[i].length()!=banned_id[step].length()) continue; //길이가 다르면 비교하지 않음

            //user_id[i]에 *표시를 해주고 tmp에 저장
            String tmp="";
            char[] tmpC=user_id[i].toCharArray();
            for(int j=0;j<starIndexList.size();j++){
                tmpC[starIndexList.get(j)]='*';
            }
            for(int j=0;j<tmpC.length;j++){
                tmp=tmp+tmpC[j];
            }
            //tmp와 banned_id 일치하는지 비교
            if(tmp.equals(banned_id[step])){
                //일치하면 2번작업 수행
                visited[i]=true;
                dfs(step+1,user_id,banned_id);
                visited[i]=false;
            }
        }
        //return;
    }
}
