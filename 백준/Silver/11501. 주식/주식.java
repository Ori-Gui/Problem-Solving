import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static int[] prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) { 
            N = Integer.parseInt(br.readLine());
            prices = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            long profit = 0;
            int max = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (prices[i] > max) {
                    max = prices[i];
                } else {
                    profit += max - prices[i];
                }
            }

            sb.append(profit).append('\n');
        }

        System.out.print(sb);
    }
}

