import java.util.*;

class Solution {
    public int solution(int[] money) {
        int len = money.length;
        int[] dp = new int[len-1];
        int[] dp2 = new int[len-1];
        
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        dp2[0] = money[1];
        dp2[1] =  Math.max(money[1], money[2]);
        
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i+1]);
        }
        
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(dp2));
        
        int ans = Math.max(dp[len-2], dp2[len-2]);
        
        return ans;
    }
}