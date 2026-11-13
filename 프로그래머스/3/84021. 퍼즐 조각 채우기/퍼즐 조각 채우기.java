import java.util.*;

class Solution {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] game_board, int[][] table) {
        // 1. game_board의 빈칸(0) 추출 및 table의 퍼즐 조각(1) 추출
        List<List<int[]>> emptySpaces = extractShapes(game_board, 0);
        List<List<int[]>> puzzlePieces = extractShapes(table, 1);

        boolean[] used = new boolean[puzzlePieces.size()];
        int totalFilled = 0;

        // 2. 빈칸 하나씩 순회하며 맞는 퍼즐 조각 찾기
        for (List<int[]> space : emptySpaces) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                // 이미 사용한 조각이거나 칸 개수가 다르면 패스 (최적화)
                if (used[i] || space.size() != puzzlePieces.get(i).size()) {
                    continue;
                }

                // 3. 조각을 90도씩 4번 회전하며 비교
                if (canMatch(space, puzzlePieces.get(i))) {
                    used[i] = true;
                    totalFilled += space.size();
                    break; // 현재 빈칸을 채웠으므로 다음 빈칸으로 이동
                }
            }
        }

        return totalFilled;
    }
    
    // BFS로 연결된 도형(0 또는 1)들을 추출하고 정규화하여 반환
    private List<List<int[]>> extractShapes(int[][] board, int target) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> shapes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == target) {
                    shapes.add(bfs(board, visited, i, j, target));
                }
            }
        }
        return shapes;
    }
    
    private List<int[]> bfs(int[][] board, boolean[][] visited, int startX, int startY, int target) {
        int n = board.length;
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> shape = new ArrayList<>();

        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        shape.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny] && board[nx][ny] == target) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        shape.add(new int[]{nx, ny});
                    }
                }
            }
        }
        normalize(shape);
        return shape;
    }
    
    private void normalize(List<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] p : shape) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }

        for (int[] p : shape) {
            p[0] -= minX;
            p[1] -= minY;
        }

        shape.sort(Comparator.comparingInt((int[] a) -> a[0])
              .thenComparingInt(a -> a[1]));
    }
    
    private boolean canMatch(List<int[]> space, List<int[]> piece) {
        for (int rot = 0; rot < 4; rot++) {
            boolean isSame = true;
            for (int i = 0; i < space.size(); i++) {
                if (space.get(i)[0] != piece.get(i)[0] || space.get(i)[1] != piece.get(i)[1]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) return true;

            // 90도 회전: (x, y) -> (y, -x) 후 다시 정규화
            for (int[] p : piece) {
                int temp = p[0];
                p[0] = p[1];
                p[1] = -temp;
            }
            normalize(piece);
        }
        return false;
    }
}