import java.util.*;

class Solution {
    static int idx = 0;
    static boolean flag = false;
    
    class Word {
        String str;
        int cnt;
        
        Word(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int l = words.length;
        for (int i = 0; i < l; i++) {
            if (words[i].equals(target)) {
                flag = true;
                idx = i;
                break;
            }
        }
        
        if (!flag) return 0;
        
        Queue<Word> q = new LinkedList<>();
        boolean v[] = new boolean[l];
        
        q.offer(new Word(begin, 0));
        
        while (!q.isEmpty()) {
            Word cur = q.poll();
            
            if (cur.str.equals(target)) {
                return cur.cnt;
            }
            
            char[] curArr = cur.str.toCharArray();

            for (int i = 0; i < l; i++) {
                if (v[i]) continue;
                
                int strCnt = 0;
                char[] nextArr = words[i].toCharArray();

                
                for (int j = 0; j < curArr.length; j++) {
                    if (curArr[j] != nextArr[j]) {
                        strCnt += 1;
                    }
                }
                
                if (strCnt == 1) {
                    v[i] = true;
                    q.offer(new Word(words[i], cur.cnt + 1));
                }   
            }
        }
        return 0;
    }
}