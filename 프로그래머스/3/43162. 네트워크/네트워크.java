import java.util.*;

class Solution {
    public void bfs(int start, List<List<Integer>> list, boolean[] v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (!v[cur]) {
                v[cur] = true;
                List<Integer> l = list.get(cur);
                for (int next : l) {
                    if (!v[next]) {
                        q.offer(next);
                    }
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        boolean[] v = new boolean[n];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    list.get(i).add(j);
                }
            }
        }
        
        for (List<Integer> l : list) {
            System.out.println(l);
        }
        
        int cnt = 0;
        
        for (int s = 0; s < n; s++) {
            if (!v[s]) {
                bfs(s, list, v);
                cnt += 1;
            }
        }
        
        return cnt;
    }
}