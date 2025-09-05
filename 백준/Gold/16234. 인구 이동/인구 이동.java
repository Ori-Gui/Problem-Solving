import java.io.*;
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

    static int bfs(int idx, boolean[] v, List<Integer> group) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(idx);
        group.add(idx);
        v[idx] = true;

        int sumP = lands.get(idx).p;

        while (!deque.isEmpty()) {
            int curI = deque.poll();
            Land curL = lands.get(curI);

            for (int i = 0; i < 4; i++) {
                int nx = curL.x + dx[i];
                int ny = curL.y + dy[i];
                int ni = nx * N + ny;

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !v[ni]) {
                    Land nextL = lands.get(ni);
                    int diffP = Math.abs(nextL.p - curL.p);

                    if (diffP >= L && diffP <= R) {
                        v[ni] = true;
                        deque.add(ni);
                        group.add(ni);
                        sumP += lands.get(ni).p;
                    }
                }
            }
        }
        return sumP;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        lands = new ArrayList<>();
        days = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lands.add(new Land(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        while (true) {
            boolean moveFlag = false;
            boolean[] v = new boolean[N * N];

            for (int i = 0; i < N * N; i++) {
                if (!v[i]) {
                    List<Integer> group = new ArrayList<>();
                    int sumP = bfs(i, v, group);

                    if (group.size() >= 2) {
                        moveFlag = true;
                        int newP = sumP / group.size();

                        for (int i2 : group) {
                            lands.get(i2).p = newP;
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
