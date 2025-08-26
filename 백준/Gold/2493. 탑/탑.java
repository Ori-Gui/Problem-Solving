import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] tops;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tops = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<int[]> s = new Stack<>();

        for (int i = 0; i < N; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < N; i++) {
            int h = tops[i];
            while (!s.isEmpty() && s.peek()[1] <= h) {
                s.pop();
            }

            if (s.isEmpty()) {
                sb.append(0).append(" ");
            } else {
                sb.append(s.peek()[0] + 1).append(" ");
            }
            s.push(new int[]{i, h});
        }
        System.out.println(sb);
    }
}
