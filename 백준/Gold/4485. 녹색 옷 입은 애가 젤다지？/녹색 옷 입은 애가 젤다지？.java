import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int ans = 0;
    static int[][] cave, dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y, cost;

        Node(int x, int y, int cost) { 
            this.x = x; this.y = y; this.cost = cost; 
        }
    }

    static int dijkstra() {
        dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], 2500);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.cost));
        dist[0][0] = cave[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.x][cur.y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                int ncost = cur.cost + cave[nx][ny];
                if (ncost < dist[nx][ny]) {
                    dist[nx][ny] = ncost;
                    pq.offer(new Node(nx, ny, ncost));
                }
            }
        }
        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;

        while (true) {;
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            ans = dijkstra();
            sb.append("Problem ").append(tc++).append(": ").append(ans).append('\n');
        }

        System.out.print(sb);
    }
}
