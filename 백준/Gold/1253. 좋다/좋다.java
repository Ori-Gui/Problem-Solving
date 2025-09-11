import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int good = 0;
    static long[] arr;

    static boolean isGood(long[] arr, int idx) {
        int l = 0;
        int r = N - 1;
        long target = arr[idx];

        while (l < r) {
            if (l == idx) { 
                l += 1; 
                continue; 
            }
            if (r == idx) { 
                r -= 1;
                continue; 
            }

            long sum = arr[l] + arr[r];
            if (sum == target) return true;
            if (sum < target) l += 1;
            else if (sum > target) r -= 1;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            if (isGood(arr, i)) {
                good += 1;
            }
        }

        System.out.println(good);
    }
}
