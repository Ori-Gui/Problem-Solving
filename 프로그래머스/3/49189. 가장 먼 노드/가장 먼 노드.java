import java.util.*;

class Solution {
    class Node {
        int num;
        int dist;
        Node (int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
    
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n+1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        int[] dists = new int[n+1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[0] = 0;
        dists[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(k -> k.dist));
        pq.offer(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node a = pq.poll();
            
            if (dists[a.num] < a.dist) continue;
            
            for (int b : graph[a.num]) {
                int nd = a.dist + 1;
                if (nd < dists[b]) {
                    dists[b] = nd;
                    pq.offer(new Node(b, nd));
                }
            }
        }
        
        Arrays.sort(dists);
        int i = n;
        int cnt = 0;
        while (dists[n] == dists[i]) {
            i -= 1;
            cnt += 1;
        }  
        
        return cnt;
    }
}