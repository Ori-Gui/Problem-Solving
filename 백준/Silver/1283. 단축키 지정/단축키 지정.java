import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] options;
    static Set<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        options = new String[N];

        for (int i = 0; i < N; i++) {
            options[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = options[i];
            int pick = -1;
            boolean isStart = false;

            for (int j = 0; j < s.length(); j++) {
                if (j == 0) { 
                    isStart = true;
                } else {
                    isStart = s.charAt(j) != ' ' && s.charAt(j - 1) == ' ';
                }
                if (isStart) {
                    char c = s.charAt(j);
                    char key = Character.toLowerCase(c);
                    if (!set.contains(key) && c != ' ') {
                        pick = j;
                        break;
                    }
                }
            }

            if (pick == -1) {
                for (int j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    char key = Character.toLowerCase(c);
                    if (!set.contains(key) && c != ' ') {
                        pick = j;
                        break;
                    }
                }
            }

            if (pick != -1) {
                set.add(Character.toLowerCase(s.charAt(pick)));

                StringBuilder sb2 = new StringBuilder();
                sb2.append(s.substring(0, pick));
                sb2.append('[').append(s.charAt(pick)).append(']');
                sb2.append(s.substring(pick + 1));
                sb.append(sb2).append('\n');
            } else {
                sb.append(s).append('\n');
            }
        }
        System.out.print(sb);
    }
}
