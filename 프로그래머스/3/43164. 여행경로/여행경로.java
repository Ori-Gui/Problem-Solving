import java.util.*;

class Solution {
    static List<String[]> answers = new ArrayList<>();
    
    public void dfs(String start, int idx, List<String> sel, String[][] tickets, boolean[] v) { 
        if (idx == tickets.length) {
            answers.add(sel.toArray(new String[0]));
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && !v[i]) {
                String next = tickets[i][1];
                sel.add(next);
                v[i] = true;
                dfs(next, idx + 1, sel, tickets, v);
                v[i] = false;
                sel.remove(sel.size() - 1);
            } 
        }
    }
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) { 
                return a[1].compareTo(b[1]); 
            }
            return a[0].compareTo(b[0]);
        });
        
        List<String> sel = new ArrayList<>();
        sel.add("ICN");

        
        dfs("ICN", 0, sel, tickets, new boolean[tickets.length]);
        
        // for (String[] arr : answers) {
        //     System.out.println(Arrays.toString(arr));
        // } 
        
        return answers.get(0);
    }
}