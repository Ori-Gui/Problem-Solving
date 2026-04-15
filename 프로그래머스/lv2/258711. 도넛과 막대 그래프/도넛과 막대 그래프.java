import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 1. 전체 정점 번호 범위 파악
        int nCount = 0;
        for (int[] e : edges) {
            nCount = Math.max(nCount, Math.max(e[0], e[1]));
        }
        
        // 2. 그래프와 inDegree 배열 초기화
        List<List<Integer>> graph = new ArrayList<>(nCount + 1);
        int[] inDegree = new int[nCount + 1];
        for (int i = 0; i <= nCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.get(u).add(v);
            inDegree[v]++;
        }
        
        // 3. "생성 정점" 찾기: inDegree == 0 이면서 outDegree (graph.get(i).size())가 2 이상인 정점
        int start = -1;
        for (int i = 1; i <= nCount; i++) {
            if (inDegree[i] == 0 && graph.get(i).size() >= 2) {
                start = i;
                break;
            }
        }
        
        int donut = 0, stick = 0, eight = 0;
        boolean[] v = new boolean[nCount + 1];

        // 4. BFS로 서브그래프 탐색
        for (int child : graph.get(start)) {
            if (v[child]) continue;
            
            int compV = 0, compE = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(child);
            v[child] = true;
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                compV++;
                
                for (int next : graph.get(cur)) {
                    compE++; // cur -> next 간선 카운트
                    if (!v[next]) {
                        v[next] = true;
                        queue.offer(next);
                    }
                }
            }
            
            // 5. 서브그래프 분류
            if (compE == compV) {         // 도넛 그래프: 간선 수 == 정점 수
                donut++;
            } else if (compE == compV - 1) { // 막대 그래프: 간선 수 == 정점 수 - 1
                stick++;
            } else if (compE == compV + 1) { // 팔자(8자) 그래프: 간선 수 == 정점 수 + 1
                eight++;
            }
        }
        
        return new int[] { start, donut, stick, eight };
    }
}