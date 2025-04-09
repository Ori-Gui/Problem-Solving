import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String n = br.readLine();
		String[] arr = n.split("");
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += Integer.parseInt(arr[i]);
		}
		
		System.out.println(sum);
	} 
}
