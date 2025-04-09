import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int start;
    static List<Integer>[] contacts;
    static boolean[] visited; 

    static int bfs(int start) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;
        int lastLevel = 0;
        int maxAtLastLevel = start;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int level = cur[1];
            
            if (level > lastLevel) {
                lastLevel = level;
                maxAtLastLevel = node;
            } else if (level == lastLevel) {
                maxAtLastLevel = Math.max(maxAtLastLevel, node);
            }
            
            if (contacts[node] == null) continue;
            
            for (int next : contacts[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, level + 1});
                }
            }
        }
        return maxAtLastLevel;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            
            contacts = new ArrayList[101];
            visited = new boolean[101];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (contacts[a] == null) {
                    contacts[a] = new ArrayList<>();
                }
                contacts[a].add(b);
            }
            System.out.printf("#%d %d\n", tc, bfs(start));
        }
    }
}
