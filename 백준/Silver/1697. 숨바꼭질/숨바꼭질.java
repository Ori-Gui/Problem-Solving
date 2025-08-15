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
            int[] nx = new int[] {cur[0] - 1, cur[0] + 1, 2 * cur[0]};
            
            for (int i = 0; i < 3; i++) {
                if (nx[i] >= 0 && nx[i] <= 100000 && !v[nx[i]]) {
                    v[nx[i]] = true;
                    q.offer(new int[] {nx[i], cur[1] + 1});
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