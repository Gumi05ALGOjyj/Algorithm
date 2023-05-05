
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int left = 0;
        int right = 200_000_000;
        int mid=(left+right)/2;
        //들어갈  수 있는 사람의 수를 끼워맞춰서 가능한지 본다.
        while(left<=right){
            mid=(left+right)/2;
            if(!isPossible(stones, k, mid)){
                //불가능하다? 사람을 줄인다.
                right = mid-1;
            }else{
                //최대 인원수를 저장해준다.
                answer = Math.max(answer,mid);
                left = mid+1;
            }
        }
        
        
        
        return answer;
    }
    
    //people만큼의 사람이 갈 수 있는지 확인해주는 함수
    public boolean isPossible(int stones[], int k, int people){
        int count = 0;
        for(int i=0;i<stones.length;i++){
            if(stones[i]-people<0) count++;
            else count=0;
            
            if(count==k) return false;
        }
        
        return true;
    }
}
