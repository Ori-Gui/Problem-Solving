import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();

                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int cnt = 1;

                    // BFS
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int x = cur[0];
                        int y = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && map[nx][ny] == 1) {
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny});
                                cnt++;
                            }
                        }
                    }
                    result.add(cnt);
                }
            }
        }
        Collections.sort(result);

        System.out.println(result.size());
        for (int cnt : result) {
            System.out.println(cnt);
        }
    }
}
