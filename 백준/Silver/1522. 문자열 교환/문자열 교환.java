import java.io.*;

public class Main {
    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        int n = s.length();
        int cntA = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') cntA += 1;
        }

        if (cntA == 0 || cntA == n) {
            System.out.println(0);
            return;
        }

        char[] circle = (s + s).toCharArray();
        int cntB = 0;
        for (int i = 0; i < cntA; i++) {
            if (circle[i] == 'b') cntB += 1;
        }

        int ans = cntB;
        for (int i = 0; i < n; i++) {
            if (circle[i] == 'b') cntB -= 1;
            if (circle[i + cntA] == 'b') cntB += 1;
            ans = Math.min(ans, cntB);
        }
        System.out.println(ans);
    }
}