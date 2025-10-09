import java.io.*;
import java.util.*;

public class Main {
    static final int EMPTY = 0;
    static final int WALL  = 6;
    static final int SEEN  = 7;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = { 0, 1, 0,-1};

    // CCTV 타입별 가능한 방향 세트
    // 1: 한 방향 / 2: 반대 두 방향 / 3: 직각 두 방향 / 4: 세 방향 / 5: 네 방향
    static final int[][][] DIRS = {
            {},
            { {0}, {1}, {2}, {3} },
            { {0,2}, {1,3} },
            { {0,1}, {1,2}, {2,3}, {3,0} },
            { {3,0,1}, {0,1,2}, {1,2,3}, {2,3,0} },
            { {0,1,2,3} }
    };

    static int N, M;
    static int[][] board;
    static List<CCTV> cams = new ArrayList<>();
    static int best = Integer.MAX_VALUE;

    static class CCTV {
        int x, y, type;
        CCTV(int x, int y, int type) { 
            this.x = x; 
            this.y = y; 
            this.type = type; 
        }
    }

    // idx번째 CCTV 방향 정하기
    static void dfs(int idx) {
        if (idx == cams.size()) {
            best = Math.min(best, countBlind());
            return;
        }

        CCTV cam = cams.get(idx);
        for (int[] dirs : DIRS[cam.type]) {
            List<int[]> painted = new ArrayList<>();
            watch(cam.x, cam.y, dirs, painted);
            dfs(idx + 1);
            undo(painted);
        }
    }

    // 주어진 방향 세트로 감시선 쏘기. 새로 칠한 좌표를 painted에 기록
    static void watch(int x, int y, int[] dirs, List<int[]> painted) {
        for (int d : dirs) {
            int nx = x;
            int ny = y;
            while (true) {
                nx += dx[d];
                ny += dy[d];
                if (!inBounds(nx, ny) || board[nx][ny] == WALL) break;

                if (board[nx][ny] == EMPTY) {
                    board[nx][ny] = SEEN;
                    painted.add(new int[]{nx, ny});
                }
            }
        }
    }

    // 칠한 좌표들 원복
    static void undo(List<int[]> painted) {
        for (int[] p : painted) {
            board[p[0]][p[1]] = EMPTY;
        }
    }

    static int countBlind() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == EMPTY) cnt++;
            }
        }
        return cnt;
    }

    static boolean inBounds(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= board[i][j] && board[i][j] <= 5) {
                    cams.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        dfs(0);
        System.out.println(best);
    }
}
