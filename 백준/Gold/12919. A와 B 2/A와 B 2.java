import java.io.*;

public class Main {
    static StringBuilder S, T;
    static int ans = 0;

    static void dfs(StringBuilder sb) {
        if (sb.compareTo(S) == 0) {
            ans = 1;
            return;
        }
        if (sb.length() == S.length()) return;

        int idx = sb.length() - 1;
        if (sb.charAt(idx) == 'A') {
            dfs(sb.deleteCharAt(idx));
            sb.append('A');
        }
        if (sb.charAt(0) == 'B') {
            dfs(sb.deleteCharAt(0).reverse());
            sb.append('B').reverse();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());
        dfs(T);

        System.out.println(ans);
    }
}