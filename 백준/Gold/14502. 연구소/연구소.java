import java.util.*;

public class Main {
    static int N, M;
    static int[][] lab;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static List<Point> emptyList;
    static List<Point> virusList;
    static int maxCnt;

    // 좌표 클래스
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 바이러스 확산 시키기
    static void bfs(Point[] sel) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] v = new boolean[N][M];
        int[][] copyLab = new int[N][M];

        // 연구실 복사
        for (int i = 0; i < N; i++) {
            copyLab[i] = lab[i].clone();
        }

        // 벽 3개 세우기
        for (int i = 0; i < 3; i++) {
            int sx = sel[i].x;
            int sy = sel[i].y;
            copyLab[sx][sy] = 1;
        }

        // 바이러스 queue에 넣기
        for (Point virus : virusList) {
            queue.offer(virus);
            v[virus.x][virus.y] = true;
        }

        // 바이러스 확산
        while (!queue.isEmpty()) {
            Point curP = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curP.x + dx[i];
                int ny = curP.y + dy[i];
                // 빈 칸으로 확산
                if (nx < N && ny < M && nx >= 0 && ny >= 0 && copyLab[nx][ny] == 0) {
                    queue.offer(new Point(nx, ny));
                    v[nx][ny] = true;
                    copyLab[nx][ny] = 2;
                }
            }
        }

        int cnt = 0;
        // 빈 칸 개수 세기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyLab[i][j] == 0) {
                    cnt += 1;
                }
            }
        }
        // 최대 안전 영역의 크기 갱신신
        maxCnt = Math.max(maxCnt, cnt);
    }

    // 벽 3개 세울 좌표 고르기 (조합)
    static void dfs(int idx, int start, Point[] sel) {
        if (idx == 3) {
            bfs(sel);
            return;
        }
        for (int i = start; i < emptyList.size(); i++) {
            sel[idx] = emptyList.get(i);
            dfs(idx + 1, i + 1, sel);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        lab = new int[N][M];
        emptyList = new ArrayList<>();
        virusList = new ArrayList<>();
        maxCnt = 0;

        // 바이러스 연구실
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                lab[i][j] = sc.nextInt();
                if (lab[i][j] == 0) {
                    // 0 : 빈 칸 리스트에 추가
                    emptyList.add(new Point(i, j));
                }
                if (lab[i][j] == 2) {
                    // 2 : 바이러스 리스트에 추가
                    virusList.add(new Point(i, j));
                }
            }
        }
        dfs(0, 0, new Point[3]);
        System.out.println(maxCnt);
    }
}