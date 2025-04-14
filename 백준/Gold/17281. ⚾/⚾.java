import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] baseball;
    static boolean[] visited;
    static int maxScore = 0;

    static ArrayList<int[]> permutations = new ArrayList<>();

    // 9명 선수 순열 만들기
    static void makePermutation(int idx, int[] order) {
        if (idx == 9) {
            permutations.add(order.clone());
            return;
        }
        // 4번 타자(idx==3), 1번 선수(p==0) 고정
        // order[idx] = 0; 배열 0으로 초기화 되어있어서 건너뛰기
        if (idx == 3) {
            makePermutation(idx + 1, order);
            return;
        }

        // 나머지 2~9번 선수(1~8)를 순열로 채움
        for (int p = 1; p < 9; p++) {
            if (!visited[p]) {
                visited[p] = true;
                order[idx] = p;
                makePermutation(idx + 1, order);
                visited[p] = false;
            }
        }
    }

    public static int playBaseball(int[] order) {
        int score = 0;
        int k = 0;

        for (int i = 0; i < N; i++) {
            int out = 0;
            boolean[] base = new boolean[3];

            while (out < 3) {
                int hit = baseball[i][order[k]];

                // 아웃
                if (hit == 0) {
                    out++;
                    // 1루타
                } else if (hit == 1) {
                    // 3루 -> 홈인
                    if (base[2]) {
                        score++;
                        base[2] = false;
                    }
                    // 2루 -> 3루
                    if (base[1]) {
                        base[2] = true;
                        base[1] = false;
                    }
                    // 1루 -> 2루
                    if (base[0]) {
                        base[0] = false;
                        base[1] = true;
                    }
                    // 타자 -> 1루
                    base[0] = true;
                    // 2루타
                } else if (hit == 2) {
                    // 3루 -> 홈인
                    if (base[2]) {
                        score++;
                        base[2] = false;
                    }
                    // 2루 -> 홈인
                    if (base[1]) {
                        score++;
                        base[1] = false;
                    }
                    // 1루 -> 3루
                    if (base[0]) {
                        base[0] = false;
                        base[2] = true;
                    }
                    // 타자 -> 2루
                    base[1] = true;
                    // 3루타
                } else if (hit == 3) {
                    // 3루 -> 홈인
                    if (base[2]) {
                        score++;
                        base[2] = false;
                    }
                    // 2루 -> 홈인
                    if (base[1]) {
                        score++;
                        base[1] = false;
                    }
                    // 1루 -> 홈인
                    if (base[0]) {
                        score++;
                        base[0] = false;
                    }
                    // 타자 -> 3루
                    base[2] = true;
                    // 홈런
                } else if (hit == 4) {
                    for (int j = 0; j < 3; j++) {
                        score += (base[j] ? 1 : 0);
                    }
                    score++;
                    base[2] = false;
                    base[1] = false;
                    base[0] = false;
                }
                k = (k + 1) % 9;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        baseball = new int[N][9];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 9; j++) {
                baseball[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[9];
        visited[0] = true;

        makePermutation(0, new int[9]);

        for (int[] order : permutations) {
            maxScore = Math.max(maxScore, playBaseball(order));
        }

        System.out.println(maxScore);
    }
}