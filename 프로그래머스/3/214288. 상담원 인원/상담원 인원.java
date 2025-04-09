import java.util.*;

class Solution {
    static int minWait = Integer.MAX_VALUE;
    static int[][] reqs;
    
        public int solution(int k, int n, int[][] reqsInput) {
        reqs = reqsInput; // 정적 변수에 저장 (상담 요청 정보)
        
        int[] comb = new int[k];
        int mentor = n - k; // 배정할 멘토 수
        Arrays.fill(comb, 1); // 상담유형별 멘토 1명 미리 배정
            
        dfs(mentor, 0, comb);  // 각 상담유형별 멘토배정 경우의 수 (중복조합)
        
        return minWait;
    }
    
    static void dfs(int mentor, int start, int[] comb) {
        if (mentor == 0) {
            System.out.println(Arrays.toString(comb));
            int wait = counsel(comb);
            minWait = Math.min(minWait, wait);
            return;
        }
        
        for (int i = start; i < comb.length; i++) {
            comb[i] += 1;
            dfs(mentor - 1, i, comb);
            comb[i] -= 1;
        }
    }
    
    static int counsel(int[] comb) {
        int wait = 0;
        
        for (int type = 1; type <= comb.length; type++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(); // 상담원 별 상담시작 시각 관리 (우선순위 큐)
            for (int i = 0; i < comb[type - 1]; i++) {
                pq.offer(0); // 초기 상담 가능시각 0
            }
            
            for (int[] req : reqs) {
                if (req[2] == type) {
                    int a = req[0];
                    int b = req[1];
                    int c = pq.poll(); // 가장 빨리 끝나는 멘토
                    int start = Math.max(a, c); // a < c 인 경우 대기 시간 발생
                    wait += (start - a);
                    pq.offer(start + b); // 상담 종료시간을 갱신
                }
            }
        }
        return wait; 
    }
}