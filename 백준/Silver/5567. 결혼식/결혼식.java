import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] ssafy;
	static boolean[] v;
	static int cnt;
	
	static void dfs(int k, int idx) {
		if (idx == 2) {
			return;
		}
		
		for (int i = 0; i < ssafy[k].size(); i++) {
			int next = ssafy[k].get(i);
			if (!v[next]) {
				v[next] = true;
				cnt += 1;
			}
			dfs(next, idx + 1);
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ssafy = new ArrayList[N+1];
		v = new boolean[N+1];
		cnt = 0;
		
		for (int i = 0; i < N+1; i++) {
			ssafy[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			ssafy[a].add(b);
			ssafy[b].add(a);
		}
		
		v[1] = true;
		dfs(1, 0);
		System.out.println(cnt);
	}
}
