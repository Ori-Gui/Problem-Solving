import java.io.*;
import java.util.*;

public class Main {
    static class Country implements Comparable<Country> {
        int n;
        int g;
        int s;
        int b;

        Country(int n, int g, int s, int b) {
            this.n = n;
            this.g = g;
            this.s = s;
            this.b = b;
        }

        @Override
        public int compareTo(Country c) {
            if (this.g > c.g) {
                return -1;
            } else if (this.g == c.g) {
                if (this.s > c.s) {
                    return -1;
                } else if (this.s == c.s) {
                    if (this.b > c.b) {
                        return -1;
                    } else if (this.b == c.b) {
                        return 0;
                    } 
                } 
            }
            return 1;
        }

        @Override
        public String toString() {
            return "[ no." + n + " : " + g + ", " + s + ", " + b + " ]"; 
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<Country> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            Country country = new Country(n, g, s, b);
            list.add(country);
        }
        Collections.sort(list);

        int rank = 0;

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).n == K) {
                rank = j;
                break;
            }
        }

        Country cur = list.get(rank);

        for (int k = rank - 1; k >= 0; k--) {
            if (cur.compareTo(list.get(k)) != 0) {
                break;
            }
            rank -= 1;
        }

        System.out.println(rank + 1);
    }
}
