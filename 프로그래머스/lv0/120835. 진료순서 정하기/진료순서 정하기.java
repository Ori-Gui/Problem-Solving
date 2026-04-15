import java.util.*;

class Solution {
    public int[] solution(int[] emergency) { 
        int len = emergency.length;
        int[] answer = new int[len];
        Integer[] arr = new Integer[len];
        
        for (int i = 0; i < len; i++) { 
            arr[i] = emergency[i];
        }
        Arrays.sort(arr, Comparator.reverseOrder());
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (emergency[i] == arr[j]) {
                    answer[i] = j+1;
                }
            }
        }
        
        return answer;
    }
}