import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Integer> minHq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (minHq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(minHq.poll());
                }
                sb.append('\n');
            } else {
                minHq.offer(x);
            }
        }
        
        System.out.print(sb);
    }
}
