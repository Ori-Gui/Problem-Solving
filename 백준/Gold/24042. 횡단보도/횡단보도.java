import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static List<List<Crosswalk>> graph = new ArrayList<>();
    static long[] times;

    static class Crosswalk {
        int nx;
        int p;

        Crosswalk (int nx, int p) {
            this.nx = nx;
            this.p = p;
        }
    }

    static class Node {
        int x;
        long t;

        Node (int x, long t) {
            this.x = x;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int nx = Integer.parseInt(st.nextToken());

            graph.get(x).add(new Crosswalk(nx, i));
            graph.get(nx).add(new Crosswalk(x, i));
        }

        times = new long[N+1];
        Arrays.fill(times, Long.MAX_VALUE);
        times[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>(Comparator.comparingLong((Node n) -> n.t)
                                                            .thenComparingInt((Node n) -> n.x));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.t > times[cur.x]) continue;

            for (Crosswalk nc : graph.get(cur.x)) {
                int curP = (int)(cur.t % M);
                long nt = cur.t + 1;

                if (curP < nc.p) {
                    nt += nc.p - curP;
                } else if (curP > nc.p) {
                    nt += (M - curP) + nc.p;
                }

                if (nt < times[nc.nx]) {
                    times[nc.nx] = nt;
                    pq.add(new Node(nc.nx, nt));
                }
            }
        }

        System.out.println(times[N]);
    }
}