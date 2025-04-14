import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        StringBuilder answer = new StringBuilder();
        StringBuilder word = new StringBuilder();

        boolean inTag = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '<') {
                answer.append(word.reverse());
                word.setLength(0);
                inTag = true;
                answer.append(c);
            } else if (c == '>') {
                inTag = false;
                answer.append(c);
            } else if (inTag) {
                answer.append(c);
            } else {
                if (c == ' ') {
                    answer.append(word.reverse());
                    word.setLength(0);
                    answer.append(' ');
                } else {
                    word.append(c);
                }
            }
        }
        answer.append(word.reverse());
        System.out.println(answer);
    }
}
