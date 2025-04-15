import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N, M, K;
    static Cell[][] map;
    static List<Cell> cells;
    // 상, 우, 하, 좌
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static class Cell implements Comparable<Cell> {
        int r;
        int c;
        int x; // 생명력 수치
        int hp; // 죽기까지 남은 체력, 체력 절반 시 활성화 (2*x)
        int stat; // 0 : 죽은 상태, 1 : 비활성 상태, 2 : 활성 상태
        
        Cell(int r, int c, int x, int hp, int stat) {
            this.r = r;
            this.c = c;
            this.x = x;
            this.hp = hp;
            this.stat = stat; 
        }

        @Override
        public int compareTo(Cell o) {
            return Integer.compare(o.x, this.x);
        }
    }
    
    static void culture(Cell cell, List<Cell> newCells) {
        // 0 죽은 상태
        if (cell.stat == 0) return;

        cell.hp -= 1; // 체력 감소
        
        // 1 비활성 상태 & 체력 절반 => 활성 상태
        if (cell.stat == 1 && cell.hp == cell.x) {
            cell.stat = 2;
        } 

        // 2 활성 상태 & 체력 절반 - 1 => 번식
        if (cell.stat == 2 && cell.hp == cell.x - 1) {
            for (int i = 0; i < 4; i++) {
                int nr = cell.r + dr[i];
                int nc = cell.c + dc[i];    
                Cell newCell = new Cell(nr, nc, cell.x, 2*cell.x, 1);
                if (map[nr][nc] == null) {
                    map[nr][nc] = newCell;
                    newCells.add(newCell);
                }
            }
        } 

        // 2 활성 상태 & 체력 0 => 죽은 상태
        if (cell.stat == 2 && cell.hp == 0) {
            cell.stat = 0;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new Cell[2*K + N][2*K + M];
            // 생명력 수치 기준 세포 내림차순 정렬
            cells = new ArrayList<>();
    
            for (int i = K; i < K + N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = K; j < K + M; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 0) continue;
                    Cell cell = new Cell(i, j, x, 2*x, 1);
                    map[i][j] = cell;
                    cells.add(cell);
                }
            }
            
            for (int k = 0; k < K; k++) {
                Collections.sort(cells);
                List<Cell> newCells = new ArrayList<>();
                for (Cell cell : cells) {
                    culture(cell, newCells);
                }
                cells.addAll(newCells);
                
                Iterator<Cell> iter = cells.iterator();
                while(iter.hasNext()) {
                    if (iter.next().stat == 0) {
                        iter.remove();
                    }
                }
            }         
            System.out.printf("#%d %d\n", tc, cells.size());
        }
    }
}