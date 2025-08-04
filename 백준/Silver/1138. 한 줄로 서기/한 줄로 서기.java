import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] counts;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        counts = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            counts[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            list.add(counts[i - 1], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(' ');
            }
        }

        System.out.println(sb);
    }
}
