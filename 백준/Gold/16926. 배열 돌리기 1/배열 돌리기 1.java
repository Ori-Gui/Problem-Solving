import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int K = 0;
		
		if (N % 2 == 0 && M % 2 == 0) {
			K = Math.min(N, M);
		} else if (N % 2 == 0) {
			K = N;
		} else {
			K = M;
		}
		
		
		int curR = R;
		int l = 1;
		
		for (int k = 0; k < K / 2; k++) {
			l = ((N-(2*k+1))+(M-(2*k+1)))*2;
			curR = R % l;
			for (int r = 0; r < curR; r++) {
				int temp2 = arr[N-1-k][k];
				int temp3 = arr[N-1-k][M-1-k];
				int temp4 = arr[k][M-1-k];
				
				for (int i = N-1-k; i > k; i--) {
					arr[i][k] = arr[i-1][k];
				}
				for (int i = M-1-k; i > k ; i--) {
					if (i == k+1) {
						arr[N-1-k][i] = temp2;
					} else {
						arr[N-1-k][i] = arr[N-1-k][i-1];
					}
				}
				for (int i = k; i < N-1-k; i++) {
					if (i == N-2-k) {
						arr[i][M-1-k] = temp3;
					} else {
						arr[i][M-1-k] = arr[i+1][M-1-k];
					}
				}
				for (int i = k; i < M-1-k; i++) {
					if (i == M-2-k) {
						arr[k][i] = temp4;
					} else {
						arr[k][i] = arr[k][i+1];
					}
				}
			}
		}
		
		for (int[] ar : arr) {
			for (int a : ar) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}
}
