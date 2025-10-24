import java.io.*;
import java.util.*;

public class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String line = br.readLine();

            Deque<Integer> dq = new ArrayDeque<>();

            if (n > 0) {
                String[] arr = line.substring(1, line.length() - 1).split(",");
                for (String s : arr) dq.addLast(Integer.parseInt(s));
            }

            boolean reversed = false;
            boolean error = false;

            for (int i = 0; i < p.length(); i++) {
                char op = p.charAt(i);
                if (op == 'R') {
                    reversed = !reversed;
                } else {
                    if (dq.isEmpty()) {
                        error = true;
                        break;
                    }
                    if (!reversed) dq.pollFirst();
                    else dq.pollLast();
                }
            }

            if (error) {
                sb.append("error\n");
            } else {
                sb.append('[');
                if (!dq.isEmpty()) {
                    if (!reversed) {
                        Iterator<Integer> it = dq.iterator();
                        sb.append(it.next());
                        while (it.hasNext()) sb.append(',').append(it.next());
                    } else {
                        Iterator<Integer> it = dq.descendingIterator();
                        sb.append(it.next());
                        while (it.hasNext()) sb.append(',').append(it.next());
                    }
                }
                sb.append(']').append('\n');
            }
        }

        System.out.print(sb.toString());
    }
}
