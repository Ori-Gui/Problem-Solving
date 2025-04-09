import java.util.*;

public class Main {

    /*
     * n = 0 -> 빵: 1 = 1
     * n = 1 -> 빵: 1, 좌: 1, 우: 1 = 3
     * n = 2 -> 빵: 3, 좌: 2, 우: 2 = 7
     * n = 3 -> 빵: 7, 좌: 5, 우: 5 = 17
     * n = 4 -> 빵: 17, 좌: 12, 우 :12 = 41
     * */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] dp = new int[N+1];

        dp[0] = 1;
        dp[1] = 3;

        for (int i = 2; i <= N; i++) {
            dp[i] = (2 * dp[i-1] + dp[i-2]) % 9901;
        }
        System.out.println(dp[N] % 9901);
    }
}
