import java.io.*;
import java.util.*;
 
public class Solution {
    static int T;
    static int N;
    static int[][] map;
    // 우, 하, 좌, 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    // 각 블록별 방향 전환
    static int[][] dd = {{2, 0, 3, 1},
                         {2, 3, 1, 0},
                         {1, 3, 0, 2},
                         {3, 2, 0, 1},
                         {2, 3, 0, 1}};
    static Hole[][] wormholes; 
    static int maxScore;
     
    static class Hole {
        int x;
        int y;
         
        Hole(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
     
    static void game(int x, int y, int d) {
        int score = 0;
        int sx = x;
        int sy = y;
         
        while (true) {
            x += dx[d];
            y += dy[d]; 
             // 출발점으로 돌아오면 종료
            if (sx == x && sy == y) break;
 
            if (x < 0 || y < 0 || x >= N || y >= N) {
                score += 1;
                d = dd[4][d]; // 방향 전환 (블록5)
                continue;
            }
             
            int n = map[x][y];
            // 블랙홀
            if (n == -1) break;
            // 길
            if (n == 0) continue;
            // 반사 블록
            if (n <= 5) {
                score += 1;
                d = dd[n-1][d]; // 방향 전환 (블록1~5)
                continue;
            }
            // 웜홀
            if (n > 5) {
                Hole wh1 = wormholes[n-6][0];
                Hole wh2 = wormholes[n-6][1];
                // 위치 변환
                if (wh1.x == x && wh1.y == y) {
                    x = wh2.x;
                    y = wh2.y;
                } else {
                    x = wh1.x;
                    y = wh1.y;
                }
            }
        }
        maxScore = Math.max(maxScore, score);
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine().trim());
         
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            maxScore = 0;
            map = new int[N][N];
            wormholes = new Hole[5][2];
             
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) { 
                    map[i][j] = Integer.parseInt(st.nextToken());
                    int p = map[i][j];
                    if (p > 5) {
                        if (wormholes[p-6][0] == null) {
                            wormholes[p-6][0] = new Hole(i, j);
                        } else {
                            wormholes[p-6][1] = new Hole(i, j);
                        }
                    } 
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) { 
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            game(i, j, d);
                        }
                    }
                }
            }   
            System.out.printf("#%d %d\n", tc, maxScore);
        }
    }
}