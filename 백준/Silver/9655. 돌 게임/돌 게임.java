import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        if (N % 2 == 0) {
            sb.append("CY");
        } else {
            sb.append("SK");
        }
        System.out.println(sb.toString());
    }
}
