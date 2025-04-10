import java.util.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] seq;

    static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(seq[i] + (i == M - 1 ? "\n" : " "));
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                seq[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        visited = new boolean[N + 1];
        seq = new int[M];

        dfs(0);
    }
}
