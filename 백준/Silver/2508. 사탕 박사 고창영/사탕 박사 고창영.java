import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int cnt = 0;
            br.readLine();

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            String[] candy = new String[r];
            for (int i = 0; i < r; i++) {
                candy[i] = br.readLine().trim();
            }

            // 가로: ">o<" 패턴, 세로: "v", "o", "^" 패턴
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    // 가로 패턴 검사
                    if (j + 2 < c) {
                        if (candy[i].charAt(j) == '>' &&
                            candy[i].charAt(j + 1) == 'o' &&
                            candy[i].charAt(j + 2) == '<') {
                            cnt++;
                        }
                    }
                    // 세로 패턴 검사
                    if (i + 2 < r) {
                        if (candy[i].charAt(j) == 'v' &&
                            candy[i + 1].charAt(j) == 'o' &&
                            candy[i + 2].charAt(j) == '^') {
                            cnt++;
                        }
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
