class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 1. dist 배열 초기화
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    dist[i][j] = 20000000; // 최대 비용 (최대 요금 100,000 * 지점갯수 200 = 20,000,000)
                }
            }
        }

        // 2. 요금 정보 입력
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            dist[c][d] = f;
            dist[d][c] = f;
        }

        // 3. Floyd-Warshall 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        // 4. s→x 합승 후 x→a, x→b 각각 이동 비용의 최솟값 탐색
        int answer = dist[s][a] + dist[s][b]; // s→a + s→b 비용
        for (int x = 1; x <= n; x++) {
            int cost = dist[s][x] + dist[x][a] + dist[x][b];
            if (cost < answer) {
                answer = cost;
            }
        }

        return answer;
    }
}
