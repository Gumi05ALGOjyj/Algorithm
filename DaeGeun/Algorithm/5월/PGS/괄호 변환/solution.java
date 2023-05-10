import java.util.*;

class Solution {
    //지문에서 제시하는 순서에 따라 착실히 수행하면 자동으로 풀리는 문제
    //'균형잡힌 문자열', '올바른 문자열'에 대한 조건 설정에 유의    
    public String solution(String p) {
        return resolve(p);
    }
    
    private static String resolve(String p){
        //조건1
        if(p.equals("")) return p;

        int index;
        String u,v;
        
        //조건2
        index = findBalanced(p);
        u = p.substring(0,index+1); //substring의 매개변수 지정에 유의
        v = p.substring(index+1,p.length());
        
        if(isCorrect(u)){
            //조건3
            return u+resolve(v);
        } else{
            //조건4
            StringBuilder tmp = new StringBuilder();
            tmp.append("("); //4-1
            tmp.append(resolve(v)); //4-2
            tmp.append(")"); //4-3
            tmp.append(operation4_4(u)); //4-4
            return tmp.toString();
        }
    }
    
    //조건2를 해결하는 메소드    
    static int findBalanced(String p){
        int cnt=0;
        int index=-1;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='('){
                cnt++;
                if(cnt==0) {
                    index=i;
                    break;
                    //조건 2의 세부 조건에 의해 u는 분해가 되면 안되기때문에 한번 찾으면 바로 stop
                }
            } else{
                cnt--;
                if(cnt==0) {
                    index=i;
                    break;
                    //조건 2의 세부 조건에 의해 u는 분해가 되면 안되기때문에 한번 찾으면 바로 stop
                }
            }
        }
        return index;
    }
    
    //조건3을 판단하는 메소드
    static boolean isCorrect(String p){
        int cnt=0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='('){
                cnt++;
            } else{ //p.charAt(i)==')'인 경우
                if(cnt>0){ //'('개수보다 ')'가 더 나온경우 올바르지 않음
                    cnt--;
                } else{
                    return false;
                }
            }
        }
        //정상적으로 포문을 통과할 경우 올바른 문자열
        return true;
    }

    //4-4조건을 수행하는 메소드.
    //처음과 끝 문자 제거하고 나머지 괄호반전
    static String operation4_4(String p){
        StringBuilder sb = new StringBuilder(p);
        sb.delete(0,1);
        sb.delete(sb.length()-1,sb.length());
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)=='(') sb.setCharAt(i,')');
            else sb.setCharAt(i,'(');
        }
        return sb.toString();
    }
}

//IDE없이 메소드목록 확인하는 방법
//String tmp;
//Method[] methods = tmp.getClass().getMethods();
    //methods 출력 시 tmp로 사용 가능한 메소드 모두 출력함.

//ex2) 객체에 getClass() 후 getMethods()
    // List<String> list = new ArrayList<>();
    // for(Object o : list.getClass().getMethods()) {
    //      System.out.println(o);
    // }

//ex3) 클래스에 .class 후 바로 .getMethods()
    // Object[] objects = StringBuilder.class.getMethods();
    // for(Object o : objects){
    //     System.out.println(o);
    // }
