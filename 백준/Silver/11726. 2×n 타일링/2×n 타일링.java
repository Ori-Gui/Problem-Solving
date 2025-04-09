import java.io.*;

public class Main {
	static int N;
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N+1];
		dp[0] = 1;
		dp[1] = 1;

		for (int n = 2; n <= N; n++) {
			dp[n] = (dp[n-1] + dp[n-2]) % 10007;
		}
		System.out.println(dp[N]);
	}
}
