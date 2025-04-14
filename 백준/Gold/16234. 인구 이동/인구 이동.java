import java.util.*;

public class Main {
    static int N, L, R;
    static List<Land> lands;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int days;

    static class Land {
        int x, y;
        int p;

        Land(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }

    static int bfs(Land land, boolean[] v, List<Land> group) {
        Deque<Land> deque = new ArrayDeque<>();
        deque.add(land);
        group.add(land);
        v[land.x * N + land.y] = true;

        int sumP = land.p;

        while (!deque.isEmpty()) {
            Land curL = deque.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curL.x + dx[i];
                int ny = curL.y + dy[i];
                int ni = nx * N + ny;

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !v[ni]) {
                    Land nextL = lands.get(ni);
                    int diffP = Math.abs(nextL.p - curL.p);

                    if (diffP >= L && diffP <= R) {
                        v[ni] = true;
                        deque.add(nextL);
                        group.add(nextL);
                        sumP += nextL.p;
                    }
                }
            }
        }
        return sumP;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        lands = new ArrayList<>();
        days = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lands.add(new Land(i, j, sc.nextInt()));
            }
        }

        while (true) {
            boolean moveFlag = false;
            boolean[] v = new boolean[N * N];

            for (int i = 0; i < N * N; i++) {
                if (!v[i]) {
                    Land land = lands.get(i);
                    List<Land> group = new ArrayList<>();
                    int sumP = bfs(land, v, group);

                    if (group.size() >= 2) {
                        moveFlag = true;
                        int newP = sumP / group.size();

                        for (Land l : group) {
                            l.p = newP;
                        }
                    }
                }
            }
            if (!moveFlag) {
                break;
            }
            days += 1;
        }
        System.out.println(days);
    }
}
