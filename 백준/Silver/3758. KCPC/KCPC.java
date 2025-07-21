import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int[][] scores;
    static int[] submitCnt;
    static int[] lastSubmit;

    static class KCPC implements Comparable<KCPC> {
        int id;
        int score;
        int submitCnt;
        int lastSubmit;

        KCPC(int id, int score, int submitCnt, int lastSubmit) {
            this.id = id;
            this.score = score;
            this.submitCnt = submitCnt;
            this.lastSubmit = lastSubmit;
        }

        @Override
        public String toString() {
            return id + " " + score + " " + submitCnt + " " + lastSubmit;
        }

        @Override
        public int compareTo(KCPC o) {
            if (this.score == o.score) {
                if (this.submitCnt == o.submitCnt) {
                    return Integer.compare(this.lastSubmit, o.lastSubmit); // 3. 마지막 제출 오름차순
                }
                return Integer.compare(this.submitCnt, o.submitCnt); // 2. 제출 횟수 오름차순
            }
            return Integer.compare(o.score, this.score); // 1. 점수 내림차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀수
            int k = Integer.parseInt(st.nextToken()); // 문제수
            int t = Integer.parseInt(st.nextToken()) - 1; // 우리팀 인덱스 (0부터 시작)
            int m = Integer.parseInt(st.nextToken()); // 로그수

            scores = new int[n][k];
            submitCnt = new int[n];
            lastSubmit = new int[n];
            Arrays.fill(lastSubmit, -1);

            for (int a = 0; a < m; a++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken()) - 1; // 팀 인덱스
                int j = Integer.parseInt(st.nextToken()) - 1; // 문제 인덱스
                int s = Integer.parseInt(st.nextToken()); // 점수

                scores[i][j] = Math.max(scores[i][j], s);
                submitCnt[i] += 1;
                lastSubmit[i] = a;
            }

            List<KCPC> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int total = 0;
                for (int j = 0; j < k; j++) {
                    total += scores[i][j];
                }
                list.add(new KCPC(i + 1, total, submitCnt[i], lastSubmit[i]));
            }

            Collections.sort(list);

            for (int r = 0; r < list.size(); r++) {
                if (list.get(r).id == t + 1) {
                    System.out.println(r + 1);
                    break;
                }
            }
        }
    }
}
