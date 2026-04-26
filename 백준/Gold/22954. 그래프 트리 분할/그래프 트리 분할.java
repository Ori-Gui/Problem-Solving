import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<List<int[]>> graph;
    static int[] parent;
    static int[] parentEdge;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new int[]{w, i});
            graph.get(w).add(new int[]{u, i});
        }

        parent = new int[N + 1];
        parentEdge = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(parent, -1);
        Arrays.fill(parentEdge, -1);

        List<List<Integer>> components = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                bfs(i, comp);
                components.add(comp);
            }
        }

        int numComp = components.size();
        boolean impossible = N <= 2
                || numComp >= 3
                || (numComp == 2 && components.get(0).size() == components.get(1).size());

        if (impossible) {
            System.out.println(-1);
            return;
        }

        if (numComp == 1) {
            List<Integer> all = components.get(0);
            int[] childCount = new int[N + 1];
            for (int node : all) {
                if (parent[node] != -1) childCount[parent[node]]++;
            }

            int leaf = -1;
            for (int node : all) {
                if (parent[node] != -1 && childCount[node] == 0) {
                    leaf = node;
                    break;
                }
            }

            System.out.println("1 " + (N - 1));
            System.out.println(leaf);

            StringBuilder sbV = new StringBuilder();
            StringBuilder sbE = new StringBuilder();
            for (int node : all) {
                if (node == leaf) continue;
                if (sbV.length() > 0) sbV.append(' ');
                sbV.append(node);
                if (parent[node] != -1) {
                    if (sbE.length() > 0) sbE.append(' ');
                    sbE.append(parentEdge[node]);
                }
            }
            System.out.println(sbV);
            System.out.println(sbE);
        } else {
            List<Integer> c1 = components.get(0);
            List<Integer> c2 = components.get(1);

            System.out.println(c1.size() + " " + c2.size());
            printTree(c1);
            printTree(c2);
        }
    }

    static void printTree(List<Integer> comp) {
        StringBuilder sbV = new StringBuilder();
        StringBuilder sbE = new StringBuilder();
        for (int node : comp) {
            if (sbV.length() > 0) sbV.append(' ');
            sbV.append(node);
            if (parent[node] != -1) {
                if (sbE.length() > 0) sbE.append(' ');
                sbE.append(parentEdge[node]);
            }
        }
        System.out.println(sbV);
        System.out.println(sbE);
    }

    static void bfs(int start, List<Integer> comp) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        comp.add(start);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int[] e : graph.get(u)) {
                int next = e[0];
                int eid = e[1];
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = u;
                    parentEdge[next] = eid;
                    q.offer(next);
                    comp.add(next);
                }
            }
        }
    }
}
