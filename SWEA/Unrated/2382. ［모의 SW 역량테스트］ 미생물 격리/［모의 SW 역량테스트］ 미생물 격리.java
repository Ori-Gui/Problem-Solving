import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static int N, M, K;
    static List<Microgroup> microGroups;
    // (상: 1, 하: 2, 좌: 3, 우: 4)
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Microgroup {
        int x;
        int y;
        int cnt;
        int d;

        Microgroup(int x, int y, int cnt, int d) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.d = d;
        }

        @Override
        public String toString() {
            return "[" + x + " " + y + " " + cnt + " " + d + "]";
        }
    }

    static void move(int m) {
        for (int i = 0; i < m; i++) {
            List<List<Microgroup>> mergeMgList = new ArrayList<>();

            for (Microgroup mg : microGroups) {
                int d = mg.d - 1;
                mg.x += dx[d];
                mg.y += dy[d];
                if (mg.x >= N - 1 || mg.y >= N - 1 || mg.x < 1 || mg.y < 1) {
                    thanos(mg);
                }
            }

            while (!microGroups.isEmpty()) {
                List<Microgroup> mgList = new ArrayList<>();
                Microgroup curMg = microGroups.get(0);
                microGroups.remove(0);
                mgList.add(curMg);

                int size = microGroups.size();
                for (int j = 0; j < size; j++) {
                    Microgroup compMg = microGroups.get(j);
                    if (curMg.x == compMg.x && curMg.y == compMg.y) {
                        mgList.add(compMg);
                        microGroups.remove(j);
                        size -= 1;
                        j -= 1;
                    }
                }
                mergeMgList.add(mgList);
            }

            for (List<Microgroup> mgList : mergeMgList) {
                microGroups.add(merge(mgList));
            }
        }
    }

    static void thanos(Microgroup mg) {
        mg.cnt /= 2;
        mg.d = mg.d % 2 == 0 ? mg.d - 1 : mg.d + 1;
    }

    static Microgroup merge(List<Microgroup> mgList) {
        if (mgList.size() >= 2) {
            int maxCnt = 0;
            int sum = 0;
            int x = 0, y = 0, d = 0;

            for (Microgroup mg : mgList) {
                sum += mg.cnt;
                maxCnt = Math.max(maxCnt, mg.cnt);
            }

            for (Microgroup mg : mgList) {
                if (mg.cnt == maxCnt) {
                    x = mg.x;
                    y = mg.y;
                    d = mg.d;
                }
            }
            return new Microgroup(x, y, sum, d);
        }
        return mgList.get(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            microGroups = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                microGroups.add(new Microgroup(x, y, cnt, d));
            }
            move(M);
            int result = 0;
            for (Microgroup mg : microGroups) {
                result += mg.cnt;
            }

            System.out.printf("#%d %d\n", tc, result);
        }
    }
}
