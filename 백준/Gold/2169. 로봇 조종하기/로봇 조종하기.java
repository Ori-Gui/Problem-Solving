import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] a;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M];

        dp[0] = a[0][0];
        for (int j = 1; j < M; j++) {
            dp[j] = dp[j - 1] + a[0][j];
        }

        for (int i = 1; i < N; i++) {
            int[] l = new int[M];
            int[] r = new int[M];

            // 왼->오
            l[0] = dp[0] + a[i][0];
            for (int j = 1; j < M; j++) {
                l[j] = Math.max(dp[j], l[j - 1]) + a[i][j];
            }

            // 오->왼
            r[M - 1] = dp[M - 1] + a[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                r[j] = Math.max(dp[j], r[j + 1]) + a[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[j] = Math.max(l[j], r[j]);
            }
        }

        System.out.println(dp[M - 1]);
    }
}
