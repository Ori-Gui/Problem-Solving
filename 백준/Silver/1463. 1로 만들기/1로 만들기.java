import java.io.*;

public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];

		for (int i = 0; i <= N; i++) {
			dp[i] = i - 1;
		}

		for (int n = 2; n <= N; n++) {
			if (n % 2 == 0) dp[n] = Math.min(dp[n], dp[n / 2] + 1);
			if (n % 3 == 0) dp[n] = Math.min(dp[n], dp[n / 3] + 1);
			dp[n] = Math.min(dp[n], dp[n-1] + 1);
		}
		System.out.println(dp[N]);
	}
}
