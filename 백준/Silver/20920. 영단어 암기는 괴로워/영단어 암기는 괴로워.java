import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, Integer> wordCount;
    static List<Word> words;

    static class Word implements Comparable<Word> {
        String word;
        int cnt;

        Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return word + " " + cnt;
        }

        @Override
        public int compareTo(Word o) {
            if (this.cnt == o.cnt) {
                if (this.word.length() == o.word.length()) {
                    return this.word.compareTo(o.word); // 3. 알파벳 사전 순
                }
                return Integer.compare(o.word.length(), this.word.length()); // 2. 단어 길이 내림차순
            }
            return Integer.compare(o.cnt, this.cnt); // 1. 빈도수 내림차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wordCount = new HashMap<>();
        words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine().trim();
            if (word.length() < M) continue;
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            words.add(new Word(word, count));
        }
        
        Collections.sort(words);

        StringBuilder sb = new StringBuilder();
        for (Word w : words) {
            sb.append(w.word).append("\n");
        }

        System.out.print(sb);
    }
}