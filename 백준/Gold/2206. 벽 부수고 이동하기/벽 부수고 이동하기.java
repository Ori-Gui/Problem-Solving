import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][][] dist;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dist = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j][0] = -1;
                dist[i][j][1] = -1;
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0});
        dist[0][0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int b = cur[2];
            int d = dist[x][y][b];

            if (x == N - 1 && y == M - 1) {
                System.out.println(d);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == '0' && dist[nx][ny][b] == -1) {
                    dist[nx][ny][b] = d + 1;
                    q.add(new int[]{nx, ny, b});
                }
                else if (map[nx][ny] == '1' && b == 0 && dist[nx][ny][1] == -1) {
                    dist[nx][ny][1] = d + 1;
                    q.add(new int[]{nx, ny, 1});
                }
            }
        }
        System.out.println(-1);
    }
}
