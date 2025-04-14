import java.util.Scanner;

public class Main {
    static int N;
    static int[][] home;
    static long[][][] dp;  // dp[r][c][dir]
    
    // 방향 정의
    static final int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        home = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                home[i][j] = sc.nextInt();
            }
        }
        
        // dp 배열 초기화
        dp = new long[N][N][3];
        
        // 처음 상태: (0,0)-(0,1)에 가로로 놓여 있으므로
        // 파이프 끝점은 (0,1), 방향은 가로(HORIZONTAL)
        if (home[0][0] == 0 && home[0][1] == 0) {
            dp[0][1][HORIZONTAL] = 1;
        }
        
        // DP 진행
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 현재 칸이 벽이면 건너뛰기
                if (home[r][c] == 1) continue;
                
                // 1) 가로 방향
                if (dp[r][c][HORIZONTAL] > 0) {
                    // 가로 이동
                    if (c + 1 < N && home[r][c + 1] == 0) {
                        dp[r][c + 1][HORIZONTAL] += dp[r][c][HORIZONTAL];
                    }
                    // 대각선 이동
                    if (r + 1 < N && c + 1 < N
                        && home[r][c + 1] == 0
                        && home[r + 1][c] == 0
                        && home[r + 1][c + 1] == 0) {
                        dp[r + 1][c + 1][DIAGONAL] += dp[r][c][HORIZONTAL];
                    }
                }
                
                // 2) 세로 방향
                if (dp[r][c][VERTICAL] > 0) {
                    // 세로 이동
                    if (r + 1 < N && home[r + 1][c] == 0) {
                        dp[r + 1][c][VERTICAL] += dp[r][c][VERTICAL];
                    }
                    // 대각선 이동
                    if (r + 1 < N && c + 1 < N
                        && home[r][c + 1] == 0
                        && home[r + 1][c] == 0
                        && home[r + 1][c + 1] == 0) {
                        dp[r + 1][c + 1][DIAGONAL] += dp[r][c][VERTICAL];
                    }
                }
                
                // 3) 대각선 방향
                if (dp[r][c][DIAGONAL] > 0) {
                    // 가로 이동
                    if (c + 1 < N && home[r][c + 1] == 0) {
                        dp[r][c + 1][HORIZONTAL] += dp[r][c][DIAGONAL];
                    }
                    // 세로 이동
                    if (r + 1 < N && home[r + 1][c] == 0) {
                        dp[r + 1][c][VERTICAL] += dp[r][c][DIAGONAL];
                    }
                    // 대각선 이동
                    if (r + 1 < N && c + 1 < N
                        && home[r][c + 1] == 0
                        && home[r + 1][c] == 0
                        && home[r + 1][c + 1] == 0) {
                        dp[r + 1][c + 1][DIAGONAL] += dp[r][c][DIAGONAL];
                    }
                }
            }
        }
        
        // (N-1, N-1)에 파이프가 놓일 수 있는 모든 방향의 합
        long answer = dp[N-1][N-1][HORIZONTAL]
                    + dp[N-1][N-1][VERTICAL]
                    + dp[N-1][N-1][DIAGONAL];
        
        System.out.println(answer);
        
        sc.close();
    }
}
