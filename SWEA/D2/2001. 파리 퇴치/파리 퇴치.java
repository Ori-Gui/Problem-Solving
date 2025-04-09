import java.util.*;

public class Solution {
    static int T;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] flies = new int[N][N];
            int maxV = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    flies[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < N - M + 1; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    int sum = 0;
                    for (int x = 0; x < M; x++) {
                        for (int y = 0; y < M; y++) {
                            sum += flies[i + x][j + y];
                        }
                    }
                    maxV = Math.max(maxV, sum);
                }
            }

            System.out.printf("#%d %d\n", t, maxV);
        }
    }
}
