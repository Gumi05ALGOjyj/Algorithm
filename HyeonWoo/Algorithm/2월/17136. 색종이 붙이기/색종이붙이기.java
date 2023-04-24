import java.io.*;
import java.util.*;
public class Main {
    static int[][] board = new int[10][10];   // 격자판을 저장할 2차원 배열
    static int[] start = new int[2];          // 가장 처음 1이 나오는 좌표
    static int[] confetti;                    // 0: 사용한 색종이 개수, 1 ~ 5: 각 크기 별 남은 색종이 개수
    static int left = 0;                      // 남은 1의 개수: 남은 채워야 할 크기
    static int min = Integer.MAX_VALUE;       // 결과 값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        start[0] = -1;                                            // 시작 좌표를 -1로 초기화

        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());   // 격자판 생성 및 저장

                if(board[i][j] == 1) {                            // 1이라면
                    left++;                                       // 남은 채워야 할 크기 +1

                    if(start[0] == -1) {                          // 아직 시작 좌표가 비어있다면 최신화
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }
        }

        confetti = new int[6];                                    // 색종이 크기 별 남은 수 초기화
        for(int j = 1; j <= 5; j++) confetti[j] = 5;

        searchBoard(start[0], start[1]);                          // 시작 좌표부터 탐색 

        if(start[0] == -1) min = 0;                               // 1이 아예 없다면 0으로 결과 수정
        else if(min == Integer.MAX_VALUE) min = -1;               // 채울 수 없다면 -1로 결과 수정

        System.out.print(min);                                    // 결과 출력
    }

    static void searchBoard(int x, int y) {                       // 백트래킹으로 색종이로 채울 수 있는 모든 경우를 확인
        if(left == 0) {                                           // 남은 1이 없다면
            min = Math.min(min, confetti[0]);                     // 결과값 최신화
            return;
        }

        if(x == -1) return;                                       // 더 이상 확인 할 수 없는 좌표라면 종료

        if(board[x][y] == 1) {                                    // 1이라면
            for(int size = 5; size > 0; size--) {                 // 크기가 큰 색종이부터 확인
                if(confetti[size] == 0 || x + size > 10 || y + size > 10 || left < (size * size)) continue;
                if(!check(x, y, size)) continue;                  // 해당 좌표에 해당 사이즈의 색종이를 둘 수 있는지 확인

                confetti[size]--;                                 // 색종이 개수 -1
                confetti[0]++;                                    // 사용한 색종이 개수 +1
                left -= (size * size);                            // 남은 1의 개수 -색종이 크기
                fill(x, y, size, 0);                              // 색종이를 덮은 만큼 채워줌

                int[] next = createNext(x, y, size);              // 다음 좌표 확인
                searchBoard(next[0], next[1]);                    // 백트래킹 탐색

                confetti[size]++;
                confetti[0]--;
                left += (size * size);
                fill(x, y, size, 1);
            }
        } else {                                                  // 0이라면 다음 좌표 확인 후 재귀 호출
            int[] next = createNext(x, y, 1);
            searchBoard(next[0], next[1]);
        }
    }

    static boolean check(int x, int y, int size) {      // 해당 좌표에 해당 사이즈의 색종이를 둘 수 있는지 확인하는 함수
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(board[i][j] == 0) return false;      // 0이 하나라도 있다면 false
            }
        }
        return true;
    }

    static void fill(int x, int y, int size, int value) {       // 색종이 크기만큼 value를 채우는 함수
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(board[i][j] != value) board[i][j] = value;
            }
        }
    }

    static int[] createNext(int x, int y, int size) {   // 다음으로 확인해야 할 좌표를 반환하는 함수
        int[] next = new int[2];
        next[0] = x;                                    // 현재 좌표에서 색종이를 채운 다음의 좌표
        next[1] = y + size;

        if(next[1] == 10) {                             // y가 범위를 벗어난다면
            if(next[0] == 9) {                          // 마지막 좌표라면
                next[0] = -1;                           // 종료를 체크할 값으로 수정
            } else {                                    // 마지막 좌표가 아니라면
                next[0]++;                              // 다음 행의 처음 좌표로 
                next[1] = 0;
            }
        }
        return next;
    }
}
