import java.io.*;

public class Main {
    static String S;
    static int cnt0 = 0;
    static int cnt1 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().trim();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '0') {
                cnt0 += 1;
            } else {
                cnt1 += 1;
            }
        }

        cnt0 /= 2;
        cnt1 /= 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '0') {
                if (cnt0 > 0) {
                    cnt0 -= 1;
                    sb.append('0');
                } 
            } else {
                if (cnt1 <= 0) {
                    sb.append('1');
                }
                cnt1 -= 1;
            }
        }
        System.out.println(sb);
    }
}