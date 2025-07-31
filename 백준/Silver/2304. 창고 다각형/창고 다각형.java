import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Storage> storages = new ArrayList<>();

    static class Storage {
        int l, h;

        Storage(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public String toString() {
            return "[" + l + ", " + h + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            storages.add(new Storage(l, h));
        }

        Collections.sort(storages, Comparator.comparingInt(s -> s.l));

        int maxIdx = 0;
        int maxH = 0;
        for (int i = 0; i < N; i++) {
            if (storages.get(i).h > maxH) {
                maxH = storages.get(i).h;
                maxIdx = i;
            }
        }

        int area = 0;
        int left = storages.get(0).h;
        int right = storages.get(N - 1).h;

        for (int i = 0; i < maxIdx; i++) {
            int w = storages.get(i + 1).l - storages.get(i).l;
            area += left * w;
            if (storages.get(i + 1).h > left) {
                left = storages.get(i + 1).h;
            }
        }
        
        for (int i = N - 1; i > maxIdx; i--) {
            int w = storages.get(i).l - storages.get(i - 1).l;
            area += right * w;
            if (storages.get(i - 1).h > right) {
                right = storages.get(i - 1).h;
            }
        }
        area += maxH;

        System.out.println(area);
    }
}
