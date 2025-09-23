import java.io.*;

public class Main {
    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i += 1) {
            words[i] = br.readLine();
        }

        int bestL = -1;
        int ansI = 0;
        int ansJ = 1;

        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                int curL = Math.min(words[i].length(), words[j].length());
                int cur = 0;
                for (int k = 0; k < curL; k += 1) {
                    if (words[i].charAt(k) != words[j].charAt(k)) break;
                    cur += 1;
                }

                if (cur > bestL || (cur == bestL && (i < ansI || (i == ansI && j < ansJ)))) {
                    bestL = cur;
                    ansI = i;
                    ansJ = j;
                }
            }
        }

        System.out.println(words[ansI]);
        System.out.println(words[ansJ]);
    }
}
