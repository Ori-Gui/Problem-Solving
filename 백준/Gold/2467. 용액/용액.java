import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] yong = new int[n];
        for (int i = 0; i < n; i++) {
            yong[i] = Integer.parseInt(st.nextToken());
        }

        // 0에 가까운 혼합용액 초기화
        long minAbs = (long) 2e9;
        int l = 0, r = n - 1;
        int y1 = 0, y2 = 0;

        // 투 포인터 알고리즘
        while (l < r) {
            int curSum = yong[l] + yong[r];
            long absSum = Math.abs(curSum);
            // 현재 두 용액의 합의 절댓값이 이전 최소값보다 작으면 갱신
            if (minAbs > absSum) {
                y1 = yong[l];
                y2 = yong[r];
                minAbs = absSum;
                // 합이 0이면 바로 break
                if (minAbs == 0) {
                    break;
                }
            }
            // 혼합용액의 합이 0보다 작거나 같으면 왼쪽 포인터를 오른쪽으로 이동
            if (curSum <= 0) {
                l++;
            } else { // 0보다 크면 오른쪽 포인터를 왼쪽으로 이동
                r--;
            }
        }
        System.out.println(y1 + " " + y2);
    }
}
