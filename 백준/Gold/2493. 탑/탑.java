import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] tops = new int[n];
        int[] result = new int[n];
        Stack<int[]> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int h = tops[i];
            while (!stack.isEmpty() && stack.peek()[1] <= h) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek()[0] + 1;
            }
            stack.push(new int[]{i, h});
        }

        for (int k : result) {
            System.out.print(k + " ");
        }
    }
}
