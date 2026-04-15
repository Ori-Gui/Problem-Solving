import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> score = new HashMap<>();
        char[] personality = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        String ans = "";
        for (int i = 0; i < 8; i ++) {
            score.put(personality[i], 0);
        }

        for (int i = 0; i < survey.length; i ++) {
            char a = survey[i].charAt(0);
            char b = survey[i].charAt(1);
            int c = choices[i];
            if (c < 4) {
                score.put(a, score.get(a) + 4-c);
            } else if (c > 4) {
                score.put(b, score.get(b) + c-4);
            }
        }
        
        if (score.get('R') >= score.get('T')) {
            ans += "R";
        } else {
            ans += "T";
        }
        if (score.get('C') >= score.get('F')) {
            ans += "C";
        } else {
            ans += "F";
        }
        if (score.get('J') >= score.get('M')) {
            ans += "J";
        } else {
            ans += "M";
        }
        if (score.get('A') >= score.get('N')) {
            ans += "A";
        } else {
            ans += "N";
        }
        return ans;
    }
}