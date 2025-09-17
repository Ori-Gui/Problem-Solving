import java.io.*;

public class Main {
    static char[] s;
    static char[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        int n = s.length;
        int m = b.length;

        char[] stack = new char[n];
        int top = 0;
        char last = b[m - 1];

        for (int i = 0; i < n; i++) {
            char c = s[i];
            stack[top++] = c;

            if (c == last && top >= m) {
                boolean flag = true;
                for (int j = 0; j < m; j++) {
                    if (stack[top - m + j] != b[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) top -= m;
            }
        }

        if (top == 0) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < top; i++) {
                sb.append(stack[i]);
            }
            System.out.println(sb);
        }
    }
}
