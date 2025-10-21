import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[100001];
        Arrays.fill(dp, 100000);

        dp[2] = 1;
        dp[4] = 2;
        dp[5] = 1;
        
        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i], dp[i-2] + 1);
            dp[i] = Math.min(dp[i], dp[i-5] + 1);
        }

        System.out.println(dp[N] == 100000 ? -1 : dp[N]);
    }
}
