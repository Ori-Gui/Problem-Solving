import java.io.*;
import java.util.*;

public class Main {
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (a % 10 == 0) {
				System.out.println(10);
				continue;
			}
			
			List<Integer> list = new ArrayList<>();
			int ans = 0;
			int temp = a % 10;
			list.add(temp);
			for (int i = 1; i < b; i++) {
				temp = (temp * a) % 10;
				if (temp == list.get(0)) break;
				list.add(temp);
			}
			System.out.println(list.get((b-1) % list.size()));
		}
	}
}
