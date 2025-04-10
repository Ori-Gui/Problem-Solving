import java.io.*;
import java.util.*;

public class Main {
    static int T;

    public static long combination(int n, int r) {
        long result = 1L;
        for (int i = 1; i <= r; i++) {
            result *= (n - i + 1);
            result /= i;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            System.out.println(combination(m, n));
        }   
    }
}
