import java.util.*;
import java.io.*;
class Solution {
    //static StringBuilder sb = new StringBuilder();
    public String solution(String p) {
        //1. 입력이 빈 문자열일 경우, 빈 문자열 반환
        if(p.length()==0) return "";
        
      
       
       return  process(p);
    }
    //STEP2. 문자열을 두 균형잡힌 문자열로 분리
    public String[] seperateString(String p){
        String[] result = new String[2];
        int uEndIndex = findU(p);
        result[0] = p.substring(0,uEndIndex+1);
        result[1] = p.substring(uEndIndex+1, p.length());
        return result;
    }
    
    public int findU(String p){
        int right = 0, left = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='(') ++left;
            else if(p.charAt(i)==')') ++right;
            
            if(left == right) return i;
        }
        return p.length();
    }
    
    public boolean isCorrectString(String str){
        //올바른 괄호 문자열인지 판한다는 함수
        Stack<Character> stack = new Stack<>();
        //1. ( 가 나오면 stack에 넣어준다. )가 나오면 스택에서 빼준다 이때 스택이 비어있으면 return false를 해준다.
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c=='(') stack.add(c);
            else if(c==')'){
                if(stack.isEmpty())return false;
                stack.pop();
            }
        }
        
        if(!stack.isEmpty()) return false;
        else return true;
    }
    
    public String process(String str){
        System.out.print(str.length()+"\n");
        if(str.length()==0) return "";
        String [] strs = seperateString(str);
        if(isCorrectString(strs[0])){
            //sb.append(strs[0]);
            return strs[0]+process(strs[1]);
        }else{
            //빈 문자열에 첫 번째 문자로 (을 붙인다.
            String result = "(";
            //문자열 v에 대해 1단계 부터 재귀적으로 수행한 결과 문자열을 이어 붙인다. 
            String add = process(strs[1]);
            result +=add;
            result+=")";
            
            //sb.append(edit(strs[0]));
            return (result+edit(strs[0]));
        }
       
    }
    
    public String edit(String str){
        //첫 번째 문자열과 마지막 문자열을 제거한다.
        //ArrayList<Character> list = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        for(int i=1;i<str.length()-1;i++){
            char c = str.charAt(i);
            if(c==')') tmp.append("(");
            else tmp.append(")");
        }
        return tmp.toString();
    }
}
