import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] a;
    static boolean[] v;
    static boolean[] f;
    static List<Integer> result = new ArrayList<>();

    static void dfs(int x) {
        v[x] = true;
        int nx = a[x];

        if (!v[nx]) {
            dfs(nx);
        } else if (!f[nx]) {
            result.add(nx);
            int cur = a[nx];
            
            while (cur != nx) {
                result.add(cur);
                cur = a[cur];
            }
        }
        f[x] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N + 1];
        v = new boolean[N + 1];
        f = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            dfs(i);
        }

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        for (int x : result) sb.append(x).append('\n');

        System.out.print(sb);
    }
}
