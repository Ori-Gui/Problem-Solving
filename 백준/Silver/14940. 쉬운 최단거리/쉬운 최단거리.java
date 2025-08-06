import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int sx, int sy) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Point> q = new ArrayDeque<>();
        visited[sx][sy] = true;
        dist[sx][sy] = 0;
        q.offer(new Point(sx, sy));

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];
        int sx = -1, sy = -1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
            }
        }

        bfs(sx, sy);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    sb.append(0);
                } else if (!visited[i][j]) {
                    sb.append(-1);
                } else {
                    sb.append(dist[i][j]);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
