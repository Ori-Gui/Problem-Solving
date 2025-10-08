import java.io.*;
import java.util.*;

public class Main {
    static int N, a, b;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        if (N < a + b - 1) {
            System.out.println(-1);
            return;
        }

        arr = new int[N + 1];
        int h = Math.max(a, b);
        int p = N - (a + b - 1);
        int idx = 1;

        if (a == 1) {
            arr[idx++] = h;
            for (int i = 0; i < p; i++) arr[idx++] = 1;
            for (int i = b - 1; i >= 1; i--) arr[idx++] = i;
        } else {
            for (int i = 0; i < p; i++) arr[idx++] = 1; 
            for (int i = 1; i <= a - 1; i++) arr[idx++] = i;
            arr[idx++] = h;
            for (int i = b - 1; i >= 1; i--) arr[idx++] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(' ');
        }
        System.out.print(sb.toString());
    }
}
