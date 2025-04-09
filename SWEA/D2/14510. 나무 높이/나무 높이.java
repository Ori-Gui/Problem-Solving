import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            trees = new int[N];
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(trees);

            int highest = trees[N - 1];
            int one = 0;
            int two = 0;

            for (int i = 0; i < N; i++) {
                one += (highest - trees[i]) % 2;
                two += (highest - trees[i]) / 2;
            }

            int min = Math.min(one, two);

            one -= min;
            two -= min;
            int day = min * 2;

            if (one > 0) {
                day += 2 * one - 1;
            }
            if (two > 0) {
                day += two + 1 + ((two - 1) / 3);
            }

            System.out.printf("#%d %d\n", t, day);
        }
    }
}

// 1 1 2 2 3 -> 4일 컷
// one = 2, two = 2

// 1 1 1 1 9 -> 짝수 컷
// one = 0, two = 16

// 1 1 1 1 2 -> 홀수 컷
// one = 4, two

/* one
* 1 1일
* 2 3일
* 3 5일
* 4 7일
* 5 9일
* 6 11일
*/

/* two
* 1 (2) -> 0 2 / 2일
* 2 (4) -> 1 2 1 / 3일
* 3 (6) -> 1 2 1 2 / 4일
* 4 (8) -> 1 2 1 2 0 2 / 6일
* 5 (10) -> 1 2 1 2 1 2 1 / 7일
* 6 (12) -> 1 2 1 2 1 2 1 2 / 8일
* 7 (14) -> 1 2 1 2 1 2 1 2 0 2 / 10일
*/
