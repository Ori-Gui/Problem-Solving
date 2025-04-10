import java.util.*;

public class Main {

    static int[][] cnt;

    public static int[] fibonacci(int n) {
        if (n == 0) {
            cnt[0] = new int[]{1, 0};
            return cnt[0];
        } else if (n == 1) {
            cnt[1] = new int[]{0, 1};
            return cnt[1];
        }

        if (cnt[n][0] != -1 && cnt[n][1] != -1) {
            return cnt[n];
        }

        int[] a = fibonacci(n - 1);
        int[] b = fibonacci(n - 2);
        cnt[n][0] = a[0] + b[0];
        cnt[n][1] = a[1] + b[1];
        return cnt[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            cnt = new int[N + 2][2];

            for (int i = 0; i <= N + 1; i++) {
                cnt[i][0] = -1;
                cnt[i][1] = -1;
            }

            int[] result = fibonacci(N);
            System.out.println(result[0] + " " + result[1]);
        }

        sc.close();
    }
}

