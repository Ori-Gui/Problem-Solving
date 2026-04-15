import java.util.*;

class Solution {
    static class Union {
        int[] p, sz;
        
        Union(int n) {
            p = new int[n];
            sz = new int[n];
            
            for (int i = 0; i < n; i++) { 
                p[i] = i; 
                sz[i] = 1; 
            }
        }
        
        int find(int x) {
            if (p[x] == x) return x;
            return p[x] = find(p[x]);
        }
        
        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) return;
            
            if (sz[pa] < sz[pb]) { 
                int t = pa; 
                pa = pb; 
                pb = t; 
            }
            
            p[pb] = pa;
            sz[pa] += sz[pb];
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        HashMap<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idx.put(nodes[i], i);
        }

        int[] d = new int[n];
        Union u = new Union(n);

        for (int[] e : edges) {
            int a = idx.get(e[0]);
            int b = idx.get(e[1]);
            d[a]++;
            d[b]++;
            u.union(a, b);
        }

        int[] treeSz = new int[n];
        int[] treeEq = new int[n];

        for (int i = 0; i < n; i++) {
            int r = u.find(i);
            treeSz[r]++;
            int labelParity = nodes[i] % 2;
            int degParity = d[i] % 2;
            if (labelParity == degParity) treeEq[r]++;
        }

        int cntOET = 0;
        int cntInvOET = 0;

        for (int i = 0; i < n; i++) {
            if (u.find(i) != i) continue;
            int size = treeSz[i];
            int eq = treeEq[i];
            if (eq == 1) cntOET++;
            if (eq == size - 1) cntInvOET++;
        }

        return new int[]{cntOET, cntInvOET};
    }
}
