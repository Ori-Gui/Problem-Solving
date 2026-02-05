import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int P = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < P; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            List<Integer> line = new ArrayList<>(20);
            int back = 0;

            for (int i = 0; i < 20; i++) {
                int cur = Integer.parseInt(st.nextToken());
                int pos = line.size();

                for (int j = 0; j < line.size(); j++) {
                    if (line.get(j) > cur) {
                        pos = j;
                        break;
                    }
                }

                back += (line.size() - pos);
                line.add(pos, cur);
            }

            sb.append(t).append(" ").append(back).append("\n");
        }

        System.out.print(sb);
    }
}
