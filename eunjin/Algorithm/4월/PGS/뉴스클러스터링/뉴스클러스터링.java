import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        // str1과 str2를 모두 소문자로 변경한 후 집합을 구해준다!
        ArrayList<String> set1 = makeSet(str1.toLowerCase());
        ArrayList<String> set2 = makeSet(str2.toLowerCase());
        
        //교집합의 크기를 저장해줄 변수
        int intersection =0;
        //중복 count를 막기 위해서 boolean 배열을 선언
        boolean[] isSelected = new boolean[set2.size()];
    
        //교집합을 찾아 준다.
        for(int i=0;i<set1.size();i++){
            for(int j=0;j<set2.size();j++){
                //string이 다르면 그냥 넘어간다.
                if(!set1.get(i).equals(set2.get(j))) continue;
                //이미 체크 된 값이면 넘어간다.
                if(isSelected[j]) continue;
                //다 만족한다면? 교집합이다. count한번 해주고 boolean배열에 체크 후 멈춘다.
                intersection++;
                isSelected[j] = true;
                break;
            }
            
        }
        //합집합을 구해준다 : 합집합 = 전체 집합크기 - 교집합 크기
        int union = set1.size()+ set2.size() - intersection;
        
        //교집합이 0이고 배열 크기 전체가 0이 아니면 0을 return 한다.
        if(intersection==0 && union!=0) return 0;
        
        //합집합이 0이란 뜻은 모두 교집합이라는 뜻이니 65536을 바로 리턴해준다. 0으로 나누지 않기 위해서!
        if(union ==0) return 65536;
        
        answer = intersection* 65536/union ;

        return answer;
    }
    
    //집합을 만들어 주는 함수이다. 
    public ArrayList<String> makeSet(String str){
        ArrayList<String> list = new ArrayList<>();
        
        for(int i=0;i<str.length()-1;i++){
            //알파벳만 포함된 집합이 유효하므로 isAlphabetic함수를 이용해서 알파벳인지 확인해준다.
            if(!Character.isAlphabetic(str.charAt(i))) continue;
            if(!Character.isAlphabetic(str.charAt(i+1))) continue;
            
            list.add(str.substring(i,i+2));
        }
        
        return list;
    }
}
