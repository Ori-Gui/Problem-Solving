import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] a;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N];
        cnt = new int[100001]; 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int maxLen = 0;
        while (r < N) {
            cnt[a[r]] += 1;
            while (cnt[a[r]] > K) {
                cnt[a[l]] -= 1;
                l += 1;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r += 1;
        }
        System.out.println(maxLen);
    }
}
