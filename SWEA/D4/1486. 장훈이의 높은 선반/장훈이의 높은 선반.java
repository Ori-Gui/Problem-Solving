import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int B;
    static int[] heights;
    static int minSum;

    static void dfs(int idx, int start, int sum) {
        if (sum >= B) {
            minSum = Math.min(minSum, sum);
            return;
        }
        for (int i = start; i < N; i++) {
            sum += heights[i];
            dfs(idx + 1, i + 1, sum);
            sum -= heights[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            B = sc.nextInt();
            heights = new int[N];

            for (int i = 0; i < N; i++) {
                heights[i] = sc.nextInt();
            }

            minSum = Integer.MAX_VALUE;
            dfs(0, 0, 0);

            System.out.printf("#%d %d\n", t, minSum - B);
        }
    }
}
