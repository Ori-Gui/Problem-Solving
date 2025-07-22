import java.io.*;

public class Main {
    static String S;
    static int cnt0 = 0;
    static int cnt1 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();

        for (int i = 0; i < S.length(); i++) {
            cnt0 += S.charAt(i) == '0' ? 1 : 0;
            cnt1 += S.charAt(i) == '1' ? 1 : 0;
        }

        cnt0 /= 2;
        cnt1 /= 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt0; i++) {
            sb.append(0);
        }
        for (int i = 0; i < cnt1; i++) {
            sb.append(1);
        }
        System.out.println(sb);
    }
}
