import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] v = new boolean[100001];
        int l = 0;
        int r = 0;
        long ans = 0;

        while (l < N) {
            while (r < N && !v[arr[r]]) {
                v[arr[r]] = true;
                r++;
            }
            ans += (r - l);
            v[arr[l]] = false;
            l++;
        }

        System.out.println(ans);
    }
}
