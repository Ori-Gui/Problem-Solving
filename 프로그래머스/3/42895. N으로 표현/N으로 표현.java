import java.util.*;

class Solution {
    
    public int solution(int N, int number) {
        int[] dp = new int[32001];
        Arrays.fill(dp, 32000);
        
        if (N != 1) {
            dp[N] = 1;
            dp[N - N] = 2; // 0
            dp[N / N] = 2; // 1
            dp[N + N] = 2;
            dp[N * N] = 2;
            
            int temp = N * 10 + N;
            int k = 2;
            while (true) {
                if (temp > 32000) {
                    break;
                }
                dp[temp] = k; 
                temp = temp * 10 + N;
                k += 1;
            }
            
            for (int a = 0; a < 2; a++) {
                for (int i = 0; i < 8000; i++) {
                    for (int j = 0; j < 8000; j++) {
                        if (dp[i] == 32000 && dp[j] == 32000) continue;

                        if (i+j <= 32000) {
                            dp[i+j] = Math.min(dp[i+j], dp[i] + dp[j]);
                        }           
                        if (i-j >= 0) {
                            dp[i-j] = Math.min(dp[i-j], dp[i] + dp[j]);
                        }

                        if (i*j <= 32000) {
                            dp[i*j] = Math.min(dp[i*j], dp[i] + dp[j]);
                        }

                        if (i != 0 && j != 0) {
                            dp[i/j] = Math.min(dp[i/j], dp[i] + dp[j]);
                        }
                    }
                }
            }
        } else {
            dp[N] = 1;
            dp[N - N] = 2; // 0
            dp[N + N] = 2;
            
            int temp = N * 10 + N;
            int k = 2;
            while (true) {
                if (temp > 32000) {
                    break;
                }
                dp[temp] = k; 
                temp = temp * 10 + N;
                k += 1;
            }
            
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        if (dp[i] == 32000 && dp[j] == 32000) continue;

                        if (i+j <= 32000) {
                            dp[i+j] = Math.min(dp[i+j], dp[i] + dp[j]);
                        }           
                        if (i-j >= 0) {
                            dp[i-j] = Math.min(dp[i-j], dp[i] + dp[j]);
                        }

                        if (i*j <= 32000) {
                            dp[i*j] = Math.min(dp[i*j], dp[i] + dp[j]);
                        }

                        if (i != 0 && j != 0) {
                            dp[i/j] = Math.min(dp[i/j], dp[i] + dp[j]);
                        }
                    }
                }
        }
             
        int answer = dp[number] > 8 ? -1 : dp[number];
        return answer;
    }
}