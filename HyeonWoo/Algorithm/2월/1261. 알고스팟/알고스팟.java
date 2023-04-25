import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] line = st.nextToken().split("");

            for(int j = 0; j < m; j++) board[i][j] = Integer.parseInt(line[j]);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();                // 우선순위 큐 생성
        queue.add(new Node(0, 0, 0));                                     // 좌표와 해당 dlist값 삽입

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int[][] dlist = new int[n][m];                                    // dlist 배열 생성 및 값 Infinity로 초기화
        for(int i = 0; i < n; i++) {
            int[] temp = new int[m];
            Arrays.fill(temp, Integer.MAX_VALUE);

            dlist[i] = temp;
        }

        dlist[0][0] = 0;

        while(!queue.isEmpty()) {                                         // queue값이 빌 때 까지 반복
            Node posi = queue.poll();

            int x = posi.x;
            int y = posi.y;
            int item = posi.item;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                
                if(dlist[nx][ny] > item + board[nx][ny]) {                // dlist값보다 작다면
                    dlist[nx][ny] = item + board[nx][ny];                 // dlist값 최신화 및 queue에 삽입
                    queue.add(new Node(nx, ny, dlist[nx][ny]));
                }
            }
        }

        System.out.print(dlist[n - 1][m - 1]);
    }

    static class Node implements Comparable<Node> {
        public int x;
        public int y;
        public int item;

        public Node(int x, int y, int item) {
            this.x = x;
            this.y = y;
            this.item = item;
        }

        @Override
        public int compareTo(Node n) {
            return this.item - n.item;
        }
    }
}
