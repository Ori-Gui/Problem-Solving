import java.util.*;

class Solution {
    // 하, 좌, 상, 우
    static int[] dx = {1, 0, -1, 0, };
    static int[] dy = {0, -1, 0, 1, };
    static int ans = Integer.MAX_VALUE;
    
    class Point {
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public void bfs(int[][] maps, Point p) { 
        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] v = new boolean[n][m];
        Queue<Point> q = new LinkedList<>();
        
        q.offer(p);
        v[p.x][p.y] = true;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.x == n - 1 && cur.y == m - 1) {
                System.out.println(cur.cnt);
                ans = Math.min(ans, cur.cnt);
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || v[nx][ny] || maps[nx][ny] == 0) continue;
            
                q.offer(new Point(nx, ny, cur.cnt + 1));
                v[nx][ny] = true;
            }
        }
    }
    
    public int solution(int[][] maps) {
        bfs(maps, new Point(0, 0, 1));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}