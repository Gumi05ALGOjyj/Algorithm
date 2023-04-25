import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    public static char result;
    static int N;
    public static void main(String[] args) throws IOException {
        N= Integer.parseInt(br.readLine());
        moo(N);
        System.out.println(result);
    }
    private static void moo(int num){
        int size = 3;
        int index=0;
        //moo
        if(num==1){
            result='m';
        }
        else if(num<=3){
            result = 'o';
        }
        else{
            while(size<num){//size 키우기 s(k-1)*2 + o(k+2개)+ m(1개) +1 (k는 전 index)
                size = size*2+index+4;
                index++;
            }
          
            //이전 사이즈
            int prev_size = (size-index-3)/2; 
            
            //10 5 10
            //이전 사이즈의 다음 인덱스는 무조건 m (11번째 인덱스)
            if(prev_size+1==num){
                result = 'm';
            }
            //이전 사이즈의 인덱스와 moo..o의 다음 인덱스는 길이를 잘라주고 다시 재귀 (16~25)
            else if(size-prev_size+1<=num){
                moo(num-(size-prev_size));
            }
            //o가 k+2개 있는 부분 (12~15)
            else{
                result='o';
            }
        }
    }
}
