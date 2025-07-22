import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String keyword = br.readLine();
            set.add(keyword);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            int cnt = st.countTokens();
            for (int j = 0; j < cnt; j++) {
                String keyword = st.nextToken();
                if (set.contains(keyword)) {
                    set.remove(keyword);
                } 
            }
            sb.append(set.size()).append("\n");
        }
        System.out.print(sb);
    }
}
