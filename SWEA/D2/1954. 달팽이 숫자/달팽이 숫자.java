import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int[][] snail = new int[N][N];
            // 우, 하, 좌, 상 순서
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};
            int d = 0, x = 0, y = 0;

            // 1부터 N*N까지 달팽이 모양으로 채우기
            for (int num = 1; num <= N * N; num++) {
                snail[x][y] = num;
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 범위 밖 or 이미 채워진 칸이면
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || snail[nx][ny] != 0) {
                    d = (d + 1) % 4; // 방향 전환
                    nx = x + dx[d];
                    ny = y + dy[d];
                }
                x = nx;
                y = ny;
            }
            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(snail[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
