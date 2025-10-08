import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static ArrayList<Edge>[] g, rg;

    static class Edge {
        int to, w;
        Edge(int to, int w) { 
            this.to = to; 
            this.w = w; 
        }
    }

    static int[] dijkstra(ArrayList<Edge>[] graph, int s) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        dist[s] = 0;
        pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];
            if (d > dist[v]) continue;

            for (Edge e : graph[v]) {
                int nd = d + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.offer(new int[]{e.to, nd});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        rg = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            g[a].add(new Edge(b, t));   // 정방향
            rg[b].add(new Edge(a, t));  // 역방향 (i -> X 계산용)
        }

        int[] distFromX = dijkstra(g, X);   // X -> i
        int[] distToX = dijkstra(rg, X);  // i -> X

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, distFromX[i] + distToX[i]);
        }

        System.out.println(ans);
    }
}
