import java.io.*;

public class Main {
    static int N;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        int kbs1 = 0, kbs2 = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            if (arr[i].equals("KBS1")) {
                kbs1 = i;
            }
            if (arr[i].equals("KBS2")) {
                kbs2 = i;
            }
        }

        StringBuffer sb = new StringBuffer();

        if (kbs1 < kbs2) {
            sb.append("1".repeat(kbs1));
            sb.append("4".repeat(kbs1));
            sb.append("1".repeat(kbs2));
            sb.append("4".repeat(kbs2 - 1));
        } else {
            sb.append("1".repeat(kbs1));
            sb.append("4".repeat(kbs1));
            kbs2 += 1;
            sb.append("1".repeat(kbs2));
            sb.append("4".repeat(kbs2 - 1));
        }
        System.out.println(sb);
    }
}
