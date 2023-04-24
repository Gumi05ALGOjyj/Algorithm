class Solution {
    public String solution(int n, int t, int m, int p) {
        /*
        n : 진법
        t : 미리 구할 숫자의 갯수
        m : 게임에 참가하는 인원
        p : 튜브의 순서
        */
        StringBuilder answer = new StringBuilder();
        int curOrder = 1;
        int num=0;
        while(true){
            String numberToString = makeNumber(num, n);
            for (int i = 0; i < numberToString.length(); i++) {
                if (curOrder == p) {
                    answer.append(numberToString.charAt(i)+"");
                    if(answer.length()==t) break;
                }
                curOrder++;
                if(curOrder>m) curOrder=1;
            }
            
            if(answer.length()==t) break;

            num++;
        }
 
        return answer.toString();
    }
    
    public String makeNumber(int number, int type){
        String storeNumber = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        
        int num = number;
        
          while (num >= type) {
            int add = num % type;
            sb.append(storeNumber.charAt(add));
            num /= type;
        }

        if (num > 0)
              sb.append(storeNumber.charAt(num));

         if(sb.length()==0) return "0";
      return sb.reverse().toString();
        
    }
}
