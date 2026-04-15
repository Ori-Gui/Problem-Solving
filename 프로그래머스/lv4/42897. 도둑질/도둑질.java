import java.util.*;

class Solution {
    public int solution(int[] money) {
        int len = money.length - 1;
        int[] dp = new int[len];
        int[] dp2 = new int[len];
        
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        dp2[0] = money[1];
        dp2[1] =  Math.max(money[1], money[2]);
        
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i+1]);
        }
        
        int ans = Math.max(dp[len-1], dp2[len-1]);
        
        return ans;
    }
}