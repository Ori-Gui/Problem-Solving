import java.util.*;

public class Main {
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> students1;
    static ArrayList<ArrayList<Integer>> students2;

    static void bfs(int x, boolean[] v, ArrayList<ArrayList<Integer>> s) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        v[x] = true;

        while (!queue.isEmpty()) {
            int curX = queue.poll();
            for (int nextX : s.get(curX)) {
                if (!v[nextX]) {
                    queue.offer(nextX);
                    v[nextX] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        students1 = new ArrayList<>();
        students2 = new ArrayList<>();
        boolean[] v1;
        boolean[] v2;

        int cnt = 0;

        for (int i = 0; i < N + 1; i++) {
            students1.add(new ArrayList<>());
            students2.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            students1.get(a).add(b);
            students2.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            v1 = new boolean[N + 1];
            v2 = new boolean[N + 1];

            bfs(i, v1, students1);
            bfs(i, v2, students2);

            boolean cntFlag = true;

            for (int j = 1; j <= N; j++) {
                if (!v1[j] && !v2[j]) {
                    cntFlag = false;
                }
            }

            if (cntFlag) {
                cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}
