import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class num16637괄호추가하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] num;
    static char[] op;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        N= Integer.parseInt(br.readLine());
        String s = br.readLine();
        //숫자
        num = new int[N/2 +1];
        //연산자
        op = new char[N/2];
        int j=0;
        int k=0;

        for(int i=0; i<N; i++){
            //짝수번 숫자
            if(i%2==0){
                num[j++] = s.charAt(i)-'0';
            }
            //홀수번 연산자
            else{
                op[k++] = s.charAt(i);
            }
        }

        for(int i=0; i<=(N/2 +1)/2; i++){
            //괄호 최대 개수 => (N/2 +1)/2
            boolean[] select = new boolean[N/2+1];
            dfs(select,i,0,1);
        }
        System.out.println(max);

    }
    private static void dfs(boolean[] select, int n,int cnt, int index){
        if(cnt== n){
            int i=1;
            //첫 숫자 선택
            int res=num[0];
            while(i<N/2+1){
                //num개수 => N/2+1
                int a=0;
                if(select[i]){
                    switch(op[i]){
                        case '+':
                            a= num[i]+num[i+1];
                            break;
                        case '*':
                            a= num[i]*num[i+1];
                            break;
                        case '-':
                            a= num[i]-num[i+1];
                            break;
                    }
                }
                else{
                    a=num[i];
                }
                switch(op[i-1]){
                    case '+':
                        res+=a;
                        break;
                    case '*':
                        res*=a;
                        break;
                    case '-':
                        res-=a;
                        break;
                }

                if(select[i]){
                    //괄호 선택 괄호 안에 계산하고 숫자 두개 뛰어넘기(괄호 안은 위에서 계산했으니까)
                    i+=2;
                }
                else{
                    i++;
                }


            }
            max = Math.max(max,res);

            return;
        }
        for(int i=index; i<N/2; i++){
            select[i]=true;
            //머를 괄호로 선택할지 조합
            dfs(select, n,cnt+1,i+2);
            select[i]=false;
        }
    }
}
