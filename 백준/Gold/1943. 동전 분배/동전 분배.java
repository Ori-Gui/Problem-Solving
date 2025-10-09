import java.io.*;
import java.util.*;

public class Main {
    static final int T = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[] val = new int[N];
            int[] cnt = new int[N];
            int sum = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                val[i] = Integer.parseInt(st.nextToken());
                cnt[i] = Integer.parseInt(st.nextToken());
                sum += val[i] * cnt[i];
            }

            if (sum % 2 == 1) { 
                sb.append(0).append('\n');
                continue;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

            for (int i = 0; i < N; i++) {
                int v = val[i];
                int c = cnt[i];
                int k = 1;
                while (c > 0) {
                    int take = Math.min(k, c);
                    int w = v * take;
                    for (int s = target; s >= w; s--) {
                        if (dp[s - w]) dp[s] = true;
                    }
                    c -= take;
                    k *= 2;
                }
            }

            sb.append(dp[target] ? 1 : 0).append('\n');
        }

        System.out.print(sb.toString());
    }
}
