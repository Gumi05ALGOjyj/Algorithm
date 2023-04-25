package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 방번호_1082 {
    static int N,M;
    static int[] P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        P = new int[N];
        st = new StringTokenizer(br.readLine());
        int min=50, minIdx=-1;
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
            if(P[i]<min){
                min = P[i];
                minIdx = i;
            }
        }
        M = Integer.parseInt(br.readLine());
        //----- 입력 끝 : 최소값을 구해 주었다.-----=------
        int maxLength=0;
        while (true){
            M-=min;
            if(M>=0){
                maxLength++;
            }else{
                M+=min;
                break;
            }
        }

        int result[] = new int[maxLength];
        Arrays.fill(result,minIdx);
        int res=0;
       int startIdx =0;
       while (true){
           //배열의 앞 자리부터 큰 idx의 숫자가 들어갈 수 있는지 확인한다.
           for (int i = startIdx; i< maxLength; i++) {
               for (int j = N-1; j >= 0; j--) {
                  if(M+min-P[j]>=0){
                      result[i] = j;
                      M=M+min-P[j];
                      break;
                  }
               }
           }
          //현재 첫 번째 자리가 0이될 수 없으으로 자릿수를 감소 시켜준다.(앞을 땡김)
           if(result[startIdx]==0) {
               startIdx++;
               M+=min;
           }else{ //종료조건은 앞 자리가 0이 아닐 때이다.
               break;
           }

          // 종료 조건
           if(startIdx==maxLength){
               System.out.println(0);
               return;
           }


       }

       String ans = "";
       for(int i=startIdx;i<maxLength;i++){
           ans+=Integer.toString(result[i]);
       }
        System.out.println(ans);
    }
}
