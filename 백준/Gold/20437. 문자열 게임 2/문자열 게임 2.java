import java.io.*;
import java.util.*;

public class Main {
    static int T, K;
    static char[] str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            str = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());

            if (K == 1) {
                System.out.println(1 + " " + 1);
                continue;
            }

            ArrayList<Integer>[] alphabet = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                alphabet[i] = new ArrayList<>();
            }

            for (int i = 0; i < str.length; i++) {
                alphabet[str[i] - 'a'].add(i);
            }

            int minL = Integer.MAX_VALUE;
            int maxL = -1;

            for (int i = 0; i < 26; i++) {
                ArrayList<Integer> list = alphabet[i];
                if (list.size() >= K) {
                    for (int j = 0; j + K - 1 < list.size(); j++) {
                        int start = list.get(j);
                        int end = list.get(j + K - 1);
                        int l = end - start + 1;

                        if (l < minL) minL = l;
                        if (l > maxL) maxL = l;
                    }
                }
            }

            if (maxL == -1) {
                System.out.println(-1);
            } else {
                System.out.println(minL + " " + maxL);
            }
        }
    }
}
