import java.util.*;
class Solution {
    public Set<Set<String>> resultSet;
    public int solution(String[] user_id, String[] banned_id) {
        resultSet = new HashSet<>();
        dfs(user_id, banned_id, new LinkedHashSet<>());
        return resultSet.size();
    }
    
    public void dfs(String[] user_id, String[] banned_id, Set<String> set){
        //중복을 제거해주기 위해서 Set을 활용한다.
       //가능한 조합을 다 넣어 줄건데 중복이 있으면 안됌!! 그래서 resultSet을 만들어 둔것임!
        if (set.size() == banned_id.length) {
            if (isAllBan(set, banned_id)) {
                resultSet.add(new HashSet<>(set));
            }
            
            return;
        }
        
        for (String userId : user_id) {
            if (!set.contains(userId)) {
                set.add(userId);
                dfs(user_id, banned_id, set);
                set.remove(userId);
            }
        }
        
        
    }
    
    public boolean isBan(String userid, String banid){
        //길이가 다르면 userid와 banid가 매칭되지 않으므로 그냥 넘어간다.
        if(userid.length()!= banid.length()) return false;
        
        for(int i=0;i<userid.length();i++){
            // ban당한 아이디와 동일한지 맞춰 본다, *이면 그냥 지나간다.
            if(banid.charAt(i)=='*') continue;
            //한글자라도 맞지 않으면 매칭 실패! false리턴한다.
            if(banid.charAt(i)!=userid.charAt(i))return false;
        }
    
        
        return true;
    }
    
    public boolean isAllBan(Set<String> set, String[] banned_id){
        int i = 0;
        
        for (String user : set) {
            if (!isBan(user, banned_id[i++])) {
                return false;
            }
        }
        
        return true;
    }
}
