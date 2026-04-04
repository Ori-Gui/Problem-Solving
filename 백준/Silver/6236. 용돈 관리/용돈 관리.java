import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int[] pay;
    static int sum = 0;
    static int max = 0;

    private static boolean check(int k) {
        int cnt = 1;
        int remain = k;

        for (int i = 0; i < N; i++) {
            if (remain < pay[i]) {
                cnt += 1;
                remain = k;
            }
            remain -= pay[i];
        }
        return cnt <= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pay = new int[N];

        for (int i = 0; i < N; i++) {
            pay[i] = Integer.parseInt(br.readLine().strip());
            sum += pay[i];
            max = Math.max(max, pay[i]);
        }

        int l = max;
        int r = sum;
        int mid = (l + r) / 2;
        int ans = 0;

        while (l <= r) {
            if (check(mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            mid = (l + r) / 2;
        }

        System.out.println(ans);
    }
}