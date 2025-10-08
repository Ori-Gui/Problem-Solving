import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] H;
    static int[] lc, rc, ln, rn;

    static class Node {
        int idx, h;
        Node(int i, int h) { 
            this.idx = i;
            this.h = h; 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        lc = new int[N + 1];
        rc = new int[N + 1];
        ln = new int[N + 1];
        rn= new int[N + 1];
        Arrays.fill(ln, -1);
        Arrays.fill(rn, -1);

        Deque<Node> stack = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek().h <= H[i]) {
                stack.pop();
            }
            lc[i] = stack.size();
            if (!stack.isEmpty()) {
                ln[i] = stack.peek().idx;
            }
            stack.push(new Node(i, H[i]));
        }

        stack.clear();
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek().h <= H[i]) { 
                stack.pop();
            }
            rc[i] = stack.size();
            if (!stack.isEmpty()) {
                rn[i] = stack.peek().idx;
            }
            stack.push(new Node(i, H[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int total = lc[i] + rc[i];
            if (total == 0) {
                sb.append("0\n");
            } else {
                int bestIdx = -1;
                int bestDist = Integer.MAX_VALUE;

                if (ln[i] != -1) {
                    bestIdx = ln[i];
                    bestDist = i - ln[i];
                }
                if (rn[i] != -1) {
                    int d = rn[i] - i;
                    if (bestIdx == -1 || d < bestDist || (d == bestDist && rn[i] < bestIdx)) {
                        bestIdx = rn[i];
                        bestDist = d;
                    }
                }
                sb.append(total).append(' ').append(bestIdx).append('\n');
            }
        }
        System.out.print(sb.toString());
    }
}
