import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

 
public class Main {
    
    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int T  = sc.nextInt();
      for(int i =0; i<T; i++) {
    	  int N = sc.nextInt();
    	  int count = 0;
    	  int[][] arr = new int[N][2];

    	  for(int j =0; j<N; j++) {
    		  arr[j][0] = sc.nextInt();
    		  arr[j][1] = sc.nextInt();
    	  }
    	  
    	  Arrays.sort(arr, new Comparator<int[]>() {
    		    @Override
    		    public int compare(int[] o1, int[] o2) {
    		    	return o1[0] - o2[0];
    		    }
    		});
    	  
    	  for(int j =1; j<N; j++) {
    		  if(arr[j][1]<arr[0][1])
    			  count++;
    	  }
    	  
    	  count += 1;
    	  
    	  int[][] res = new int[count][2];
    	  res[0][0] = arr[0][0];
    	  res[0][1] = arr[0][1];
    	  int len = 1;
    	  for(int j =1; j<N; j++) {
    		  if(arr[j][1]<res[0][1]) {
    			  res[len][0] = arr[j][0];
    			  res[len][1] = arr[j][1];
    			  len++;
    		  }
    	  }

    	  int result = count;
    	  
    	  for(int j =0; j<count; j++) {
    		  for(int a = 0; a<count; a++) {
    			  if((res[j][0]!=0)&&(res[j][1]!=0)) {
    				  if((res[j][0]<res[a][0])&&(res[j][1]<res[a][1])) {
        				  res[a][0] = 0;
        				  res[a][1] = 0;
        				  result -= 1;
        			  }
    			  }
    		  }
    	  } 

    	  System.out.println(result);

      }
      
      
    }
}
