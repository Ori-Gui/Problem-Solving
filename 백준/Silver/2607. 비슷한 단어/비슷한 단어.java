import java.io.*;

public class Main {
    static int N;
    static String[] words;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        cnt = 0;

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        char[] w = words[0].toCharArray();

        for (int i = 1; i < N; i++) {
            boolean[] v = new boolean[w.length];
            char[] wi = words[i].toCharArray();
            int wl = w.length;
            int wil = wi.length;
            int charCnt = 0;
            
            if (Math.abs(wl - wil) <= 1) {
                int check = Math.min(wl, wil);
                for (int j = 0; j < wi.length; j++) {
                    for (int k = 0; k < w.length; k++) {
                        if (wi[j] == w[k] && !v[k]) {
                            v[k] = true;
                            charCnt += 1;
                            break;
                        }
                    }
                }
                if (check == charCnt) {
                    cnt += 1;
                } else if (wl == wil && charCnt == check - 1) {
                    cnt += 1;
                }
            } 
        }
        System.out.println(cnt);
    }
}