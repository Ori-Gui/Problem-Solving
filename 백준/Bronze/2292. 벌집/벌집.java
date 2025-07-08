import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(1);
        } else {
            int k = (N - 2) / 6 + 1;
            int i = 1;
            int j = 2;
            while (true) {
                if (k <= i) {
                    System.out.println(j);
                    break;
                }
                i += j;
                j += 1;
            }
        }
    }
}
