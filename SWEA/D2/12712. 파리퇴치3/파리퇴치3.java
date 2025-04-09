import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
             
            int[][] arr = new int[n][n];
             
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int max = 0;
     
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int temp_sum1 = arr[i][j];
                    int temp_sum2 = arr[i][j];
                    for (int k = 1; k < m; k++) {        
                        if (i-k >= 0) {
                            temp_sum1 += arr[i-k][j];
                        }
                        if (i+k < n) {
                            temp_sum1 += arr[i+k][j];
                        }
                        if (j-k >= 0) {
                            temp_sum1 += arr[i][j-k];
                        }
                        if (j+k < n) {
                            temp_sum1 += arr[i][j+k];
                        }
                         
                        if (i-k >= 0 && j-k >= 0) {
                            temp_sum2 += arr[i-k][j-k];
                        }
                        if (i+k < n && j+k < n) {
                            temp_sum2 += arr[i+k][j+k];
                        }
                        if (i-k >= 0 && j+k < n) {
                            temp_sum2 += arr[i-k][j+k];
                        }
                        if (i+k < n && j-k >= 0) {
                            temp_sum2 += arr[i+k][j-k];
                        }
                         
                        max = Math.max(max, temp_sum1);
                        max = Math.max(max, temp_sum2);
                    }
                }
            }
            System.out.println("#" + t + " " + max);
        }
    }
}