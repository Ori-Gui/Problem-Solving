import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int ans = 0;
    static char[][] board;
    static boolean[] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int x, int y, int depth) {
        if (depth > ans) ans = depth;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
            
            int idx = board[nx][ny] - 'A';
            if (v[idx]) continue; 

            v[idx] = true;
            dfs(nx, ny, depth + 1);
            v[idx] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        v = new boolean[26];
        v[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(ans);
    }
}
