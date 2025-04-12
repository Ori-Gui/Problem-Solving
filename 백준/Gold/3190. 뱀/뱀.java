import java.util.*;

public class Main {
    // 0: 오른쪽, 1: 아래쪽, 2: 왼쪽, 3: 위쪽
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N;
    static int K;
    static int L;
    static int[][] board;
    static Map<Integer, Character> map;

    static class Point {
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(Point p, int d) {
        Deque<Point> snake = new ArrayDeque<>();
        snake.addLast(p);
        board[p.x][p.y] = 1;

        int sec = 0;

        while (true) {
            sec += 1;
            Point head = snake.peekLast();
            int nx = head.x + dx[d];
            int ny = head.y + dy[d];

            // 벽 or 자기 몸
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) {
                return sec;
            };

            // 사과 o
            if (board[nx][ny] == 2) {
                board[nx][ny] = 1;
                snake.addLast(new Point(nx, ny)); // 뱀 머리 (길어짐)
            } else { // 사과 x
                board[nx][ny] = 1;
                snake.addLast(new Point(nx, ny)); // 뱀 머리
                Point tail = snake.pollFirst();
                board[tail.x][tail.y] = 0; // 꼬리 짜르기 (길이 그대로)
            }

            if (map.containsKey(sec)) {
                char turn = map.get(sec);
                if (turn == 'L') {
                    d = (d + 3) % 4;
                } else if (turn == 'D') {
                    d = (d + 1) % 4;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        board = new int[N][N]; // 0: 빈 칸, 1: 뱀, 2: 사과

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            board[r][c] = 2;
        }

        L = sc.nextInt();
        map = new HashMap<>();

        for (int i = 0; i < L; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            map.put(x, c);
        }

        int sec = bfs(new Point(0, 0), 0);

        System.out.println(sec);
    }
}
