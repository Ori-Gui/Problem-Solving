import java.util.*;

public class Main {
    static int N, r, c;

    static int dfs(int N, int r, int c) {
        if (N == 1) {
            return 2 * r + c;
        }
        int half = (int) Math.pow(2, N - 1);
        if (r < half && c < half) {
            return dfs(N - 1, r, c);
        } else if (r < half && c >= half) {
            return half * half + dfs(N - 1, r, c - half);
        } else if (r >= half && c < half) {
            return half * half * 2 + dfs(N - 1, r - half, c);
        } else if (r >= half && c >= half) {
            return half * half * 3 + dfs(N - 1, r - half, c - half);
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        System.out.println(dfs(N, r, c));
    }
}
