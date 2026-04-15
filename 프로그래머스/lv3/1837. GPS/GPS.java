import java.util.*;
 
class Solution {
    static List<Integer>[] graph;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        // 1부터 n까지 정점, 양방향 간선으로 인접 리스트 생성
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            // "머무르기" 가능
            graph[i].add(i);
        }
        for (int[] edge : edge_list) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // dp[t][v] : 시간 t에 v에 있을 때까지 수정한 최소 횟수
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 시작 위치는 gps_log[0]
        dp[0][gps_log[0]] = 0;
        
        // 마지막 위치 gps_log[k-1]는 수정 불가
        for (int t = 0; t < k - 1; t++) {
            for (int v = 1; v <= n; v++) {
                if (dp[t][v] == Integer.MAX_VALUE) continue;
                // v에서 이동 가능한 모든 거점 w (v 자신 포함)
                for (int w : graph[v]) {
                    int cost = (w == gps_log[t + 1] ? 0 : 1);
                    dp[t + 1][w] = Math.min(dp[t + 1][w], dp[t][v] + cost);
                }
            }
        }

        int answer = dp[k - 1][gps_log[k - 1]];
        return (answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
