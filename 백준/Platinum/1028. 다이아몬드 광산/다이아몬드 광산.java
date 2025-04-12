import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static int[][] dr; // 아래-오른쪽(DR)
    static int[][] dl; // 아래-왼쪽(DL)
    static int[][] ur; // 위-오른쪽(UR)
    static int[][] ul; // 위-왼쪽(UL)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = line[j] - '0';
            }
        }

        dr = new int[R][C]; // 아래-오른쪽
        dl = new int[R][C]; // 아래-왼쪽
        ur = new int[R][C]; // 위-오른쪽
        ul = new int[R][C]; // 위-왼쪽

        // DR: 오른쪽 아래 방향
        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                if (map[i][j] == 1) {
                    if (i + 1 < R && j + 1 < C) {
                        dr[i][j] = 1 + dr[i + 1][j + 1];
                    } else {
                        dr[i][j] = 1;
                    }
                } else {
                    dr[i][j] = 0;
                }
            }
        }
        
        // DL: 아래 왼쪽 방향
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    if (i + 1 < R && j - 1 >= 0) {
                        dl[i][j] = 1 + dl[i + 1][j - 1];
                    } else {
                        dl[i][j] = 1;
                    }
                } else {
                    dl[i][j] = 0;
                }
            }
        }
        
        // UR: 위 오른쪽 방향
        for (int i = 0; i < R; i++) {
            for (int j = C - 1; j >= 0; j--) {
                if (map[i][j] == 1) {
                    if (i - 1 >= 0 && j + 1 < C) {
                        ur[i][j] = 1 + ur[i - 1][j + 1];
                    } else {
                        ur[i][j] = 1;
                    }
                } else {
                    ur[i][j] = 0;
                }
            }
        }
        
        // UL: 위 왼쪽 방향
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        ul[i][j] = 1 + ul[i - 1][j - 1];
                    } else {
                        ul[i][j] = 1;
                    }
                } else {
                    ul[i][j] = 0;
                }
            }
        }
        
        int ans = 0;
        // 모든 칸을 위 꼭짓점 후보로 검사
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    // 위 꼭짓점에서 만들 수 있는 최대 크기는 아래 두 방향에서의 연속 1의 최소값
                    int possible = Math.min(dr[i][j], dl[i][j]);
                    // 큰 후보만 검사
                    for (int k = possible; k > ans; k--) {
                        int bottom = i + 2 * (k - 1); // 아래쪽 꼭짓점의 행 번호
                        if (bottom >= R) continue;   // 범위 벗어나면 무시
                        // 아래쪽 꼭짓점에서 ur, ul 방향의 연속 길이 확인
                        if (ur[bottom][j] >= k && ul[bottom][j] >= k) {
                            ans = k;
                            break; // 이 위 꼭짓점에서는 더 큰 k는 나올 수 없으므로 break
                        }
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}
