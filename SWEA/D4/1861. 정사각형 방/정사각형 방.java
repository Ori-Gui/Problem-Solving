import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int[][] rooms;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int[][] cnts;
    static int maxCnt;
    static int A;
    static int cnt;

    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N && rooms[nx][ny] == (rooms[x][y] + 1)) {
                dfs(nx, ny);
                cnt += 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            rooms = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rooms[i][j] = sc.nextInt();
                }
            }

            maxCnt = 0;
            A = Integer.MAX_VALUE;
            cnts = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cnt = 1;
                    dfs(i, j);
                    cnts[i][j] = cnt;
                    maxCnt = Math.max(maxCnt, cnt);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cnts[i][j] == maxCnt) {
                        A = Math.min(A, rooms[i][j]);
                    }
                }
            }

            System.out.printf("#%d %d %d\n", t, A, maxCnt);
        }
    }
}
