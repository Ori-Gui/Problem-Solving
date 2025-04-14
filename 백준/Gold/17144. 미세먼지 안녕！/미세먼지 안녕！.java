import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int airTop;
    static int airBottom;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();
        map = new int[R][C];
        airTop = 0;
        airBottom = 1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -1) {
                    airBottom = i;
                }
            }
        }
        airTop = airBottom - 1;

        for (int t = 0; t < T; t++) {
            int[][] tempMap = new int[R][C]; // 미세먼지 확산 동시 발생 처리

            tempMap[airTop][0] = -1;
            tempMap[airBottom][0] = -1;

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        int dust = map[i][j];
                        int spread = dust / 5;
                        int cnt = 0;

                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] >= 0) {
                                tempMap[nx][ny] += spread;
                                cnt++;
                            }
                        }
                        tempMap[i][j] += dust - (spread * cnt);
                    }
                }
            }
            map = tempMap;

            // 공기 청정기 Top (반시계)
            // 좌 (아래쪽 방향): (airTop-1, 0) ~ (1, 0)
            for (int i = airTop - 1; i > 0; i--) {
                map[i][0] = map[i - 1][0];
            }
            // 상 (왼쪽 방향): (0, 0) ~ (0, C-2)
            for (int j = 0; j < C - 1; j++) {
                map[0][j] = map[0][j + 1];
            }
            // 우 (위쪽 방향): (0, C-1) ~ (airTop-1, C-1)
            for (int i = 0; i < airTop; i++) {
                map[i][C - 1] = map[i + 1][C - 1];
            }
            // 하 (오른쪽 방향): (airTop, C-1) ~ (airTop, 2)
            for (int j = C - 1; j > 1; j--) {
                map[airTop][j] = map[airTop][j - 1];
            }
            // 정화된 공기: (airTop, 1) 0처리
            map[airTop][1] = 0;

            // 공기 청정기 Bottom (시계)
            // 좌 (위쪽 방향): (airBottom+1, 0) ~ (R-1, 1)
            for (int i = airBottom + 1; i < R - 1; i++) {
                map[i][0] = map[i + 1][0];
            }
            // 하 (왼쪽 방향): (R-1, 0) ~ (R-1, C-2)
            for (int j = 0; j < C - 1; j++) {
                map[R - 1][j] = map[R - 1][j + 1];
            }
            // 우 (아래쪽 방향): (R-1, C-1) ~ (airBottom+1, C-1)
            for (int i = R - 1; i > airBottom; i--) {
                map[i][C - 1] = map[i - 1][C - 1];
            }
            // 상 (오른쪽 방향): (airBottom, C-1) ~ (airBottom, 2)
            for (int j = C - 1; j > 1; j--) {
                map[airBottom][j] = map[airBottom][j - 1];
            }
            // 정화된 공기: (airBottom, 1) 0처리
            map[airBottom][1] = 0;
        }

        sum = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }
        System.out.println(sum);
    }
}
