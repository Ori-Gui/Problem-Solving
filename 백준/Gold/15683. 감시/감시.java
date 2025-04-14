import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int map[][];
    static int minV = Integer.MAX_VALUE;
    // (우: 0, 하: 1, 좌: 2, 상: 3)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    // (1번: 4, 2번: 2, 3번: 4, 4번: 4, 5번: 1)
    static int[][][] dirs = {
            {},
            { { 0 }, { 1 }, { 2 }, { 3 } },
            { { 0, 2 }, { 1, 3 } },
            { { 0, 3 }, { 0, 1 }, { 1, 2 }, { 2, 3 } },
            { { 2, 3, 0 }, { 3, 0, 1 }, { 0, 1, 2 }, { 1, 2, 3 } },
            { { 0, 1, 2, 3 } }
    };

    static List<CCTV> cctvs;

    static class CCTV {
        int x;
        int y;
        int type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static void watch(int x, int y, int[][] map, int[] dir) {
        for (int d : dir) {
            int nx = x;
            int ny = y;
            while (true) {
                nx += dx[d];
                ny += dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6) {
                    break;
                }
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 7;
                }
            }
        }
    }

    static void dfs(int idx, int[][] map) {
        if (idx == cctvs.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        cnt += 1;
                    }
                }
            }
            minV = Math.min(minV, cnt);
            return;
        }

        CCTV cctv = cctvs.get(idx);

        for (int[] dir : dirs[cctv.type]) {
            int[][] copyMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                copyMap[i] = map[i].clone();
            }
            watch(cctv.x, cctv.y, copyMap, dir);
            dfs(idx + 1, copyMap);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        dfs(0, map);
        System.out.println(minV);
    }
}
