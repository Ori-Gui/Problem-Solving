import java.io.*;
import java.util.*;

public class Solution {

    static int maxTaste = 0;

    public static void dfs(int idx, int sumTaste, int sumCal, int n, int l, int[] taste, int[] cal) {
        if (sumCal > l) {
            return;
        }

        if (idx == n) {
            maxTaste = Math.max(maxTaste, sumTaste);
            return;
        }

        dfs(idx + 1, sumTaste, sumCal, n, l, taste, cal);
        dfs(idx + 1, sumTaste + taste[idx], sumCal + cal[idx], n, l, taste, cal);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int[] taste = new int[n];
            int[] cal = new int[n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                taste[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            maxTaste = 0;

            dfs(0, 0, 0, n, l, taste, cal);

            System.out.println("#" + t + " " + maxTaste);
        }
    }
}
