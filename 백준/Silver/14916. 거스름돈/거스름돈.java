import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = -1;
        dp[1] = -1;
        dp[2] = 1;
        dp[3] = -1;
        dp[4] = 2;
        dp[5] = 1;
        
        for (int i = 6; i <= N; i++) {
            if (dp[i-2] != -1) {
                dp[i] = Math.min(dp[i], dp[i-2] + 1);
            }
            if (dp[i-5] != -1) {
                dp[i] = Math.min(dp[i], dp[i-5] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}
