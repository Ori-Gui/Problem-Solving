import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 각 문제의 진입차수 배열
        int[] indegree = new int[N + 1];
        
        // m개의 선행 관계
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a -> b 방향 간선 추가
            graph[a].add(b);
            indegree[b] += 1;
        }
        
        // 우선순위 큐를 사용하여 번호가 작은 문제부터 처리 (진입차수가 0인 문제)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        // 위상 정렬
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");
            // 현재 문제를 풀면 다음 문제들의 진입차수를 감소시킴
            for (int next : graph[cur]) {
                indegree[next] -= 1;
                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        
        System.out.println(sb);
    }
}
