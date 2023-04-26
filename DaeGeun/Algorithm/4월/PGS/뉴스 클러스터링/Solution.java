import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        int gyo=0, hap=0;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        // System.out.println(str1.length());
        char c1, c2;
        
        //str1 두글자씩 리스트화
        c2=str1.charAt(0);
        if(isLowerCase(c2)) c2-=('a'-'A'); //대문자로 통일
        for(int i=1;i<str1.length();i++){
            c1 = c2;
            c2 = str1.charAt(i);
            if(isLowerCase(c2)) c2-=('a'-'A');
            if(isAlphabet(c1)&&isAlphabet(c2)){
                list1.add(c1+""+c2);
                System.out.print(c1+""+c2+", ");
            } else{
                continue;
            }
        }System.out.println();
        //str2 두글자씩 리스트화
        c2=str2.charAt(0);
        if(isLowerCase(c2)) c2-=('a'-'A'); //대문자로 통일
        for(int i=1;i<str2.length();i++){
            c1 = c2;
            c2 = str2.charAt(i);
            if(isLowerCase(c2)) c2-=('a'-'A');
            if(isAlphabet(c1)&&isAlphabet(c2)){
                list2.add(c1+""+c2);
                System.out.print(c1+""+c2+", ");
            } else{
                continue;
            }
        }System.out.println();
        
        //매칭 시작.
            //매칭이 성공하면 각 리스트에서 제거하고 gyo, hap에 +1
            //매칭이 실패하면 l
        loop:
        while(!list1.isEmpty() && !list2.isEmpty()){
            for(int i=0;i<list2.size();i++){
                System.out.println(list1.get(0)+" & "+list2.get(i));
                if(list1.get(0).equals(list2.get(i))){
                    list1.remove(0);
                    list2.remove(i);
                    i=-1;
                    gyo++;
                    hap++;
                }
                if(list1.isEmpty() || list2.isEmpty()) break loop;
            }
            //list1의 첫번째 원소에 대해서 일치하는 list2의 원소가 없을 때
            list1.remove(0);
            hap++;
        } //일치 계산 끝
        //리스트 중 하나 이상이 비었을 때
        if(list1.isEmpty()) hap+=list2.size();
        else if(list2.isEmpty()) hap+=list1.size();
        if(hap==0) return 65536;
        answer = (int)(1.0*gyo/hap*65536);
        System.out.println(gyo+"/"+hap);
        return answer;
    }
    
    public static boolean isLowerCase(char c){
        if(c>='a' && c<='z') return true;
        else return false;
    }
    public static boolean isUpperCase(char c){
        if(c>='A' && c<='Z') return true;
        else return false;
    }
    public static boolean isAlphabet(char c){
        if(isLowerCase(c)||isUpperCase(c)) return true;
        else return false;
    }
}
