import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Deque<Integer> stack = new ArrayDeque<>();
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        ans = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                ans += 1;
            }

            if (y > 0 && (stack.isEmpty() || stack.peek() < y)) {
                stack.push(y);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() > 0) {
                ans += 1;
            }
        }

        System.out.println(ans);
    }
}