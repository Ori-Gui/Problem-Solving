import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			int n = sc.nextInt();
			int cnt = 0;
			int[][] planets = new int[n][3];
			
			for (int i = 0; i < n; i++) {
				planets[i][0] = sc.nextInt();
				planets[i][1] = sc.nextInt();
				planets[i][2] = sc.nextInt();		
			}
			
			for (int[] planet : planets) {
				boolean flag1 = false;
				boolean flag2 = false;
				
				if (Math.pow(planet[2], 2) > Math.pow(planet[0]-x1, 2) + Math.pow(planet[1]-y1, 2)) {
					flag1 = true;
					cnt += 1;
				}
				
				if (Math.pow(planet[2], 2) > Math.pow(planet[0]-x2, 2) + Math.pow(planet[1]-y2, 2)) {
					flag2 = true;
					cnt += 1;
				}
				
				if (flag1 && flag2) {
					cnt -= 2;
				}
			}
			System.out.println(cnt);
		}
	}
}