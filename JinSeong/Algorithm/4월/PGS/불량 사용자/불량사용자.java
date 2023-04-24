import java.util.*;

class Solution {
    
    public HashSet<String> set;
    public boolean[] checked;
    
    
    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        checked = new boolean[user_id.length];
        for(int i=0;i<banned_id.length; i++){
            //정규식 표현에서 '.'이 임의의 한 글자
            banned_id[i] = banned_id[i].replace('*','.');
        }
        
        dfs(0, user_id, banned_id, new HashSet<String>());
        return set.size();
    }
    
    public void dfs(int checkIdx, String[] user_id, String[] banned_id, HashSet<String> result){
        if(result.size()==banned_id.length){
            ArrayList<String> list = new ArrayList<String>(result);
            Collections.sort(list);
            
            StringBuilder sb = new StringBuilder();
            for(String str : list){
                sb.append(str+" ");
            }
            
            set.add(sb.toString());
            
            return;
        }

        for(int i=0;i<user_id.length;i++){
            if(checked[i]) continue;
            
            if(user_id[i].matches(banned_id[checkIdx])){
                checked[i] = true;
                result.add(user_id[i]);
                dfs(checkIdx+1, user_id, banned_id, result);
                result.remove(user_id[i]);
                checked[i] = false;
            }
        }
    }
}
