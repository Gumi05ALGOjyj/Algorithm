import java.util.*;
class Solution {
    static boolean[] isUsed;    //사용한 속성인지 확인해주는 배열 => 최소성 만족
    static ArrayList<Integer> list = new ArrayList<>();
    static ArrayList<HashSet<Integer>> candidateKeys = new ArrayList<>();
    static int result=0;
    
    public int solution(String[][] relation) {
        isUsed = new boolean[relation[0].length];
       
        for(int i=1;i<=relation[0].length;i++){
            comb(0,i,relation);
            list.clear();
        }
        
        return result;
    }
    
    public void comb(int start, int size, String[][] relation){
        //조합으로 키를 선택해준다.
        //if(start==relation[0].length-1 && list.size()!=size) return;
        if(list.size()==size){
            HashSet<Integer> currentSet = new HashSet<>(list);
            for (HashSet<Integer> key : candidateKeys) {
                if (currentSet.containsAll(key)) {
                    return;
                }
            }
            if(isUnique(currentSet, relation)){
                candidateKeys.add(currentSet);
                result++;
            }
            return;
        }
        
        for(int i=start;i<relation[0].length;i++){
            if(isUsed[i]) continue;
            list.add(i);
            comb(i+1, size,relation);
            list.remove(list.size()-1);
            comb(i+1, size,relation);
        }
        
    }
    
     public boolean isUnique(HashSet<Integer> set, String[][] relation){
        ArrayList<String> tuple = new ArrayList<>();
        for(int i=0;i<relation.length;i++){
            String temp = "";
            for(int index : set){
                temp += relation[i][index];
            }
            if(tuple.contains(temp)){
                return false;
            } else {
                tuple.add(temp);
            }
        }
        return true;
    }
}
