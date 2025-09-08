import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] init, target;

    static int simulate(boolean pressFirst) {
        char[] cur = Arrays.copyOf(init, N);
        int cnt = 0;

        if (pressFirst) {
            press(cur, 0);
            cnt += 1;
        }

        for (int i = 1; i < N; i++) {
            if (cur[i - 1] != target[i - 1]) {
                press(cur, i);
                cnt += 1;
            }
        }

        if (cur[N - 1] != target[N - 1]) return Integer.MAX_VALUE;
        return cnt;
    }

    static void press(char[] arr, int idx) {
        for (int j = idx - 1; j <= idx + 1; j++) {
            if (0 <= j && j < N) {
                arr[j] = (arr[j] == '0') ? '1' : '0';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        int res0 = simulate(false);
        int res1 = simulate(true);

        int ans = Math.min(res0, res1);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
