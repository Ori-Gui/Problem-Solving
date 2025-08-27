import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<int[]>> graph = new ArrayList<>();
    static int[] dist;
    static boolean[] v;

    static class Node {
        int n, d;

        Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.d));
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        v = new boolean[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, cow});
            graph.get(b).add(new int[] {a, cow});
        }

        dist[1] = 0;
        pq.offer(new Node(1, dist[1]));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (v[cur.n]) continue;

            List<int[]> list = graph.get(cur.n);
            for (int[] n : list) {
                int cost = dist[cur.n] + n[1];
                if (dist[n[0]] > cost) {
                    dist[n[0]] = cost;
                    pq.offer(new Node(n[0], cost));
                }
            }
            v[cur.n] = true;
        }
        System.out.println(dist[N]);
    }
}
