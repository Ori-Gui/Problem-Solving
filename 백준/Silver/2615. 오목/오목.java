import java.util.*;

public class Main {
    static int[][] board = new int[19][19];
    static int[] dx = {0, 1, 1, -1};
    static int[] dy = {1, 0, 1, 1};

    public static boolean omok(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int px = x - dx[i];
            int py = y - dy[i];
            if (px >= 0 && py >= 0 && px < 19 && py < 19 && board[px][py] == board[x][y]) {
                continue;
            }

            int cnt = 1;
            int nx = x;
            int ny = y;
            while (true) {
                nx += dx[i];
                ny += dy[i];

                if ((nx < 0 || ny < 0 || nx >= 19 || ny >= 19) || board[nx][ny] != board[x][y]) {
                    break;
                }
                cnt++;
            }

            if (cnt == 5) {
                System.out.println(board[x][y]);
                System.out.println((x + 1) + " " + (y + 1));

                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0 && omok(i, j)) {
                    return;
                }
            }
        }
        System.out.println(0);
    }
}
