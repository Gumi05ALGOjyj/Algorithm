import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static class Point implements Comparable<Point> {
        int location;
        int adj;


        public Point(int location, int adj) {
            this.location = location;
            this.adj = adj;
        }


        @Override
        public String toString() {
            return "Point{" +
                    "location=" + location +
                    ", adj=" + adj +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            return this.location - o.location;
        }
    }

    static Point[] country;
    static long totalPeople = 0;
    static long rightSum = 0;
    static long leftSum =0 ;
    static long mySum = 0;
    static long minDiif = Integer.MAX_VALUE;
    static long postLocation;
    static long mid;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        country = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int location = Integer.parseInt(st.nextToken());
            int adj = Integer.parseInt(st.nextToken());
            totalPeople += adj;
            country[i] = new Point(location, adj);

        }
        mid = totalPeople / 2;
        Arrays.sort(country);

        int idx = 0;
        while (idx < N) {
            //1. 현재 나의 위치에 우체국을 세운다고 가정한다.
            int cur_postLocation = country[idx].location;

            //2. 나랑 같은 위치에 마을이 존재할 수 있으므로 idx를 이동시켜주면서 mySum을 저장해준다.
            mySum = country[idx].adj;
            for (int i = idx + 1; i < N; i++) {
                if (country[idx].location != country[i].location) {
                    idx = i-1;
                    break;
                } else {
                    mySum += country[i].adj;
                }
            }

            rightSum = totalPeople-(leftSum+mySum);
            long currDiff = Math.abs(Math.abs(rightSum-leftSum));
            // minDiif = Math.min(currDiff,minDiif);
            if(currDiff<minDiif){
                minDiif = currDiff;
                postLocation=cur_postLocation;
            }
            leftSum += mySum;
             //System.out.println("cur idx = "+idx + " cur_minDiff = "+currDiff + " cur_postLocation = "+postLocation);
            idx+=1;
        }

        System.out.println(postLocation);
    }
}
