import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int M;
    static int[][] noBurgers;
    static int cnt;

    static void dfs(int idx, boolean[] isSel) {
        if (idx == N) {
            boolean burgerFlag = true;

            for (int i = 0; i < M; i++) {
                int x1 = noBurgers[i][0] - 1;
                int x2 = noBurgers[i][1] - 1;
                if (isSel[x1] && isSel[x2]) {
                    burgerFlag = false;
                    break;
                }
            }
            if (burgerFlag) {
                cnt++;
            }
            return;
        }

        isSel[idx] = true;
        dfs(idx + 1, isSel);

        isSel[idx] = false;
        dfs(idx + 1, isSel);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            noBurgers = new int[M][2];
            cnt = 0;

            for (int i = 0; i < M; i++) {
                noBurgers[i][0] = sc.nextInt();
                noBurgers[i][1] = sc.nextInt();
            }

            dfs(0, new boolean[N]);
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
