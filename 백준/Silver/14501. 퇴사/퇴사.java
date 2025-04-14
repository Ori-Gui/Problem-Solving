import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] t = new int[N];
        int[] p = new int[N];

        for (int i = 0; i < N; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        int[] dp = new int[N+1];

        for (int i = 0; i < N; i++) {
            dp[i+1] = Math.max(dp[i+1], dp[i]);

            if (i + t[i] <= N) {
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i] + p[i]);
            }
        }
        System.out.println(dp[N]);
    }
}
