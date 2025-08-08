import java.io.*;
import java.util.*;

public class Main {
    static int N, D;
    static List<ShortCut> shortcuts = new ArrayList<>();
    static int[] dp;

    static class ShortCut {
        int s;
        int e;
        int d;

        ShortCut(int s, int e, int d) {
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dp = new int[D + 1];

        for (int i = 1; i <= D; i++) {
            dp[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            shortcuts.add(new ShortCut(s, e, d));
        }

        shortcuts.sort(Comparator.comparingInt((ShortCut c) -> c.e));

        for (int i = 0; i < N; i++) {
            ShortCut cur = shortcuts.get(i);
            if (cur.e <= D) {
                dp[cur.e] = Math.min(dp[cur.e], dp[cur.s] + cur.d);
                for (int j = cur.e + 1; j <= D; j++) {
                    dp[j] = Math.min(dp[j], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[D]);
    }
}