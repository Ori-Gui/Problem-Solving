import java.io.*;

public class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int ans = 1;
            if (n == 1) {
                sb.append(ans).append("\n");
            } else {
                int k = 0;
                for (int i = 1; i < n; i++) {
                    if (i % 6 == 1) k += 1;
                    if (i % 6 == 5) k += 1;
                    if (i % 6 == 0) k -= 1;
                    ans += k;
                }
                sb.append(ans).append("\n");
            }   
        }
        System.out.println(sb);
    }
}
