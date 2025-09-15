import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int sum = 0;
        int ans = N + 1; 

        while (true) {
            if (sum >= S) {
                ans = Math.min(ans, r - l);
                sum -= arr[l++];
            } else {
                if (r == N) break;
                sum += arr[r++];
            }
        }

        System.out.println(ans == N + 1 ? 0 : ans);
    }
}
