import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	static List<List<Integer>> students1, students2;
	static boolean[] v1, v2;
	
	static void bfs(int x, boolean[] v, List<List<Integer>> s) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(x);
		v[x] = true;
		
		while (!queue.isEmpty()) {
			int cx = queue.poll();
			for (int nx : s.get(cx)) {
				if (!v[nx]) {
					queue.offer(nx);
					v[nx] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			students1 = new ArrayList<>();
			students2 = new ArrayList<>();
			
			int cnt = 0;
			
			for (int i = 0; i < N + 1; i++) {
				students1.add(new ArrayList<Integer>());
				students2.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				students1.get(a).add(b);
				students2.get(b).add(a);
			}
			
			for (int i = 1; i <= N; i++) {
				v1 = new boolean[N+1];
				v2 = new boolean[N+1];
				bfs(i, v1, students1);
				bfs(i, v2, students2);

				boolean cntFlag = true;
				for (int j = 1; j <= N; j++) {
					if (!v1[j] && !v2[j]) {
						cntFlag = false;
					}
				}
				
				if (cntFlag) {
					cnt += 1;
				}
			}
			System.out.printf("#%d %d\n", t, cnt);
		}
	}
}
