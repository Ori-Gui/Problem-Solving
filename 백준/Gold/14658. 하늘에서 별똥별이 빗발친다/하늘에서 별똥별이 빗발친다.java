import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L, K;
    static int[] xs, ys;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        xs = new int[K];
        ys = new int[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            xs[i] = Integer.parseInt(st.nextToken());
            ys[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < K; i++) {
            int r = xs[i];
            for (int j = 0; j < K; j++) {
                int c = ys[j];

                int cnt = 0;
                for (int k = 0; k < K; k++) {
                    if (r <= xs[k] && xs[k] <= r + L &&
                        c <= ys[k] && ys[k] <= c + L) {
                        cnt++;
                    }
                }
                if (cnt > max) max = cnt;
            }
        }

        System.out.println(K - max);
    }
}
