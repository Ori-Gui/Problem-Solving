class Solution {
    static long limit;
    static int maxV = 0;
    
    public int solution(int[] diffs, int[] times, long limit) {
        for (int i = 0; i < diffs.length; i++) {
            maxV = Math.max(maxV, diffs[i]);
        }
        int l = 1;
        int r = maxV;
        int ans = 0;
 
        while(l <= r) {
            int curLv = (l + r) / 2;
            long sumT = 0;
            for (int i = 0; i < diffs.length; i++) {
                if (diffs[i] <= curLv) {
                    sumT += times[i];
                } else if (diffs[i] > curLv) {
                    sumT += (diffs[i] - curLv) * (times[i-1] + times[i]) + times[i];
                }
            }  
            if (sumT > limit) {
                l = curLv + 1;
            } else if (sumT <= limit) {
                r = curLv - 1;
                ans = curLv;
            }
        }
        return ans;
    }
}