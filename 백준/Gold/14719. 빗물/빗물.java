import java.io.*;
import java.util.*;

public class Main {
    static int H, W;
    static int[] world;
    static int totalWater;
    static PriorityQueue<Block> pq;

    static class Block {
        int i;
        int h;

        Block(int i, int h) {
            this.i = i;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        world = new int[W];
        totalWater = 0;
        pq = new PriorityQueue<>(Comparator.comparingInt((Block b) -> b.h).reversed());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            world[i] = Integer.parseInt(st.nextToken());
            pq.offer(new Block(i, world[i]));
        }
        
        while (pq.size() > 1) {
            Block b1 = pq.poll();
            Block b2 = pq.peek();

            if (b1.i > b2.i) {
                for (int i = b2.i + 1; i < b1.i; i++) {
                    int water = b2.h - world[i];
                    totalWater += water > 0 ? water : 0;
                    world[i] = b2.h; 
                }
            } else {
                for (int i = b2.i - 1; i > b1.i; i--) {
                    int water = b2.h - world[i];
                    totalWater += water > 0 ? water : 0;
                    world[i] = b2.h; 
                }
            }
        }
        System.out.println(totalWater);
    }
}
