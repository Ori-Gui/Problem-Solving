import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N];
        st = new StringTokenizer(br.readLine());

        int l = 0;
        int r = 0;

        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            l = Math.max(l, a[i]);
            r += a[i];
        }

        while (l < r) {
            int mid = (l + r) / 2;

            int cnt = 1;
            int sum = 0;
            for (int len : a) {
                if (sum + len > mid) { 
                    cnt += 1; 
                    sum = len; 
                } else {
                    sum += len; 
                }
            }

            if (cnt <= M) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(l);
    }
}
