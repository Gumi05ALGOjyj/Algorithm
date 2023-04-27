import java.io.*;
import java.util.*;

class Solution {
    public String[] userId, bannedId;
    
    public int ul, bl, answer = 0; // user_id[] 의 길이, banned_id[]의 길이
    
    public boolean[] visited; // user_id[]의 방문체크
    
    HashSet<HashSet<String>> result = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        ul = user_id.length;
        
        bl = banned_id.length;
        
        userId = user_id;
        
        bannedId = banned_id;
        
        visited = new boolean[ul];

        combination(new HashSet<String>(), 0);
        
        return result.size();
    }
    
    public void combination(HashSet<String> set, int cnt){
        if(cnt == bl){ 
            result.add(set);
            return;
        }
        for(int i = 0; i<ul; i++){
            if(isSame(userId[i], bannedId[cnt]) && !visited[i]){
                visited[i] = true;
                set.add(userId[i]);
                combination(set, cnt+1);
                set.remove(userId[i]);
                visited[i] = false;
            }
        }
    }
    
    
      // 두 개의 문자열이 같은 문자열이면 true를 반환한다.
    public boolean isSame(String user, String ban){
        if(user.length() == ban.length()){
            for(int i =0; i<ban.length(); i++){
                if(ban.charAt(i) == '*')
                    continue;
                
                if(ban.charAt(i) != user.charAt(i))
                    return false;
            }
            return true;
        }
        return false;
    }

}
