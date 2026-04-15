import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        // 1. 그래프 구성
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] p : path) {
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }

        // 2. 제약 정보 설정
        // pre[i] : i번 방을 방문하기 전에 반드시 방문해야 하는 방 (없으면 -1)
        int[] pre = new int[n];
        Arrays.fill(pre, -1);
        // wait[x] : x번 방을 방문한 후에 unlock될, 아직 방문 못한 방 (없으면 -1)
        int[] wait = new int[n];
        Arrays.fill(wait, -1);

        for (int[] o : order) {
            pre[o[1]] = o[0];
        }

        // 0번 방(입구)가 잠겨있으면 탐험 불가능
        if (pre[0] != -1) {
            return false;
        }

        // 3. BFS 탐색 0번 방부터 시작
        boolean[] v = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        v[0] = true;
        q.offer(0);

        while (!q.isEmpty()) {
            int cur = q.poll();

            // cur 방문으로 인해 unlock되어야 하는 방이 있다면 큐에 추가
            if (wait[cur] != -1) {
                int next = wait[cur];
                q.offer(next);
                v[next] = true;
                wait[cur] = -1;
            }

            for (int next : graph[cur]) {
                if (v[next]) {
                    continue;
                }

                // next가 방문 조건(선행 방문)이 있고, 아직 방문 전이라면 next 대기
                if (pre[next] != -1 && !v[pre[next]]) {
                    wait[pre[next]] = next;
                } else {
                    v[next] = true;
                    q.offer(next);
                }
            }
        }

        // 4. 모든 방을 방문했는지 확인
        for (boolean flag : v) {
            if (!flag) {
                return false;
            } 
        }
        return true;
    }
}
