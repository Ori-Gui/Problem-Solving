import java.io.*;
import java.util.*;

public class Main {
    static int n, d, k, c;
    static int[] sushi;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 접시 수
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 초밥 번호

        sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        
        count = new int[d + 1];
        int type = 0;

        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) {
                type += 1;
            }
            count[sushi[i]] += 1;
        }
        
        int max = type;
        if (count[c] == 0) {
            max = type + 1;
        }
        
        for (int i = 0; i < n; i++) {
            int rmIdx = i;
            int addIdx = (i + k) % n;
            
            count[sushi[rmIdx]] -= 1;
            if (count[sushi[rmIdx]] == 0) {
                type -= 1;
            }
            if (count[sushi[addIdx]] == 0) {
                type += 1;
            }

            count[sushi[addIdx]] += 1;
            
            int cur = type;
            if (count[c] == 0) {
                cur += 1;
            }
            max = Math.max(max, cur);
        }
        
        System.out.println(max);
    }
}