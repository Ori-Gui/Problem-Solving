import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int[][] cheeseBox;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void bfs(int x, int y, int[][] box) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && box[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            cheeseBox = new int[N][N];
            int maxCount = 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cheeseBox[i][j] = sc.nextInt();
                }
            }


            for (int day = 0; day <= 100; day++) {
                visited = new boolean[N][N];
                int[][] tempBox = new int[N][N];
                int count = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        tempBox[i][j] = Math.max(0, cheeseBox[i][j] - day);
                    }
                }

                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        if (!visited[x][y] && tempBox[x][y] > 0) {
                            bfs(x, y, tempBox);
                            count++;
                        }
                    }
                }
                maxCount = Math.max(maxCount, count);
            }
            System.out.println("#" + t + " " + maxCount);
        }
    }
}
