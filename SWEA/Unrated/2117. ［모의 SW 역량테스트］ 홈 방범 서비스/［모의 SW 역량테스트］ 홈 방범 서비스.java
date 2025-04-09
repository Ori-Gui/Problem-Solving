import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N, M;
	static int[][] city;
	static List<Home> homes;
	static int maxC;
	
	static class Home {
		int x;
		int y;
		
		Home(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			city = new int[N][N];
			homes = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
					if (city[i][j] == 1) {
						homes.add(new Home(i, j));
					}
				}
			}
			
			maxC = 0;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					for (int k = 1; k <= N + 1; k++) {
						int cost = k*k + (k-1)*(k-1);
						int count = 0;
						
						for (Home home : homes) {
							if (Math.abs(x-home.x) + Math.abs(y-home.y) < k) {
								count += 1;
							}
						}
						if (count * M >= cost) {
							maxC = Math.max(maxC, count);
						};
					}	
				}
			}
			System.out.printf("#%d %d\n", tc, maxC);
		}
	}
}
