import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        int[] PMN = new int[3];


        for (int i = 0; i < 3; i++) {
            PMN[i] = sc.nextInt();
        }

        for (int i = 0; i < 3; i++) {
            int cnt = 0;
            int dog1 = PMN[i] % (A + B);
            int dog2 = PMN[i] % (C + D);

            if (dog1 > 0 && dog1 <= A) {
                cnt++;
            }

            if (dog2 > 0 && dog2 <= C) {
                cnt++;
            }

            System.out.println(cnt);
        }
    }
}
