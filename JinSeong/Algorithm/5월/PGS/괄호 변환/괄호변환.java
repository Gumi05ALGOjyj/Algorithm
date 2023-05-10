import java.util.*;

class Solution {
    
    public String solution(String p) {
        StringBuilder answer = new StringBuilder();
        //1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
        if(p.equals("")){
            return "";
        }
        
        //2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        //단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며,
        //v는 빈 문자열이 될 수 있습니다. 
        int bracket=0;
        StringBuilder u= new StringBuilder();
        StringBuilder v= new StringBuilder();
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '('){
                bracket++;
            }
            else{//')'
                bracket--;
            }
            if(bracket==0){
                u.append(p.substring(0,i+1));
                v.append(p.substring(i+1));
                break;
            }
        }
        
        //3. 문자열 u가 "올바른 괄호 문자열" 이라면
        //문자열 v에 대해 1단계부터 다시 수행합니다. 
        //3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
        if(isCorrect(u.toString())){
            return u.append(solution(v.toString())).toString();
        }
        //4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
        //4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
        //4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
        //4-3. ')'를 다시 붙입니다. 
        answer.append('(').append(solution(v.toString())).append(')');
        
        //4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
        u.deleteCharAt(0);
        u.deleteCharAt(u.length()-1);
        answer.append(swap(u.toString()));
        //4-5. 생성된 문자열을 반환합니다.
        
        return answer.toString();
    }
    
    public boolean isCorrect(String s){
        Stack<String> st = new Stack<>();
        for(String t: s.split("")){
            if(t.equals("(")){
                st.push(t);
            }
            if(t.equals(")")&&!st.isEmpty()){
                st.pop();
            }
        }
        
        return st.size() < 1;
    }
    
    public String swap(String s){
        StringBuilder sb2 = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c=='('){
                sb2.append(')');
            }
            else{
                sb2.append('(');
            }
        }
        return sb2.toString();
    }
}
