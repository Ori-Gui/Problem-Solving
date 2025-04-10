import java.util.*;
import java.io.*;

public class Main {

    static class Baseball {
        int[] nums;
        int strike;
        int ball;

        Baseball(int x, int y, int z, int strike, int ball) {
            this.nums = new int[]{x, y, z};
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Baseball> bblist = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String numString = st.nextToken();
            int x = numString.charAt(0) - '0';
            int y = numString.charAt(1) - '0';
            int z = numString.charAt(2) - '0';
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bblist.add(new Baseball(x, y, z, s, b));
        }

        int cnt = 0;

        for (int k = 123; k <= 987; k++) {
            int num100 = k / 100;
            int num10 = (k / 10) % 10;
            int num1 = k % 10;

            if (num10 == 0 || num1 == 0 || num100 == num10 || num10 == num1 || num100 == num1) {
                continue;
            }

            boolean isPossible = true;

            for (Baseball bb : bblist) {
                int s = 0;
                int b = 0;

                int[] num = { num100, num10, num1 };
                int[] guess = bb.nums;

                for (int i = 0; i < 3; i++) {
                    if (num[i] == guess[i]) {
                        s += 1;
                    } else if (num[i] == guess[0] ||
                            num[i] == guess[1] ||
                            num[i] == guess[2]) {
                        b += 1;
                    }
                }

                if (s != bb.strike || b != bb.ball) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
