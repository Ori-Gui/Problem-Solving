import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dist;
    static int[] cities;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N-1];
        cities = new int[N];
        ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }

        int curi = 0;
        while (curi < N-1) {
            long d = 1;

            for (int i = curi + 1; i < N-1; i++) {
                if (cities[curi] < cities[i]) {
                    d += 1;
                } else {
                    break;
                }
            }

            for (int i = curi; i < curi + d; i++) {
                ans += (long) cities[curi] * (long) dist[i];
            }
            curi += d;
        }
        System.out.println(ans);
    }
}
