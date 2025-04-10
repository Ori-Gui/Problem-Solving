import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 초밥 번호

        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine().trim());
        }
        
        int[] count = new int[d + 1];
        int distinct = 0; // 현재 윈도우 내의 초밥 종류 개수

        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) {
                distinct++;
            }
            count[sushi[i]]++;
        }
        
        int max = distinct;
        if (count[c] == 0) {
            max = distinct + 1;
        }
        
        for (int i = 0; i < n; i++) {
            int removeIndex = i;
            int addIndex = (i + k) % n;
            
            count[sushi[removeIndex]]--;
            if (count[sushi[removeIndex]] == 0) {
                distinct--;
            }
            
            if (count[sushi[addIndex]] == 0) {
                distinct++;
            }
            count[sushi[addIndex]]++;
            
            int cur = distinct;
            if (count[c] == 0) {
                cur++;
            }
            max = Math.max(max, cur);
        }
        
        System.out.println(max);
    }
}
