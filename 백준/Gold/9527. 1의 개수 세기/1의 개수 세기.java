import java.io.*;
import java.util.*;

public class Main {
    static long A, B;

    static long countOnes(long n) {
        if (n <= 0) return 0;
        long total = 0;
        for (int i = 0; i < 61; i++) { 
            long p = 1L << i; // 2^i
            long cycle = p << 1; // 2^(i+1)
            long full = ((n + 1) / cycle) * p; 
            long rem = (n + 1) % cycle;
            long extra = Math.max(0L, rem - p);
            total += full + extra;
            if (p > n && cycle > (n + 1)) break;
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        long ans = countOnes(B) - countOnes(A - 1);
        System.out.println(ans);
    }
}
