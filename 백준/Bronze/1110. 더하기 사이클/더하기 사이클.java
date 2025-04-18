import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        N = N < 10 ? N * 10 : N;
        int cnt = 0;
        int num = N;
        
        do {
            int a = num / 10;
        	int b = num % 10;
        	num = (a + b) % 10 + (b * 10);
        	cnt += 1;
        } while(num != N);
        
        System.out.println(cnt);
    }
}
