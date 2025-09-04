import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static char[] op = {' ', '+', '-'};
    static List<String[]> anslist = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        anslist.add(new String[] {"1+2-3"});
        anslist.add(new String[] {"1-2-3+4"});
        anslist.add(new String[] {"1 2-3-4-5"});
        anslist.add(new String[] {"1 2+3-4-5-6"});
        anslist.add(new String[] {  
                            "1+2-3+4-5-6+7",
                            "1+2-3-4+5+6-7",
                            "1-2 3+4+5+6+7",
                            "1-2 3-4 5+6 7",
                            "1-2+3+4-5+6-7",
                            "1-2-3-4-5+6+7"
                        });
        anslist.add(new String[] {  
                            "1 2-3 4-5 6+7 8",
                            "1+2 3-4 5+6+7+8",
                            "1+2+3+4-5-6-7+8",
                            "1+2+3-4+5-6+7-8",
                            "1+2-3+4+5+6-7-8",
                            "1+2-3-4-5-6+7+8",
                            "1-2 3-4+5+6+7+8",
                            "1-2+3-4-5+6-7+8",
                            "1-2-3+4+5-6-7+8",
                            "1-2-3+4-5+6+7-8"
                        });
        anslist.add(new String[] {  
            
                            "1 2+3 4-5 6-7+8+9",
                            "1 2+3+4-5-6-7+8-9",
                            "1 2+3-4 5+6+7+8+9",
                            "1 2+3-4+5-6+7-8-9",
                            "1 2-3+4+5 6-7 8+9",
                            "1 2-3+4+5+6-7-8-9",
                            "1 2-3-4-5+6-7-8+9",
                            "1 2-3-4-5-6+7+8-9",
                            "1+2-3 4-5 6+7 8+9",
                            "1-2 3-4-5 6-7+8 9",
                            "1-2-3 4+5+6+7+8+9"
                        });

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            int len = anslist.get(N-3).length;
            for (int i = 0; i < len; i++) {
                sb.append(anslist.get(N-3)[i]).append("\n");
            }
            System.out.println(sb);
        }
    }
}
