import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] ls;

    static int bfs(int s) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[101];

        q.offer(new int[] {s, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == 100) return cur[1];

            for (int i = 1; i <= 6; i++) {
                int x = cur[0] + i;
                if (x <= 100 && !v[x]) {
                    v[x] = true;
                    int y = ls[x];
                    if (y != 0) {
                        v[y] = true;
                        q.offer(new int[] {y, cur[1] + 1});
                    } else {
                        q.offer(new int[] {x, cur[1] + 1});
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ls = new int[101];

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ls[x] = y;
        }

        System.out.println(bfs(1));
    }
}
