import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;
    static int[][] arrClone;
    static ArrayList<int[]> rotations;
    static ArrayList<int[]> permutations;
    static boolean[] v;
    static int minSum = Integer.MAX_VALUE;

    public static void permutation(int k, int idx, int sel[]) {
        if (idx == k) {
            permutations.add(sel.clone());
            return;
        }
        for (int i = 0; i < k; i++) {
            if (!v[i]) {
                sel[idx] = i;
                v[i] = true;
                permutation(k, idx + 1, sel);
                v[i] = false;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        arrClone = new int[N][M];
        rotations = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            arrClone[i] = arr[i].clone();
        }

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int[] rcs = new int[] { r, c, s };

            rotations.add(rcs);
        }

        permutations = new ArrayList<>();
        v = new boolean[K];

        permutation(K, 0, new int[K]);

        for (int[] p : permutations) {

            for (int k = 0; k < K; k++) {
                int r = rotations.get(p[k])[0] - 1;
                int c = rotations.get(p[k])[1] - 1;
                int S = rotations.get(p[k])[2];

                for (int s = 1; s <= S; s++) {
                    int x1 = r - s;
                    int y1 = c - s;
                    int x2 = r + s;
                    int y2 = c + s;

                    int temp = arr[x1][y1];

                    for (int i = x1; i < x2; i++) {
                        arr[i][y1] = arr[i + 1][y1];
                    }
                    for (int i = y1; i < y2; i++) {
                        arr[x2][i] = arr[x2][i + 1];
                    }
                    for (int i = x2; i > x1; i--) {
                        arr[i][y2] = arr[i - 1][y2];
                    }
                    for (int i = y2; i > y1; i--) {
                        if (i == y1 + 1) {
                            arr[x1][i] = temp;
                        } else {
                            arr[x1][i] = arr[x1][i - 1];
                        }
                    }
                }
            }

            int tempSum;

            for (int i = 0; i < N; i++) {
                tempSum = 0;
                for (int j = 0; j < M; j++) {
                    tempSum += arr[i][j];
                }
                minSum = Math.min(minSum, tempSum);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[i][j] = arrClone[i][j];
                }
            }
        }
        System.out.println(minSum);
    }
}
