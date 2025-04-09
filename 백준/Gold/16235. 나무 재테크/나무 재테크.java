import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A, B;
    static int x, y, z;
    static List<Tree> trees;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static int treeCnt;

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;
        boolean isAlive;

        Tree(int x, int y, int age, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N*N 땅
        M = Integer.parseInt(st.nextToken()); // M 그루
        K = Integer.parseInt(st.nextToken()); // K 년 후
        A = new int[N][N]; // 겨울에 추가될 양분
        B = new int[N][N]; // 내 땅 양분


        trees = new LinkedList<>();
        treeCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                B[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()); // 나무 x좌표
            y = Integer.parseInt(st.nextToken()); // 나무 y좌표
            z = Integer.parseInt(st.nextToken()); // 나무 나이 z
            trees.add(new Tree(x-1, y-1, z, true));
        }
        Collections.sort(trees);

        for (int k = 1; k <= K; k++) { // 나이별 나무 (오름차순)

            // 봄
            for (Tree tree : trees) {
                if (B[tree.x][tree.y] >= tree.age && tree.isAlive) {
                    B[tree.x][tree.y] -= tree.age;
                    tree.age += 1;
                } else {
                    tree.isAlive = false;
                }
            }

            // 여름
            Iterator<Tree> iter = trees.iterator();
            while (iter.hasNext()) {
                Tree tree = iter.next();
                if (!tree.isAlive) {
                    B[tree.x][tree.y] += tree.age / 2; // 죽은나무 양분
                    iter.remove();
                }
            }

            // 가을
            List<Tree> newTrees = new LinkedList<>(); 
            for (Tree tree : trees) {
                if (tree.age % 5 == 0 && tree.isAlive) {
                    for (int d = 0; d < 8; d++) {
                        int nx = tree.x + dx[d];
                        int ny = tree.y + dy[d];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        newTrees.add(0, new Tree(nx, ny, 1, true));
                    }
                }
            }
            trees.addAll(0, newTrees);

            // 겨울
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    B[i][j] += A[i][j];
                }
            }
        }
        
        for (Tree tree : trees) {
            if (tree.isAlive) {
                treeCnt += 1;
            }
        }
        System.out.println(treeCnt);
    }
}
