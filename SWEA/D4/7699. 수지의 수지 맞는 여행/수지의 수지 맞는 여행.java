import java.util.*;

public class Solution {
	static int T;
	static int R, C;
	static int maxCount;
	static char[][] island;
	static boolean[] visited;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void dfs(int x, int y, char[][] box, int cnt) {
		visited[(box[x][y]-'A')] = true;
		cnt += 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[(box[nx][ny]-'A')]) {
				visited[(box[nx][ny]-'A')] = true;
				dfs(nx, ny, box, cnt);
				visited[(box[nx][ny]-'A')] = false;
			}
		}
		maxCount = Math.max(maxCount, cnt);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			R = sc.nextInt();
			C = sc.nextInt();
			
			maxCount = 0;
			island = new char[R][C];
			visited = new boolean[26];
			
			for (int i = 0; i < R; i++) {
				String line = sc.next();
				for (int j = 0; j < C; j++) {
					island[i][j] = line.charAt(j);
				}
			}
			
			dfs(0, 0, island, 0);
			System.out.println("#" + t + " " + maxCount);
		}
	}
}
