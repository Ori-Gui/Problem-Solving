import java.util.*;
import java.io.*;

public class Solution {
    static int T, N;
    static int[][] processor;
    static int maxCore, minLength;
    static ArrayList<Point> cores;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x; 
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            processor = new int[N][N];
            cores = new ArrayList<>();
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    processor[i][j] = Integer.parseInt(st.nextToken());
                    if (processor[i][j] == 1) {
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
                        cores.add(new Point(i, j));
                    }
                }
            }
            
            maxCore = 0;
            minLength = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            System.out.println("#" + tc + " " + minLength);
        }
    }
    
    static void dfs(int idx, int connected, int length) {
        if (idx == cores.size()) {
            if (connected > maxCore) {
                maxCore = connected;
                minLength = length;
            } else if (connected == maxCore) {
                minLength = Math.min(minLength, length);
            }
            return;
        }
        
        int remain = cores.size() - idx;
        if (connected + remain < maxCore) return;
        
        Point p = cores.get(idx);
        int x = p.x;
        int y = p.y;
        
        for (int d = 0; d < 4; d++) {
            ArrayList<Point> list = new ArrayList<>();
            int nx = x;
            int ny = y;
            boolean possible = true;
            
            while (true) {
                nx += dx[d];
                ny += dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
                if (processor[nx][ny] != 0) {
                    possible = false;
                    break;
                }
                list.add(new Point(nx, ny));
            }
            
            if (!possible) continue;
            for (Point point : list) {
                processor[point.x][point.y] = 2;
            }
            dfs(idx + 1, connected + 1, length + list.size());

            for (Point point : list) {
                processor[point.x][point.y] = 0;
            }
        }
        dfs(idx + 1, connected, length);
    }
}
