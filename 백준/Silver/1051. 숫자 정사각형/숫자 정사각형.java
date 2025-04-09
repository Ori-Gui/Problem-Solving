import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}
		
		int max = 0;
		
		for (int s = Math.min(n, m); s > 0; s--) {
			int k = s - 1;
			for (int i = 0; i < n - k; i++) {
				for (int j = 0; j < m - k; j++) {
					if (arr[i][j] == arr[i][j+k] && arr[i][j] == arr[i+k][j] && 
						arr[i][j] == arr[i+k][j+k]) {
						max = Math.max(max, s*s);
					}
				}
			}
		}
		System.out.println(max);
	}
}
