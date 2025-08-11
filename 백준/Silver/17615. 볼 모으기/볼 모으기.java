import java.io.*;

public class Main {
    static int N;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        s = br.readLine();
        char[] a = s.toCharArray();

        int totalR = 0;
        int totalB = 0;
        for (char c : a) {
            if (c == 'R') {
                totalR += 1;
            } else { 
                totalB += 1; 
            }
        }

        if (totalR == 0 || totalB == 0) {
            System.out.println(0);
            return;
        }

        int leftR = 0;
        int leftB = 0;
        int i = 0;

        while (i < N && a[i] == 'R') {
            leftR += 1; 
            i += 1; 
        }

        i = 0;
        while (i < N && a[i] == 'B') { 
            leftB += 1;
            i += 1; 
        }

        int rightR = 0;
        int rightB = 0;

        i = N - 1;
        while (i >= 0 && a[i] == 'R') { 
            rightR += 1;
            i -= 1; 
        
        }

        i = N - 1;
        while (i >= 0 && a[i] == 'B') { 
            rightB += 1;
            i -= 1; 
        }

        int moveR = Math.min(totalR - leftR, totalR - rightR);
        int moveB = Math.min(totalB - leftB, totalB - rightB);

        System.out.println(Math.min(moveR, moveB));
    }
}
