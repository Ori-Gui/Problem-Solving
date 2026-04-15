import java.util.*;

public class Main {
    static int N;
    static int[][] S;
    static ArrayList<int[]> startLink;
    static int minV = Integer.MAX_VALUE;

    public static void dfs(int idx, int start, int[] sel) {
        if (idx == N / 2) {
            startLink.add(sel.clone());
            return;
        }
        for (int i = start; i < N; i++) {
            sel[idx] = i;
            dfs(idx + 1, i + 1, sel);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = new int[N][N];
        startLink = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                S[i][j] = sc.nextInt();
            }
        }
        
        dfs(0, 0, new int[N / 2]);

        int size = startLink.size();

        for (int i = 0; i < size / 2; i++) {
            int sumS = 0;
            int sumL = 0;
            int[] teamS = startLink.get(i);
            int[] teamL = startLink.get(size-1-i);

            int l = 0;
            int r = 1;

            while (l <= r) {
                int x = teamS[l];
                int y = teamS[r];
                int x2 = teamL[l];
                int y2 = teamL[r];

                sumS += S[x][y] + S[y][x];
                sumL += S[x2][y2] + S[y2][x2];

                r += 1;
                if (r == N / 2) {
                    l += 1;
                    r = l + 1;
                    if (r >= N / 2) {
                        break;
                    }
                }
            }
            minV = Math.min(minV, Math.abs(sumS - sumL));
        }
        System.out.println(minV);
    }
}
