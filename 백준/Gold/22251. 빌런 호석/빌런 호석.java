import java.io.*;
import java.util.*;

public class Main {
    static int N, K, P, X;
    static int[] mask = {
        0b1111110, // 0
        0b0110000, // 1
        0b1101101, // 2
        0b1111001, // 3
        0b0110011, // 4
        0b1011011, // 5
        0b1011111, // 6
        0b1110000, // 7
        0b1111111, // 8
        0b1111011  // 9
    };

    static int cnts(int a, int b) {
        return Integer.bitCount(mask[a] ^ mask[b]);
    }

    static int[] toArr(int x) {
        int[] arr = new int[K];
        for (int i = K - 1; i >= 0; i--) {
            arr[i] = x % 10;
            x /= 10;
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 최대 층
        K = Integer.parseInt(st.nextToken()); // 자리수
        P = Integer.parseInt(st.nextToken()); // LED 최대 반전 개수
        X = Integer.parseInt(st.nextToken()); // 현재 층

        int[] xArr = toArr(X);
        
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int[] yArr = toArr(i);
            int cnt = 0;
            for (int j = 0; j < K; j++) {
                cnt += cnts(xArr[j], yArr[j]);
                if (cnt > P) break;
            }
            if (cnt >= 1 && cnt <= P) ans += 1;
        }
        System.out.println(ans);
    }
}
