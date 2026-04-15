import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N, K;
	static int[] volums;
	static int[] costs;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			volums = new int[N+1];
			costs = new int[N+1];
			dp = new int[N+1][K+1];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				volums[i] = Integer.parseInt(st.nextToken());
				costs[i] =Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				for (int v = 1; v <= K; v++) {
					if (v >= volums[i]) {
						dp[i][v] = Math.max(dp[i-1][v], dp[i-1][v-volums[i]] + costs[i]);
					} else {
						dp[i][v] = dp[i-1][v];
					}
				}
			}			
			System.out.printf("#%d %d\n", tc, dp[N][K]);
		}
	}
}
