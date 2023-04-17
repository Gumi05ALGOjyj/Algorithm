import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        List<Character> list = new ArrayList<>();
        int num=1;
        list.add('0');
        
        while(list.size()< t* m){
            int temp = num;
            String s = "";
            while(temp!=0){
                int mod = temp%n;
                mod= mod>=10? 'A' +mod-10 : '0'+mod;
                s = (char)mod+s;
                temp = temp/n;
            }
            for(char c : s.toCharArray()){
                list.add(c);
            }
            num++;
        }
        for(int i=p-1; answer.length()<t; i+=m){
            answer += list.get(i);
        }
        
        return answer;
    }
}
