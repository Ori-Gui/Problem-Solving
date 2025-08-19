import java.io.*;
import java.util.*;

public class Main {
    static int X, K;
    static boolean[] v = new boolean[100001];

    static int bfs(int s, int t) {
        Queue<int[]> q = new ArrayDeque<int[]>();
        q.offer(new int[] {s, t});
        v[s] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == K) return cur[1];

            int[] nx = new int[] {2 * cur[0], cur[0] - 1, cur[0] + 1};
            for (int i = 0; i < 3; i++) {
                int k = nx[i];
                if (k >= 0 && k <= 100000 && !v[k]) {
                    if (i == 0) {
                        while (k <= 100000) {
                            v[k] = true;
                            q.offer(new int[] {k, cur[1]});
                            k = k << 1;
                        } 
                    } else {
                        v[k] = true;
                        q.offer(new int[] {k, cur[1] + 1});
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        X = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(st.nextToken()); 

        System.out.println(bfs(X, 0));
    }
}