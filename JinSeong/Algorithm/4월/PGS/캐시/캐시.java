import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        if(cacheSize==0){
            //캐시 없을 때 remove하니까 에러터짐
            return cities.length*5;
        }
        else{
            for(String city : cities){
            
                //대소문자 구별없음
                String temp = city.toUpperCase();

                if(list.contains(temp)){
                    //있을 때
                    list.remove(temp);
                    list.add(temp);
                    answer++;
                }
                else{
                    //없을 때
                    if(list.size()==cacheSize){
                        //캐시 가득찼으면
                        list.remove(0);
                    }
                    list.add(temp);
                    answer+=5;
                }
            }
        return answer;
        }
        
    }
}
