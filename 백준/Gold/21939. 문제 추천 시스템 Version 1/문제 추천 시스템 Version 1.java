import java.io.*;
import java.util.*;

public class Main {
    static class Problem {
        int id, lv;
        public Problem(int id, int lv) {
            this.id = id;
            this.lv = lv;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        // 난이도 오름차순, 난이도가 같으면 문제 번호 오름차순 (추천 -1에 사용)
        TreeSet<Problem> easySet = new TreeSet<>(
            Comparator.comparing((Problem p) -> p.lv)
            .thenComparing(p -> p.id));

        // 난이도 내림차순, 난이도가 같으면 문제 번호 내림차순 (추천 1에 사용)
        TreeSet<Problem> hardSet = new TreeSet<>(
            Comparator.comparing((Problem p) -> p.lv).reversed()
            .thenComparing(p -> p.id, Comparator.reverseOrder()));
        
        // 문제 번호를 키로 하고 난이도 정보를 저장
        Map<Integer, Integer> pMap = new HashMap<>();
        
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int lv = Integer.parseInt(st.nextToken());
            Problem cur = new Problem(id, lv);
            easySet.add(cur);
            hardSet.add(cur);
            pMap.put(id, lv);
        }
        
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());
                if (x == 1){
                    Problem p = hardSet.first();
                    sb.append(p.id).append("\n");
                } else if (x == -1){
                    Problem p = easySet.first();
                    sb.append(p.id).append("\n");
                }
            } else if (command.equals("add")){
                int id = Integer.parseInt(st.nextToken());
                int lv = Integer.parseInt(st.nextToken());
                Problem newP = new Problem(id, lv);
                easySet.add(newP);
                hardSet.add(newP);
                pMap.put(id, lv);
            } else if (command.equals("solved")){
                int id = Integer.parseInt(st.nextToken());
                int lv = pMap.get(id);
                Problem rem = new Problem(id, lv);
                easySet.remove(rem);
                hardSet.remove(rem);
                pMap.remove(id);
            }
        }
        
        System.out.print(sb.toString());
    }
}
