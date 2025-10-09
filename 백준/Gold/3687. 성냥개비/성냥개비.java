import java.io.*;
import java.util.*;

public class Main {
    static String[] dp = new String[101];
    static int[] cost = {2,3,4,5,5,5,6,6,6,7};
    static char[] digit = {'1','7','4','2','3','5','0','6','9','8'};

    static String minOf(String a, String b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.length() != b.length()) {
            return a.length() < b.length() ? a : b;
        }
        return a.compareTo(b) < 0 ? a : b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        dp[2] = "1";
        dp[3] = "7";
        dp[4] = "4";
        dp[5] = "2";
        dp[6] = "6";
        dp[7] = "8";

        for (int n = 8; n <= 100; n++) {
            String min = null;
            for (int k = 0; k < 10; k++) {
                int c = cost[k];
                if (n - c < 2) continue;
                if (dp[n - c] == null) continue;
                String num = dp[n - c] + digit[k];
                min = minOf(min, num);
            }
            dp[n] = min;
        }

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            String max = new String();
            if (n % 2 == 0) {
                char[] s = new char[n / 2];
                Arrays.fill(s, '1');
                max = new String(s);
            } else {
                char[] s = new char[(n - 3) / 2 + 1];
                s[0] = '7';
                Arrays.fill(s, 1, s.length, '1');
                max = new String(s);
            }
            sb.append(dp[n]).append(' ').append(max).append('\n');
        }
        System.out.print(sb.toString());
    }
}
