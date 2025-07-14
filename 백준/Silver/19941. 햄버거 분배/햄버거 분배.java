import java.io.*;
import java.util.*;

public class Main {
    static int N, K; 
    static String table;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        table = br.readLine();
        cnt = 0;
        boolean[] eat = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (table.charAt(i) == 'P') {
                int a = i - K >= 0 ? i - K : 0;
                int b = i + K < N ? i + K : N - 1;
                for (int j = a; j <= b; j++) {
                    if (table.charAt(j) == 'H' && !eat[j]) {
                        eat[j] = true;
                        cnt += 1;
                        break;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
