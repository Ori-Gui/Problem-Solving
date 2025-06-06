import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A;
	static int[] dp;
	static int maxL;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		dp = new int[N+1];
		maxL = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int n = 1; n <= N; n++) {
			dp[n] = 1;		
			for (int k = 1; k < n; k++) {
				if (A[k] > A[n]) {
					dp[n] = Math.max(dp[n], dp[k] + 1);
				} 
			}
		}
		
		for (int i = 1; i <= N; i++) {
			maxL = Math.max(maxL, dp[i]);
		}
		
		System.out.println(maxL);
	}
}
