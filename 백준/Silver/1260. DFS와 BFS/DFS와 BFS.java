import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V;
    static List<List<Integer>> edges = new ArrayList<>();

    static String dfs(int start) {
        Stack<Integer> s = new Stack<>();
        boolean[] v = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();
        s.push(start);

        while (!s.isEmpty()) {
            int cur = s.pop();
            if (v[cur]) continue;
            v[cur] = true;
            sb.append(cur).append(' ');
            List<Integer> nodes = edges.get(cur);
            for (int i = 0; i < nodes.size(); i++) {
                if (v[nodes.get(i)]) continue;
                s.push(nodes.get(i));
            }
        }
        return sb.toString();
    }

    static String bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (v[cur]) continue;
            v[cur] = true;
            sb.append(cur).append(' ');
            List<Integer> nodes = edges.get(cur);
            for (int i = 0; i < nodes.size(); i++) {
                q.offer(nodes.get(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!edges.get(a).contains(b)) {
                edges.get(a).add(b);
                edges.get(b).add(a);
            }
        }

        for (int i = 0; i <= N; i++) {
            Collections.sort(edges.get(i), Comparator.reverseOrder());
        }
        System.out.println(dfs(V));

        for (int i = 0; i <= N; i++) {
            Collections.sort(edges.get(i), Comparator.naturalOrder());
        }
        System.out.println(bfs(V));
    }
}
