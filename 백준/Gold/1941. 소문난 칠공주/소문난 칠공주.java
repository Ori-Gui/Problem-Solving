import java.util.*;
import java.io.*;

public class Main {
	static char[][] arr = new char[5][5];
    static int cnt = 0;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static void dfs(int d, int start, int sCount, int[] sel) {
		if (d == 7) {
			if (sCount < 4) return;
            if (bfs(sel)) {
                cnt++;
            }
            return;
		}

		for (int i = start; i < 25; i++) {
            int x = i / 5, y = i % 5;
            if (arr[x][y] == 'S') {
                sel[d] = i;
                dfs(d + 1, i + 1, sCount + 1, sel);
            } else {
                if (d - sCount + 1 >= 4) continue;
                sel[d] = i;
                dfs(d + 1, i + 1, sCount, sel);
            }
		}
	}

	public static boolean bfs(int[] sel) {
        boolean[][] arr2 = new boolean[5][5];
        for (int s : sel) {
            arr2[s/5][s%5] = true;
        }
        boolean[][] visited = new boolean[5][5];
		Deque<int[]> queue = new ArrayDeque<>();

        int x = sel[0] / 5, y = sel[0] % 5;
        visited[x][y] = true;

		queue.offer(new int[] { x, y });
        int sevenCnt = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !visited[nx][ny] && arr2[nx][ny]) {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
                    sevenCnt++;
				}
			}
		}
        return sevenCnt == 7;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[5][5];

		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		dfs(0, 0, 0, new int[7]);
		System.out.println(cnt);
	}
}
