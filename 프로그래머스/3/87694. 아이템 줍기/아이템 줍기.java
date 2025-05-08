import java.util.*;

class Solution {
    static int[][] map = new int[101][101];
    // 하, 좌, 상, 우
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
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
        // public String toString() {
        //     return "x : " + x + ", y : " + y + ", cnt : " + cnt;
        // }
    }
    
    public void bfs(Point s, Point e) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[101][101];
        
        q.offer(s);
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            // System.out.println(p);
            
            if (p.x == e.x && p.y == e.y) {
                ans = Math.min(ans, p.cnt);
            }
            
            v[p.x][p.y] = true;
            Point nPoint = null;
            
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if (nx >= 101 || ny >= 101 || nx < 0 || ny < 0) continue;
                
                if (!v[nx][ny]) {
                    if (map[nx][ny] == 2) {
                        if (p.cnt == 0) {
                            q.offer(new Point(nx, ny, p.cnt + 1));
                        }
                        nPoint = new Point(nx, ny, p.cnt + 1);
                    }
                }
            }
            if (p.cnt > 0 && nPoint != null) {
                q.offer(nPoint);
            } 
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int i = 0; i < rectangle.length; i++) {
            int[] cur = rectangle[i];
            for (int r = cur[0] * 2; r <= cur[2] * 2; r++) {
                for (int c = cur[1] * 2; c <= cur[3] * 2; c++) {
                    if (map[r][c] == 1) continue;
                    map[r][c] = 1; // 면
                    if (r == cur[0] * 2 || r == cur[2] * 2 || c == cur[1] * 2 || c == cur[3] * 2) {
                        map[r][c] = 2; // 선
                    } 
                }
            }
        }

        for (int[] m : map) {
            for (int a : m) {
                System.out.print(a);
            }
            System.out.println();
        }
        bfs(new Point(characterX * 2, characterY * 2, 0), new Point(itemX * 2, itemY * 2, 0));
        
        return ans / 2; 
    }
}