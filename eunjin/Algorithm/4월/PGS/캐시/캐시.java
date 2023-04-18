import java.lang.*;
import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize==0|| cacheSize>=cities.length) return cities.length*5;
        
        for(int i=0;i<cities.length;i++){
            cities[i] = cities[i].toLowerCase();
        }
        
        ArrayList<String> list = new ArrayList<>();
       
        int answer =0 ;
        
        for(int i=0;i<cities.length;i++){
            if(!list.contains(cities[i])){
                answer+=5;
                list.add(cities[i]);
                //list.remove(0);
                if(list.size()>cacheSize)
                    list.remove(0);
            }
            else{
                answer+=1;
                for(int k=0;k<list.size();k++){
                    if(list.get(k).equals(cities[i])){
                        list.remove(k);
                        list.add(cities[i]);
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
