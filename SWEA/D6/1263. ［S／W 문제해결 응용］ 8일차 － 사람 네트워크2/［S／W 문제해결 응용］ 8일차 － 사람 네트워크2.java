import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int[][] networks;
	static final int INF = 1000000;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			N = Integer.parseInt(st.nextToken());
			networks = new int[N][N];
			ans = INF;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					networks[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && networks[i][j] == 0) {
						networks[i][j] = INF;
					}
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						networks[i][j] = Math.min(networks[i][k] + networks[k][j], networks[i][j]);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += networks[i][j];
				}
				ans = Math.min(ans, sum);
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
