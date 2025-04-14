import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static class Lecture {
        int s;
        int e;

        Lecture(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 강의 시간 : 우선순위 큐 (힙)
        // 정렬 기준 - 강의 시작 시간 (오름차순) -> 강의 종료 시간 (오름차순)
        PriorityQueue<Lecture> lectures = new PriorityQueue<>(
                Comparator.comparing((Lecture l) -> l.s)
                        .thenComparing(l -> l.e));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(s, e));
        }

        // 강의실 별 종료 시간을 저장 : 우선순위 큐 (힙)
        // 정렬 기준 - 강의실 별 종료 시간 (오름차순) (Integer 우선순위 큐 기본 정렬)
        PriorityQueue<Integer> roomEnds = new PriorityQueue<>();

        while (!lectures.isEmpty()) {
            Lecture curl = lectures.poll();
            // 가장 먼저 끝나는 강의 종료 시간이 현재 강의의 시작 시간보다 작거나 같으면, 해당 강의실을 재사용
            if (!roomEnds.isEmpty() && roomEnds.peek() <= curl.s) {
                roomEnds.poll();
            }
            // 현재 강의의 종료 시간을 큐에 추가 (새로운 강의실 할당 또는 기존 강의실 업데이트)
            roomEnds.offer(curl.e);
        }
        System.out.println(roomEnds.size());
    }
}