import java.util.*;

class Solution {
    static boolean[] v;
    static int[] nums;
    static int t;
    static int cnt;
    
    public void dfs(int idx, int[] sel) {
        if (idx == nums.length) {
            int sum = 0;
            for (int n : sel) {
                sum += n;
            }
            if (sum == t) {
                cnt += 1;
            }
            return;
        }

        sel[idx] = nums[idx];
        dfs(idx + 1, sel);
        
        sel[idx] = - nums[idx];
        dfs(idx + 1, sel);
    }
    
    public int solution(int[] numbers, int target) {
        nums = numbers.clone();
        v = new boolean[numbers.length];
        t = target;
        cnt = 0;
        
        dfs(0, new int[numbers.length]);

        return cnt;
    }
}