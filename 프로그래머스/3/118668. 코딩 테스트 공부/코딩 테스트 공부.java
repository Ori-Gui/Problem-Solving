import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 1. 목표 알고력, 코딩력 설정 (각 문제의 최대 요구치)
        int maxAlp = 0, maxCop = 0;
        for (int[] prob : problems) {
            maxAlp = Math.max(maxAlp, prob[0]);
            maxCop = Math.max(maxCop, prob[1]);
        }
        // 초기치가 목표치를 초과하면 목표치로 맞춤
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        // 2. dp 배열 초기화
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        
        // 3. DP 전이 (알고리즘 공부, 코딩 공부, 문제 풀기)
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue;
                
                // 3-1. 알고리즘 공부: 알고력 1 증가
                if (i < maxAlp) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                // 3-2. 코딩 공부: 코딩력 1 증가
                if (j < maxCop) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                // 3-3. 각 문제 풀이
                for (int[] prob : problems) {
                    if (i >= prob[0] && j >= prob[1]) {
                        int newAlp = Math.min(maxAlp, i + prob[2]);
                        int newCop = Math.min(maxCop, j + prob[3]);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + prob[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}
