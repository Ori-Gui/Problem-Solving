import java.io.*;
import java.util.*;

public class Main {
    static int N, X;
    static int[] visitors;
    static int[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visitors = new int[N];
        prefixSum = new int[N - X + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            visitors[i] = Integer.parseInt(st.nextToken());
        }

        int cur = 0;

        for (int i = 0; i < X; i++) {
            cur += visitors[i];
        }
        prefixSum[0] = cur;

        for (int i = 1; i <= N - X; i++) {
            cur += visitors[i + X - 1] - visitors[i - 1];
            prefixSum[i] = cur;
        }
        
        int max = prefixSum[0];
        int cnt = 1;
        for (int i = 1; i <= N - X; i++) {
            if (prefixSum[i] > max) {
                max = prefixSum[i];
                cnt = 1;
            } else if (prefixSum[i] == max) {
                cnt++;
            }
        }
        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
