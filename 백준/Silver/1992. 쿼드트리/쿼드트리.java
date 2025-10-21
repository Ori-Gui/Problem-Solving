import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int x, int y, int size) {
        boolean check = true;

        L:
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[x][y] != map[i][j]) {
                    check = false;
                    break L;
                } 
            }
        }

        if (check) {
            sb.append(map[x][y]);
            return;
        } else {
            int half = size / 2;
            sb.append("(");
            dfs(x, y, half);
            dfs(x, y + half, half);
            dfs(x + half, y, half);
            dfs(x + half, y + half, half);
            sb.append(")");
        } 
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = line[j] - '0';
            }
        }

        dfs(0, 0, N);

        System.out.println(sb.toString());
    }
}
