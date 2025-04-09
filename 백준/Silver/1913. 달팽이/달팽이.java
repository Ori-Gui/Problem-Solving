import java.io.*;

public class Main {
	static int N;
	static int find;
	static int[][] snail;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		find = Integer.parseInt(br.readLine());
		snail = new int[N][N];

		int x = N / 2;
		int y = N / 2;
		int num = 1;
		int k = 0;

		snail[x][y] = 1;
		num++;

		loop: while (num <= N * N) {
			k++;
			for (int j = 0; j < k; j++) {
				x--;
				if (x >= 0) {
					snail[x][y] = num++;
					if (num == N * N + 1)
						break loop;
				}
			}
			for (int j = 0; j < k; j++) {
				y++;
				if (y < N) {
					snail[x][y] = num++;
				}
			}

			k++;
			for (int j = 0; j < k; j++) {
				x++;
				if (x < N) {
					snail[x][y] = num++;
				}
			}
			for (int j = 0; j < k; j++) {
				y--;
				if (y >= 0) {
					snail[x][y] = num++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();

		for (int[] sn : snail) {
			for (int s : sn) {
				sb.append(s).append(" ");
			}
			sb.append("\n");
		}

		loop2: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (snail[i][j] == find) {
					sb.append((i + 1) + " " + (j + 1));
					break loop2;
				}
			}
		}
		
		System.out.println(sb);

	}
}
