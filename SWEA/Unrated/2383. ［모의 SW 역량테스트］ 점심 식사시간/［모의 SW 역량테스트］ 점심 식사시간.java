import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static int N;
    static int[][] map;

    static class Person {
        int x;
        int y;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Stair {
        int x;
        int y;
        int len;

        Stair(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            ArrayList<Person> persons = new ArrayList<>();
            ArrayList<Stair> stairs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        persons.add(new Person(i, j));
                    } else if (map[i][j] >= 2) { // 계단: 숫자(2~10)는 계단의 길이
                        stairs.add(new Stair(i, j, map[i][j]));
                    }
                }
            }

            int size = persons.size();
            int answer = Integer.MAX_VALUE;

            // 0번 계단과 1번 계단에 각각 배정하는 경우의 수 2^size
            for (int mask = 0; mask < (1 << size); mask++) {
                ArrayList<Integer> arrive1 = new ArrayList<>();
                ArrayList<Integer> arrive2 = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    Person p = persons.get(i);
                    // i번째 사람의 계단 배정: 0번 계단 or 1번 계단
                    // i번째 비트가 0이면 0번 계단, 1이면 1번 계단을 이용
                    if ((mask & (1 << i)) == 0) {
                        Stair s = stairs.get(0);
                        // 이동 시간 = 맨해튼 거리 + 1 (계단 입구 도착 후 1분 대기)
                        int time = Math.abs(p.x - s.x) + Math.abs(p.y - s.y) + 1;
                        arrive1.add(time);
                    } else {
                        Stair s = stairs.get(1);
                        int time = Math.abs(p.x - s.x) + Math.abs(p.y - s.y) + 1;
                        arrive2.add(time);
                    }
                }

                // 각 계단별 시뮬레이션: 대기열을 고려하여 내려가는 완료 시간을 계산
                int time0 = down(arrive1, stairs.get(0).len);
                int time1 = down(arrive2, stairs.get(1).len);
                // 해당 배정에서 전체 소요시간은 두 계단에서 늦게 끝난 시간
                int totalTime = Math.max(time0, time1);
                answer = Math.min(answer, totalTime);
            }

            System.out.println("#" + tc + " " + answer);

        }

    }

    static int down(ArrayList<Integer> arrive, int len) {
        int size = arrive.size();
        int[] finish = new int[size];

        if (size == 0) {
            return 0;
        }

        // 도착 시간 오름차순 정렬
        Collections.sort(arrive);

        // 첫 3명은 도착하는 대로 바로 내려가기
        for (int i = 0; i < size; i++) {
            if (i < 3) {
                finish[i] = arrive.get(i) + len;
            } else {
                // 3명 이상일 경우, 이전 3번째 사람이 끝나는 시간까지 대기
                finish[i] = Math.max(arrive.get(i), finish[i - 3]) + len;
            }
        }
        
        return finish[size - 1];
    }
}
