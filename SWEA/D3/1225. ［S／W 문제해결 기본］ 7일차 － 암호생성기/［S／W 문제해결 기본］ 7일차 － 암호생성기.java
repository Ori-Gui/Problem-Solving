import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();

        for (int t = 1; t <= 10; t++) {
            int T = sc.nextInt();

            for (int i = 0; i < 8; i++) {
                deque.add(sc.nextInt());
            }

            int k = 1;

            while (true) {
                int cur = deque.poll() - k;
                if (cur <= 0) {
                    deque.add(0);
                    break;
                }
                deque.add(cur);
                k = (k + 1) % 5 != 0 ? (k + 1) % 5 : 5;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(T).append(" ");

            for (int code : deque) {
                sb.append(code).append(" ");
            }

            deque.clear();
            System.out.println(sb);
        }
    }
}
