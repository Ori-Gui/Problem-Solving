import java.util.*;

public class Solution {
    static int T;
    static int winCnt;
    static int loseCnt;
    static int[] gu = new int[9];
    static int[] in = new int[9];

    static void game(int[] sel) {
        int sumGu = 0;
        int sumIn = 0;

        for (int i = 0; i < 9; i++) {
            if (gu[i] > sel[i]) {
                sumGu += gu[i] + sel[i];
            } else {
                sumIn += gu[i] + sel[i];
            }
        }

        if (sumGu > sumIn) {
            winCnt++;
        } else if (sumGu < sumIn) {
            loseCnt++;
        }
    }

    static void perm(int idx, int[] sel, boolean[] v) {
        if (idx == 9) {
            game(sel);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!v[i]) {
                v[i] = true;
                sel[idx] = in[i];
                perm(idx + 1, sel, v);
                v[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            boolean[] v = new boolean[19];

            winCnt = 0;
            loseCnt = 0;

            for (int i = 0; i < 9; i++) {
                gu[i] = sc.nextInt();
                v[gu[i]] = true;
            }

            int k = 0;
            for (int i = 1; i <= 18; i++) {
                if (!v[i]) {
                    in[k++] = i;
                }
            }

            perm(0, new int[9], new boolean[9]);

            System.out.printf("#%d %d %d\n", t, winCnt, loseCnt);
        }
    }
}
