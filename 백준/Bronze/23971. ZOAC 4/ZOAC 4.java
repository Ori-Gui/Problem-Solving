import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        for (int i = 1; i <= H; i += N + 1) {
            for (int j = 1; j <= W; j += M + 1) {
                cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}
