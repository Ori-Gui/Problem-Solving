import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] h;
    static int ans = 0;

    static boolean visible(int i, int j) {
        if (i < j) {
            for (int k = i + 1; k <= j - 1; k++) {
                long l = (h[k] - h[i]) * (j - i);
                long r = (h[j] - h[i]) * (k - i);
                if (l >= r) return false;
            }
        } else {
            for (int k = j + 1; k <= i - 1; k++) {
                long l = (h[k] - h[i]) * (j - i);
                long r = (h[j] - h[i]) * (k - i);
                if (l <= r) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        h = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (visible(i, j)) {
                    cnt += 1;
                }
            }
            if (cnt > ans) ans = cnt;
        }

        System.out.println(ans);
    }
}
