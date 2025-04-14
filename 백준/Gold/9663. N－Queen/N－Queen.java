import java.util.*;

public class Main {
    static int cnt = 0;
    static int N;
    // "|" 열에 퀸이 이미 놓였는지 표시 (col)
    static boolean[] col;
    // “/” 대각선에 퀸이 놓였는지 표시 (r + col)
    static boolean[] diag1;
    // “\” 대각선 (오른쪽 위에서 왼쪽 아래 방향)에 퀸이 놓였는지 표시 (r - col)
    static boolean[] diag2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        col = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];

        // 0번째 행부터 퀸 배치
        dfs(0);
        System.out.println(cnt);
    }

    // 행에 퀸을 배치, 재귀
    static void dfs(int r) {
        // 모든 행에 퀸을 배치했다면, 한 가지 해를 찾음
        if (r == N) {
            cnt++;
            return;
        }
        // 현재 행의 모든 열을 순회하며 퀸을 놓을 수 있는지 확인
        for (int c = 0; c < N; c++) {
            // "/" 대각선 배열의 N-1개는 x, y 좌표의 차가 음수가 되므로 N-1 더해 줌
            if (!col[c] && !diag1[r + c] && !diag2[r - c + N - 1]) {
                col[c] = true;
                diag1[r + c] = true;
                diag2[r - c + N - 1] = true;
                dfs(r + 1);
                col[c] = false;
                diag1[r + c] = false;
                diag2[r - c + N - 1] = false;
            }
        }
    }
}
