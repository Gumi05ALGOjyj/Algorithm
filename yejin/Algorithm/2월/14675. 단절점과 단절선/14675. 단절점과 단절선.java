import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class Main {
	
	public static List[] list;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        list = new ArrayList[N+1];
        
        for(int i =0; i<N+1; i++)
        	list[i] = new ArrayList<Integer>();
        
        String[] str;
        int v1,v2;
        for(int i =0; i<N-1; i++) {
        	str = br.readLine().split(" ");
        	v1 = Integer.parseInt(str[0]);
        	v2 = Integer.parseInt(str[1]);
        	
        	list[v1].add(v2);
        	list[v2].add(v1);
        }
        
        int q = Integer.parseInt(br.readLine());
        
        for(int i =0; i<q; i++) {
        	str = br.readLine().split(" ");
        	
        	if(Integer.parseInt(str[0]) == 1) { // 단절점인가? (단절점 : 정점을 제거했을 때, 그 정점을 포함한 그래프가 2개로 나누어지는 경우)
        		if(list[Integer.parseInt(str[1])].size() == 1)
        			sb.append("no\n");
        		else
        			sb.append("yes\n");
        	}else { // 단절선인가? (단절선 : 간선을 제거했을 때, 간선이 포함된 그래프가 2개로 나누어지는 경우)
        		sb.append("yes\n");
        	}
        }
        
        System.out.println(sb.toString());
        
        
        
        
    }
    
  
    
}
