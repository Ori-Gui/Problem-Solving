import java.io.*;
import java.util.*;

public class Main {
    static int N, K, W, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            for (int k = K; k >= W; k--) {
                dp[k] = Math.max(dp[k], dp[k - W] + V);
            }
        }

        System.out.println(dp[K]);
    }
}
