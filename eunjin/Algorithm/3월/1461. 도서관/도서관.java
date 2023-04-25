import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int result;
    static ArrayList<Integer> minusList = new ArrayList<>();
    static ArrayList<Integer> plusList = new ArrayList<>();
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //책의 총 개수
        M = Integer.parseInt(st.nextToken());   //한번에 들 수 있는 책의 개수

        int input = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(st.nextToken());
            maxValue = Math.max(maxValue, Math.abs(input));
            if (input < 0) minusList.add(input);
            else plusList.add(input);
        }
        Collections.sort(minusList);
        Collections.sort(plusList);
        Collections.reverse(plusList);
        //Collections.reverse(minusList);

//        System.out.println(minusList.toString());
//        System.out.println(plusList.toString());
        for (int i = 0; i < minusList.size(); i += M) {

            result += Math.abs(minusList.get(i));
//            System.out.println("curindex = "+i+" "+Math.abs(minusList.get(i)));
            if (i + M > minusList.size() && i + 1 < minusList.size()) {
//                result += Math.abs(minusList.get(i + 1));
//                System.out.println(Math.abs(minusList.get(i)));
                break;
            }

        }

        for (int i = 0; i < plusList.size(); i += M) {
//            if (i + M > plusList.size()) {
//                result += Math.abs(plusList.get(i) * 2);
//                break;
//            }
//            result += Math.abs(plusList.get(i) * 2);

            result += plusList.get(i);
//            System.out.println(Math.abs(plusList.get(i)));
            if (i + M > plusList.size() && i + 1 < plusList.size()) {
//                result += plusList.get(i + 1);
//                System.out.println(Math.abs(plusList.get(i)));
                break;
            }
        }
        result *= 2;

        result -= maxValue;


        System.out.println(result);
    }
}
