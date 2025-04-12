import java.util.*;

public class Main {
	static int N, M;
	static int arr[];
	static int maxSum;

	static void dfs(int idx, int start, int[] sel) {
		if (idx == 3) {
			int sum = 0;
			for (int i = 0; i < sel.length; i++) {
				sum += sel[i];
			}
			if (sum <= M) {
				maxSum = Math.max(maxSum, sum);
			}
			return;
		}

		for (int i = start; i < arr.length; i++) {
			sel[idx] = arr[i];
			dfs(idx + 1, i + 1, sel);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		maxSum = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		dfs(0, 0, new int[3]);

		System.out.println(maxSum);
	}
}
