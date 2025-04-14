import java.util.*;

public class Main {

    static int N;
    static int[] nums;
    static int[] operators = new int[4];

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        for(int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt();  // +, -, *, /
        }

        dfs(1, nums[0], operators[0], operators[1], operators[2], operators[3]);

        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int idx, int cur, int plus, int minus, int mul, int div) {
        if (idx == N) {
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }

        if (plus > 0) {
            dfs(idx + 1, cur + nums[idx], plus - 1, minus, mul, div);
        }
        if (minus > 0) {
            dfs(idx + 1, cur - nums[idx], plus, minus - 1, mul, div);
        }
        if (mul > 0) {
            dfs(idx + 1, cur * nums[idx], plus, minus, mul - 1, div);
        }
        if (div > 0) {
            dfs(idx + 1, cur / nums[idx], plus, minus, mul, div - 1);
        }
    }
}
