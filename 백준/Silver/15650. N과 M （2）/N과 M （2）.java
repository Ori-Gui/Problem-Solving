import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] seq;

    static void dfs(int idx, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(seq[i] + (i == M - 1 ? "\n" : " "));
            }
            return;
        }

        for (int i = idx; i <= N; i++) {
            seq[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        seq = new int[M];

        dfs(1, 0);
    }
}
