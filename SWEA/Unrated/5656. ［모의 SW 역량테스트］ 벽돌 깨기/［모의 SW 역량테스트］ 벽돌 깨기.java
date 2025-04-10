import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N, W, H;
    static int[][] map;
    static int[][] gameMap;
    // 우, 하, 좌, 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int minCnt;
    
    static void breakBrick(int x, int y) {
    	int power = gameMap[x][y];
		gameMap[x][y] = 0;
		// 벽돌 연쇄 폭발 1 이상
		if (power > 1) {
			for (int d = 0; d < 4; d++) { // 4방향
				for (int p = 1; p < power; p++) { // p깊이
					int nx = x + dx[d] * p;
					int ny = y + dy[d] * p;
					if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
					if (gameMap[nx][ny] > 0) {
						breakBrick(nx, ny);
					}
				}
			}	
		}
    }
    
    static void game(int[] sel) {
		gameMap = new int[H][W]; // 게임 맵 복사
		for (int i = 0; i < H; i++) {
			gameMap[i] = map[i].clone();
		}

		// 구슬 던지기
		for (int i = 0; i < N; i++) {
			int c = sel[i];
			// 해당 열 가장 위쪽 벽돌 찾기 (0 보다 큰)
			for (int r = 0; r < H; r++) {
				if (gameMap[r][c] > 0) {
					breakBrick(r, c);
					break;
				}
			}

			// 중력 처리: 각 열에 대해 아래쪽부터 다시 채우기
            for (int y = 0; y < W; y++) {
                int idx = H - 1; // 바닥부터 채울 인덱스
                for (int x = H - 1; x >= 0; x--) {
                    if (gameMap[x][y] > 0) {
                        gameMap[idx][y] = gameMap[x][y];
						idx -= 1;
                    }
                }
                // 나머지 칸은 0으로 채우기
                for (int x = idx; x >= 0; x--) {
                    gameMap[x][y] = 0;
                }
            }

			if (countBricks(gameMap) == 0) {
				minCnt = 0;
				return;
			}
		}
		int cnt = countBricks(gameMap);
		minCnt = Math.min(minCnt, cnt);
    }

	// 남은 벽돌 수 세기
	static int countBricks(int[][] board) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }
    
	// 중복순열
    static void dfs(int idx, int[] sel) {
    	if (idx == N) {
    		game(sel);
    		return;
    	}
    	
    	for (int i = 0; i < W; i++) {
			sel[idx] = i;
			dfs(idx + 1, sel);
		}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            map = new int[H][W];
            minCnt = Integer.MAX_VALUE;
            
            for (int i = 0; i < H; i++) {
            	st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
            
            dfs(0, new int[N]);
			System.out.printf("#%d %d\n", tc, minCnt);
        }   
    }
}
