class Solution {
    static String[] nums;
    static int mt;

    public String solution(int n, int t, int m, int p) {
        // n: 진법    t: 미리 구할 숫자의 개수   m: 게임에 참가하는 인원    p: 튜브의 순서
        String answer = ""; // 결과를 담을 변수
        nums = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" }; // 0-16


        // 1. 1부터 n진수로 변환하기
        // 2. n진수로 변환하면서 결과를 string result 변수에 추가
        // 3. result변수의 길이가 nm보다 작아질 때 확인
        // 4. 변수의 길이가 nm보다 같거나 작을때까지, 수를 n진수로 변환하기

        String result ="0";                         // 사람들이 번갈아가면서 불렀던 n진법 숫자들, nm번까지 부를때 나온 n진법 숫자들, 0부터 시작이라서 0 추가해줌
        int mt = m * t;

        int num = 1;                                // 문제에서 숫자 0부터 시작인데 미리 result에 0 추가해서 num은 1부터 시작, 시작 숫자 뭔지 잘 확인하기
        while (result.length() <= mt) {             // 사람들이 부르는 숫자들의 길이가 mt보다 같거나 작을때까지 계속 숫자를 부름
            String convertNum = convert(num, n);    
            result += convertNum;                   // 사람들이 부르는 숫자들 추가
            num += 1;                               // 그 다음 숫자
        }
        //System.out.println("리절트 : " + result);

        // 여기서 나온 String result (즉 길이가 mt인 길이까지) 에서 내가 말할 숫자 뽑기
        int tubeTurn = p;
        for(int i = 0 ; i < result.length() ; i++){
            if(answer.length()==t) break;
            char tmp = result.charAt(tubeTurn-1);
            System.out.println("tmp " + tmp);
            answer += tmp;
            tubeTurn += m;
        }
        //System.out.println(answer);
        return answer;
    }

    static String convert(int num, int n) { // 십진수 숫자 num을 n진수로 변환하는 함수
        String tmp = "";                    
        while (num > 0) {
            int remain = num % n; // num을 n으로 나눈 나머지
//          if (remain > 9) { // 나머지가 10이상인 경우
//              tmp = tmp + nums[remain]; // A-F를 추가함
//          } else { // 나머지가 0-9인경우
//              tmp = tmp + remain; // 해당 숫자를 추가함
//          }
            tmp = tmp + nums[remain];
            num = num / n; // num은 다시 나눈 몫으로 갱신
        }

        String resultN = ""; // 십진수 숫자 num을 n진수로 변환한 결과                     
        // tmp문자열을 반대로 뒤집기 (StringBuilder)
        StringBuilder sb = new StringBuilder(tmp);
        resultN = sb.reverse().toString();
        //System.out.println("convert : " + resultN);

        return resultN;

    }
  
  	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solution(2,4,2,1);
	}
}
