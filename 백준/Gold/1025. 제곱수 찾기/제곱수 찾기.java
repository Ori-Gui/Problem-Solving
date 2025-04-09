import java.util.*;

public class Main {
    static int N, M;
    static int[][] A;
    static long ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N][M];
        ans = -1L;

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                A[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int r = -N; r < N; r++) {
                    for (int c = -M; c < M; c++) {
                        String temp = "";
                        if (r == 0 && c == 0) {
                            continue;
                        }
                        int ii = i;
                        int jj = j;
                        while (ii >= 0 && jj >= 0 && ii < N && jj < M) {
                            temp += A[ii][jj];
                            long tempL = Long.parseLong(temp);
                            long sqrtL = (long) Math.sqrt(tempL);
                            if (sqrtL * sqrtL == tempL) {
                                ans = Math.max(ans, tempL);
                            }
                            ii += r;
                            jj += c;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
