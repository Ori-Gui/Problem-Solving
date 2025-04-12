import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(houses);
        
        // 이분 탐색
        int l = 1; 
        int r = houses[N - 1] - houses[0]; // 최대 거리
        int ans = 0;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            int cnt = 1; // 첫 번째 집에 무조건 공유기 설치
            int wifi = houses[0];
            for (int i = 1; i < N; i++) {
                if (houses[i] - wifi >= mid) {
                    cnt += 1;
                    wifi = houses[i];
                }
            }
            
            if (cnt >= C) {
                // 공유기 설치 가능 -> 거리 늘리기  
                ans = mid;
                l = mid + 1;
            } else {
                // 공유기 설치가 불가 -> 거리 줄이기
                r = mid - 1;
            }
        }
        
        System.out.println(ans);
    }
}
