import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B;
    
    static int[][] pow(int[][] A, long e) {
        if (e == 1) return A;

        int[][] half = pow(A, e / 2);
        int[][] res = mul(half, half);
        if (e % 2 == 1) res = mul(res, A);
        return res;
    }

    static int[][] mul(int[][] X, int[][] Y) {
        int[][] Z = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < N; j++) {
                    Z[i][j] = (int) (((long) X[i][k] * Y[k][j] + Z[i][j]) % 1000);
                }
            }
        }
        return Z;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] ans = pow(A, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ans[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
