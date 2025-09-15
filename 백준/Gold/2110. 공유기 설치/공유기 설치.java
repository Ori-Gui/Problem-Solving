import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int ans = 0;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);
        
        int l = 1; 
        int r = houses[N - 1] - houses[0];
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            int cnt = 1;
            int wifi = houses[0];
            for (int i = 1; i < N; i++) {
                if (houses[i] - wifi >= mid) {
                    cnt += 1;
                    wifi = houses[i];
                }
            }
            
            if (cnt >= C) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        System.out.println(ans);
    }
}
