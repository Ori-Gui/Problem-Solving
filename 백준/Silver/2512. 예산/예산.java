import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] requests;
    static int max, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        requests = new int[N];
        max = 0;
        sum = 0;

        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            sum += requests[i];
            max = Math.max(requests[i], max);
        }

        int m = Integer.parseInt(br.readLine());

        if (sum <= m) {
            System.out.println(max);
            return;
        }

        int l = 0;
        int r = max;
        int result = 0;

        while (l <= r) {
            int mid = (l + r) / 2;
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += Math.min(requests[i], mid);
            }

            if (total > m) {
                r = mid - 1;
            } else {
                result = mid;
                l = mid + 1;
            }
        }
        System.out.println(result);
    }
}
