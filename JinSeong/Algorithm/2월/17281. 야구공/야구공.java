import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class num17281야구공 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] players;
    static boolean[] select;
    static int[] lineUp;
    static int result;
    public static void main(String[] args) throws IOException {
        //이닝 수
        N = Integer.parseInt(br.readLine());
        //플레이어 별 이닝에서 얻는 결과 저장 배열
        players = new int[N+1][10];

        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<10; j++){
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //플레이어 선택 저장 배열
        select = new boolean[10];
        //타자 순서 저장 배열
        lineUp = new int[10];

        //4번 타자는 첫번째 타자로 고정
        select[4]=true;
        lineUp[4]=1;

        perm(2);
        System.out.println(result);

    }
    private static void perm(int num) {
        //순서가 필요하기 때문에 순열
        if (num == 10) {
            //순서 다 정했으면 play
            play();
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (select[i]) {
                //골랐던애면 continue
                continue;
            }
            select[i] = true;
            lineUp[i] = num;
            perm(num + 1);
            select[i] = false;
        }
    }

    private static void play() {

        int score = 0;
        int startPlayer = 1;
        boolean[] base;

        for (int i = 1; i < N+1; i++) {
            int outCnt = 0;
            base = new boolean[4];

            outer: while (true) {
                //나중에 break outer 써버리면 해당 while문 break
                for (int j = startPlayer; j < 10; j++) {
                    //다음이닝 되면 순서 이어가기
                    int hitter = players[i][lineUp[j]];

                    switch (hitter) {
                        case 0: // out.
                            outCnt++;
                            break;
                        case 1: // 1루타
                            for (int k = 3; k >= 1; k--) {
                                if (base[k]) {
                                    if (k == 3) { // 3루 => 홈
                                        score++; // 점수 획득
                                        base[k] = false; // 3루 빔
                                        continue;
                                    }

                                    // 1, 2루에 => 다음 base
                                    base[k] = false;
                                    base[k + 1] = true;
                                }
                            }
                            base[1] = true; // 1루
                            break;
                        case 2: // 2루타
                            for (int k = 3; k >= 1; k--) {
                                if (base[k]) {
                                    if (k == 3 || k == 2) {
                                        score++;
                                        base[k] = false;
                                        continue;
                                    }
                                    base[k] = false;
                                    base[k + 2] = true;
                                }
                            }
                            base[2] = true;
                            break;
                        case 3: // 3루타
                            for (int k = 3; k >= 1; k--) {
                                if (base[k]) {
                                    score++;
                                    base[k] = false;
                                }
                            }

                            base[3] = true; // 홈에서 3루로 진루.
                            break;
                        case 4: // 홈런
                            for (int k = 1; k <= 3; k++) {
                                if (base[k]) {
                                    score++;
                                    base[k] = false;
                                }
                            }
                            score++;
                            break;
                    }

                    if (outCnt == 3) {
                        //아웃3개 => 이닝 종료 =>다음 타자부터 시작
                        startPlayer = j + 1;
                        if (startPlayer == 10) {
                            startPlayer = 0;
                        }
                        break outer;
                    }
                }
                //9번 타자까지 다치고 1번타자로 이어갈 때
                startPlayer = 1;
            }
        }

        //최대 점수 result
        result = result<=score ? score : result;
    }

}
