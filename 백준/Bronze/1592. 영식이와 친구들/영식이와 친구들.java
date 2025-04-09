import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();

        int[] counts = new int[N];
        int cur = 0;
        int ballCount = 0;

        counts[cur] += 1;

        while (counts[cur] != M) {
            if (counts[cur] % 2 == 1) {
                cur = (cur + L) % N;
            } else if (counts[cur] % 2 == 0) {
                cur = (cur - L + N) % N;
            }

            ballCount += 1;
            counts[cur] += 1;
        }

        System.out.println(ballCount);
    }
}
