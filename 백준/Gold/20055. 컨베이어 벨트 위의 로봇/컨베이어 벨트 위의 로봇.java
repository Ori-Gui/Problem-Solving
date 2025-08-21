import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] conveyor;
    static int[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        conveyor = new int[2 * N];
        robots = new int[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            conveyor[i] = Integer.parseInt(st.nextToken()); 
        }

        int t = 0;
        while (true) {
            t += 1;
            int tempC = conveyor[2*N-1];
            int tempR = robots[2*N-1];
            for (int i = 2 * N - 1; i > 0; i--) {
                conveyor[i] = conveyor[i-1];
                robots[i] = robots[i-1];
            }
            conveyor[0] = tempC;
            robots[0] = tempR;
            
            if (robots[N-1] == 1) robots[N-1] = 0;

            for (int i = N-1; i > 0; i--) {   
                if (robots[i] == 0 && robots[i-1] == 1 && conveyor[i] >= 1) {
                    robots[i] = 1;
                    robots[i-1] = 0;
                    conveyor[i] -= 1;
                }
            }

            if (robots[N-1] == 1) robots[N-1] = 0;

            if (conveyor[0] > 0) { 
                robots[0] = 1; 
                conveyor[0] -= 1;
            }

            int cnt = 0;
            for (int i = 0; i < 2 * N; i++) {
                if (conveyor[i] == 0) cnt += 1;
            }
            if (cnt >= K) break;
        }
        System.out.println(t);
    }
}
