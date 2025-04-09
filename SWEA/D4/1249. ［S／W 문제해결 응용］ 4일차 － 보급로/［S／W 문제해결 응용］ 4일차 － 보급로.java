import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int[][] map;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int minCost;

	static class Node {
		int x;
		int y;
		int dist;

		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	static int dijkstra(Node s) {
		// 시작점 부터 거리 배열 초기화
		int dist[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[s.x][s.y] = s.dist;

		PriorityQueue<Node> pq = new PriorityQueue<>(
			Comparator.comparingInt((Node n) -> n.dist));

		pq.offer(s);

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dist[cur.x][cur.y] < cur.dist) continue; // 이미 더 짧은 거리 찾음

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
	
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

				int cost = cur.dist + map[nx][ny];
				if (dist[nx][ny] > cost) {
					dist[nx][ny] = cost;
					pq.offer(new Node(nx, ny, cost));
				}
			}
		}
		return dist[N-1][N-1];
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			minCost = dijkstra(new Node(0, 0, 0));
			System.out.printf("#%d %d\n", tc, minCost);
		}
	}
}
