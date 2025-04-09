import java.util.*;

public class Solution {
    static int[][] ladder;
    static int[] dx = { -1, 0, 0 };
    static int[] dy = { 0, 1, -1 };
    static boolean[][] v;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int dfs(Point p) {
        int start = 0;
        Stack<Point> stack = new Stack<>();
        stack.push(p);
        v[p.x][p.y] = true;

        while (!stack.isEmpty()) {
            Point curP = stack.pop();

            if (curP.x == 0) {
                start = curP.y;
                break;
            }

            for (int i = 0; i < 3; i++) {
                int nx = curP.x + dx[i];
                int ny = curP.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 100 && ny < 100 && ladder[nx][ny] == 1 && !v[nx][ny]) {
                    stack.push(new Point(nx, ny));
                    v[nx][ny] = true;
                }
            }
        }
        return start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int t = 1; t <= 10; t++) {
            int T = sc.nextInt();
            ladder = new int[100][100];
            v = new boolean[100][100];
            Point end = new Point(0, 0);

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = sc.nextInt();
                    if (ladder[i][j] == 2) {
                        end = new Point(i, j);
                    }
                }
            }

            int start = dfs(end);

            System.out.printf("#%d %d\n", T, start);
        }
    }
}
