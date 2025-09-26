import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] fireTime;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireTime = new int[R][C];
        dist = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                fireTime[i][j] = Integer.MAX_VALUE;
                dist[i][j] = -1;
            }
        }

        ArrayDeque<int[]> fq = new ArrayDeque<>();
        ArrayDeque<int[]> jq = new ArrayDeque<>();
        int jx = -1, jy = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') {
                    fq.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                } else if (map[i][j] == 'J') {
                    jx = i; jy = j;
                }
            }
        }

        while (!fq.isEmpty()) {
            int[] cur = fq.poll();
            int x = cur[0];
            int y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (map[nx][ny] == '#') continue;
                if (fireTime[nx][ny] != Integer.MAX_VALUE) continue;
                fireTime[nx][ny] = fireTime[x][y] + 1;
                fq.offer(new int[]{nx, ny});
            }
        }

        jq.offer(new int[]{jx, jy});
        dist[jx][jy] = 0;

        if (jx == 0 || jy == 0 || jx == R - 1 || jy == C - 1) {
            System.out.println(1);
            return;
        }

        while (!jq.isEmpty()) {
            int[] cur = jq.poll();
            int x = cur[0];
            int y = cur[1];
            int t = dist[x][y];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    System.out.println(t + 1);
                    return;
                }

                if (map[nx][ny] == '#') continue;
                if (dist[nx][ny] != -1) continue;
                int nt = t + 1;

                if (nt >= fireTime[nx][ny]) continue;

                dist[nx][ny] = nt;
                jq.offer(new int[]{nx, ny});
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
