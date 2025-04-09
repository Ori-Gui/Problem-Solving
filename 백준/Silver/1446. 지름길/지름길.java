import java.util.*;

public class Main {

    public static class ShortCut {
        int s;
        int e;
        int d;

        public ShortCut(int s, int e, int d) {
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int D = sc.nextInt();
        List<ShortCut> shortcuts = new ArrayList<>();
        int dp[] = new int[D + 1];

        for (int i = 1; i <= D; i++) {
            dp[i] = i;
        }

        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int d = sc.nextInt();
            shortcuts.add(new ShortCut(s, e, d));
        }

        // 지름길 end 기준 -> 지름길 start 기준 (오름차순 정렬), dp계산 순서대로 하기
        shortcuts.sort(Comparator.comparingInt((ShortCut shortCut) -> shortCut.e));

        for (int i = 0; i < N; i++) {
            ShortCut cur = shortcuts.get(i);

            if (cur.e <= D) {
                dp[cur.e] = Math.min(dp[cur.e], dp[cur.s] + cur.d);
                for (int j = cur.e; j < D + 1; j++) {
                    dp[j] = Math.min(dp[j], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[D]);
    }
}
