import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] mom;

    static int find(int x) {
        if (mom[x] == x) return x;
        return mom[x] = find(mom[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) mom[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        mom = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            mom[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int link = find(first);
        boolean ok = true;

        for (int i = 1; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());
            if (find(city) != link) {
                ok = false;
                break;
            }
        }

        System.out.println(ok ? "YES" : "NO");
    }
}
