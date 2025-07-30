import java.io.*;
import java.util.*;

public class Main {
    static String str;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder(str);
        int cursor = sb.length();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);

            if (c == 'L') {
                cursor = cursor == 0 ? 0 : cursor - 1;
            } else if (c == 'D') {
                cursor = cursor == sb.length() ? cursor : cursor + 1; 
            } else if (c == 'B') {
                if (cursor == 0) continue;
                sb.delete(cursor - 1, cursor);
                cursor -= 1;
            } else if (c == 'P') {
                sb.insert(cursor, st.nextToken());
                cursor += 1;
            }
        }

        System.out.println(sb);
    }
}
