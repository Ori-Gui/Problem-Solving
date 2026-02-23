import java.io.*;

public class Main {
    private static final int VOWELS =
            (1 << 0)  // a
          | (1 << 4)  // e
          | (1 << 8)  // i
          | (1 << 14) // o
          | (1 << 20); // u

    public static boolean isVowel(char c) {
        if (c < 'a' || c > 'z') return false;
        int idx = c - 'a';
        return (VOWELS & (1 << idx)) != 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String pw = br.readLine();
            if (pw.equals("end")) break;

            boolean a1 = false;
            boolean a2 = true;
            boolean a3 = true;
            
            char[] pwArr = pw.toCharArray();
            int v2 = 0;
            int c2 = 0;
            char prev = '0';

            for (char c : pwArr) {
                if (isVowel(c)) {
                    a1 = true;
                    v2 += 1;
                    c2 = 0;
                } else {
                    v2 = 0;
                    c2 += 1;
                }

                if (v2 >= 3 || c2 >= 3) {
                    a2 = false;
                    break;
                }

                if (prev != '0') {
                    if (prev == c && c != 'e' && c != 'o') {
                        a3 = false;
                        break;
                    }
                }
                prev = c;
            }

            if (a1 && a2 && a3) {
                sb.append("<").append(pw).append("> is acceptable.").append("\n");
            } else {
                sb.append("<").append(pw).append("> is not acceptable.").append("\n");
            }
        }
        
        System.out.println(sb);
    }
}
