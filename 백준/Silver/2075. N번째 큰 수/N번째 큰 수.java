import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }
        Collections.sort(list, Comparator.reverseOrder());
        System.out.print(list.get(N - 1));
    }
}
