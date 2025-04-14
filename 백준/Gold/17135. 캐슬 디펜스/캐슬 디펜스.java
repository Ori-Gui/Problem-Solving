import java.util.*;
import java.io.*;

public class Main {
    static int N, M, D;
    static int[][] castle;
    static int maxKill = 0;

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        castle = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0, 0, new int[3]);
        System.out.println(maxKill);
    }

    static int game(int[] archers) {
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = castle[i].clone();
        }

        int kill = 0;

        for (int t = 0; t < N; t++) {
            Set<Point> targets = new HashSet<>();

            for (int a : archers) {
                Point target = selectTarget(board, a);
                if (target != null) {
                    targets.add(target);
                }
            }

            for (Point p : targets) {
                if (board[p.x][p.y] == 1) {
                    kill++;
                    board[p.x][p.y] = 0;
                }
            }

            int[][] newBoard = new int[N][M];

            for (int i = N - 1; i >= 1; i--) {
                newBoard[i] = board[i - 1].clone();
            }

            Arrays.fill(newBoard[0], 0);

            board = newBoard;
        }
        return kill;
    }

    static Point selectTarget(int[][] board, int y) {
        int min = Integer.MAX_VALUE;
        int targetY = Integer.MAX_VALUE;
        int targetX = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    int dist = Math.abs(N - i) + Math.abs(y - j);
                    if (dist <= D) {
                        if (dist < min || (dist == min && j < targetY)) {
                            min = dist;
                            targetX = i;
                            targetY = j;
                        }
                    }
                }
            }
        }
        if (targetX == -1) return null;
        return new Point(targetX, targetY);
    }

    static void comb(int idx, int start, int[] archers) {
        if (idx == 3) {
            int kill = game(archers);
            maxKill = Math.max(maxKill, kill);
            return;
        }

        for (int i = start; i < M; i++) {
            archers[idx] = i;
            comb(idx + 1, i + 1, archers);
        }
    }
}