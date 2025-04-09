import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> nums = new ArrayList<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
     
        while (st.hasMoreTokens()) {
    		nums.add(Integer.parseInt(st.nextToken()));
		}
        Collections.sort(nums);
        System.out.println(nums.get(n/2));
    }
}