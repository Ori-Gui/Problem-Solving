import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] requests = new int[n];
        int max = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
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
            for (int i = 0; i < n; i++) {
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
