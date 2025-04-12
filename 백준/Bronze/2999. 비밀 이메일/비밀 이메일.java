import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int N = s.length();
        int R = 0;
        int C = 0;

        for (int r = 1; r <= N; r++) {
            if (N % r == 0) {
                int c = N / r;
                if (r <= c) {
                    R = r;
                    C = c;
                }
            }
        }

        char[][] matrix = new char[C][R];

        int i = 0;
        for (int c = 0; c < C; c++) {
            for (int r = 0; r < R; r++) {
                matrix[c][r] = s.charAt(i++);
            }
        }
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(matrix[c][r]);
            }
        }

    }
}
