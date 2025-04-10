import java.util.*;

public class Main {
	static int T;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int r1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int r2 = sc.nextInt();
			
			double d = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
			if (d == 0 && r1 == r2) {
				System.out.println(-1);
			} else if (d > r1 + r2 || d < Math.abs(r1 - r2)) {
			    System.out.println(0);
			} else if (d == r1 + r2 || d == Math.abs(r1 - r2)) {
			    System.out.println(1);
			} else {
			    System.out.println(2);
			}
		}
		sc.close();
	}
}
