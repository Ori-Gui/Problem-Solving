import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int cnt = 0;
        int limit = (int) Math.sqrt(n);

        for (int a = 1; a <= limit; a++) {
            int b = n / a;
            cnt += (b - a + 1);
        }
        System.out.println(cnt);
    }
}
