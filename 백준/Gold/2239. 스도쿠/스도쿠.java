import java.io.*;

public class Main {
    static int[][] a = new int[9][9];
    static int[] row = new int[9];
    static int[] col = new int[9];
    static int[] box = new int[9];

    static int K = 0;
    static int[] x = new int[81];
    static int[] y = new int[81];

    static boolean dfs(int k) {
        if (k == K) return true;

        int r = x[k];
        int c = y[k];
        int b = (r / 3) * 3 + (c / 3);

        int used = row[r] | col[c] | box[b];
        int avail = (~used) & 1022; // 11 1111 1110

        for (int d = 1; d <= 9; d++) {
            int bit = 1 << d;
            if ((avail & bit) == 0) continue;

            a[r][c] = d;
            row[r] |= bit;
            col[c] |= bit;
            box[b] |= bit;

            if (dfs(k + 1)) return true;
            
            row[r] ^= bit;
            col[c] ^= bit;
            box[b] ^= bit;
            a[r][c] = 0;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                a[i][j] = s.charAt(j) - '0';
                if (a[i][j] == 0) {
                    x[K] = i;
                    y[K] = j;
                    K++;
                } else {
                    int bit = 1 << a[i][j];
                    row[i] |= bit;
                    col[j] |= bit;
                    box[(i / 3) * 3 + (j / 3)] |= bit;
                }
            }
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(a[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
