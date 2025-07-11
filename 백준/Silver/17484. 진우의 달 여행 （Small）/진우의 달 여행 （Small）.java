import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] space;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];
        dp = new int[N][M][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (i > 0) {
                    dp[i][j][0] = 600;
                    dp[i][j][1] = 600;
                    dp[i][j][2] = 600;
                } else {
                    dp[i][j][0] = space[i][j];
                    dp[i][j][1] = space[i][j];
                    dp[i][j][2] = space[i][j];
                    if (j == 0) {
                        dp[i][j][0] = 600;
                    } else if (j == M - 1) {
                        dp[i][j][2] = 600;
                    }
                }
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    dp[i][j][0] = 600;
                    dp[i][j][1] = dp[i-1][j][2] + space[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][0]) + space[i][j];
                } else if (j == M - 1) {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + space[i][j];
                    dp[i][j][1] = dp[i-1][j][0] + space[i][j];
                    dp[i][j][2] = 600;
                } else {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + space[i][j];
                    dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + space[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + space[i][j];
                }
            }
        }
    
        int min = 600;

        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                min = Math.min(min, dp[N-1][j][k]);
            }
        }
        System.out.println(min);
    }
}
