import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		StringBuilder answer = new StringBuilder();

		int[] cnts = new int[4];

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '1') {
				cnts[0] += 1;
			} else if (input.charAt(i) == 'I') {
				cnts[1] += 1;
			} else if (input.charAt(i) == 'l') {
				cnts[2] += 1;
			} else if (input.charAt(i) == '|') {
				cnts[3] += 1;
			}
		}

		for (int j = 0; j < 4; j++) {
			answer.append(cnts[j]).append('\n');
		}
		
		System.out.println(answer);
	}
}
