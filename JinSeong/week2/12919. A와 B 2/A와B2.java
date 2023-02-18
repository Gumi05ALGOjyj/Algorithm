import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class num12919AB2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String S,T;
    static int result;
    public static void main(String[] args) throws IOException {
        S= br.readLine();
        T= br.readLine();
        dfs(S,T);
        if(result>=1){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
    private static void dfs(String S, String T){
        if(S.length()==T.length()){
            if(S.equals(T)){
                result++;
            }
                return;
        }
        if(T.charAt(0)=='B'){
            String substring = T.substring(1);
            StringBuilder sb = new StringBuilder(substring);
            String string = sb.reverse().toString();
            dfs(S,string);
        }
        if(T.charAt(T.length()-1)=='A'){
            dfs(S, T.substring(0,T.length()-1));
        }
    }
}
