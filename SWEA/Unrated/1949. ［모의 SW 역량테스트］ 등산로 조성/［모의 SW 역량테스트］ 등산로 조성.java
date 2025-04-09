import java.util.*;

public class Solution {
    static int T;
    static int N, K;
    static int[][] map;
    static int[][] copyMap;
    static boolean[][] v;
    static int highest;
    static List<Point> starts;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int dist;
    static int maxDist;

    // 좌표 클래스
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void dfs(Point p) {
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] || copyMap[p.x][p.y] <= copyMap[nx][ny]) {
                // 지도 범위 밖 or 이미 방문 or 낮은 지형 -> 높거나 같은 지형
                // 방문x
                continue;
            }
            v[nx][ny] = true;
            dist += 1;
            dfs(new Point(nx, ny));
            dist -= 1;
            v[nx][ny] = false;
        }
        maxDist = Math.max(maxDist, dist); // 등산로 길이 최대값 갱신
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            K = sc.nextInt();
            highest = 0;
            starts = new ArrayList<>();
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    highest = Math.max(highest, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (highest == map[i][j]) {
                        starts.add(new Point(i, j));
                    }
                }
            }

            maxDist = 0;

            for (Point p : starts) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        for (int k = 1; k <= K; k++) {
                            // 지도 복사
                            copyMap = new int[N][N];
                            for (int r = 0; r < N; r++) {
                                copyMap[r] = map[r].clone();
                            }
                            copyMap[i][j] -= k; // 공사
                            v = new boolean[N][N]; // 방문배열 초기화
                            dfs(p);
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, maxDist + 1);
        }
    }
}
