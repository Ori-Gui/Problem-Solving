import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] titles;
    static int[] limits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        TreeMap<Integer,String> map = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int limit = Integer.parseInt(st.nextToken());
            map.putIfAbsent(limit, title);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            sb.append(map.ceilingEntry(power).getValue()).append("\n");
        }
        System.out.print(sb);
    }
}
