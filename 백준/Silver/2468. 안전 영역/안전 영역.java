import java.util.*;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[][] v;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int cnt;
	
	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} 
	
	static void bfs(Point p, int nongdo) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(p);
		v[p.x][p.y] = true;
		
		while (!queue.isEmpty()) {
			Point curP = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curP.x + dx[i];
				int ny = curP.y + dy[i];
				
				if (nx >= 0 && ny >= 0 && nx < N && ny < N && 
					!v[nx][ny] && arr[nx][ny] > nongdo) {
					queue.offer(new Point(nx, ny));
					v[nx][ny] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		v = new boolean[N][N];
		cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for (int k = 0; k <= 100; k++) {
			v = new boolean[N][N];
			int tempCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!v[i][j] && arr[i][j] > k) {
						bfs(new Point(i, j), k);
						tempCnt += 1;
					}
				}
			}
			cnt = Math.max(cnt, tempCnt);
		}
		
		System.out.println(cnt);
	}
}
