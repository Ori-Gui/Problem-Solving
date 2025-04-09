import java.io.*;

public class Main {
	static int T;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
        dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int n = 4; n <= 10; n++) {
            dp[n] = dp[n-1] + dp[n-2] + dp[n-3];
        }

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
	}
}
