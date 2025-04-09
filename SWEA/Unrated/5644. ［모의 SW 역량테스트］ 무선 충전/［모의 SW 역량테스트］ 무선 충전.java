import java.util.*;

public class Solution {
    static int T;
    static int M, A;
    static int[] moveA;
    static int[] moveB;

    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};
    static BatteryCharger[] bcList;
    static boolean[][][] coverage;


    // 좌표 클래스
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    // 무선 충전기 클래스
    static class BatteryCharger {
        int x;
        int y;
        int c;
        int p;

        BatteryCharger(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    // 사용자 이동
    static void move(Point p, int d) {
        // d: 0(이동없음), 1(상), 2(우), 3(하), 4(좌)
        p.x += dx[d];
        p.y += dy[d];
    }

    // 맨해튼 거리
    static int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    // 모든 (bcA 후보, bcB 후보) 조합을 따져 보고, 가장 충전량이 큰 값을 찾는다.
    // 만약 bcA나 bcB가 비어있으면, "연결 안 함"을 -1로 가정
    static int getCharge(Point userAp, Point userBp) {
        // 사용자 A, 사용자 B가 각각 접속 가능한 BC 목록
        List<Integer> bcA = new ArrayList<>();
        List<Integer> bcB = new ArrayList<>();

        for (int i = 0; i < A; i++) {
            if (coverage[i][userAp.x][userAp.y]) {
                bcA.add(i);
            }
            if (coverage[i][userBp.x][userBp.y]) {
                bcB.add(i);
            }
        }

        int maxCharge = 0;

        if (bcA.isEmpty()) {
            bcA.add(-1);
        }
        if (bcB.isEmpty()) {
            bcB.add(-1);
        }

        for (int iA : bcA) {
            for (int iB : bcB) {
                int chargeA = 0;
                int chargeB = 0;

                if (iA == -1 && iB == -1) {
                    // 둘 다 연결 불가
                } else if (iA == -1) {
                    // A는 연결 불가, B만 연결
                    chargeB = bcList[iB].p;
                } else if (iB == -1) {
                    // B는 연결 불가, A만 연결
                    chargeA = bcList[iA].p;
                } else {
                    // 둘 다 연결 가능
                    if (iA == iB) {
                        // 같은 BC 선택 -> 성능 p 반반
                        chargeA = bcList[iA].p / 2;
                        chargeB = bcList[iA].p / 2;
                    } else {
                        // 다른 BC 선택 -> 성능 p 그대로
                        chargeA = bcList[iA].p;
                        chargeB = bcList[iB].p;
                    }
                }
                maxCharge = Math.max(maxCharge, chargeA + chargeB);
            }
        }
        return maxCharge;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            M = sc.nextInt();
            A = sc.nextInt();
            moveA = new int[M];
            moveB = new int[M];
            bcList = new BatteryCharger[A];

            for (int i = 0; i < M; i++) {
                moveA[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                moveB[i] = sc.nextInt();
            }

            for (int i = 0; i < A; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                bcList[i] = new BatteryCharger(x, y, c, p);
            }

            // 무선 충전기별 커버 가능한 좌표
            coverage = new boolean[A][11][11];
            for (int i = 0; i < A; i++) {
                BatteryCharger bc = bcList[i];
                for (int x = 1; x <= 10; x++) {
                    for (int y = 1; y <= 10; y++) {
                        if (dist(bc.x, bc.y, x, y) <= bc.c) {
                            coverage[i][x][y] = true;
                        }
                    }
                }
            }

            // 사용자 시작 위치 설정
            Point userAp = new Point(1, 1);
            Point userBp = new Point(10, 10);

            int sum = 0;

            // 0초 ~ M초, 총 M+1번
            for (int t = 0; t <= M; t++) {
                // 현재 시점(t)의 최적 충전량을 누적
                int maxCharge = getCharge(userAp, userBp);
                sum += maxCharge;
                // t == M이면 이동할 필요 없음 (마지막 시점)
                if (t == M) {
                    break;
                }
                // 사용자 이동
                move(userAp, moveA[t]);
                move(userBp, moveB[t]);
            }
            System.out.printf("#%d %d\n", tc, sum);
        }
    }
}
