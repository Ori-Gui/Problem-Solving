import java.io.*;

public class Main {
    static String str;
    static StringBuilder maxStr;
    static int minN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        maxStr = new StringBuilder();

        // 이어 붙인 수 -> 지우고 남은 수 최대 3000자리 [1 ~ 30000] 
        for (int i = 1; i <= 30000; i++) {
            maxStr.append(i);
        }

        int j = -1;
        for (int i = 0; i < str.length(); i++) {
            while (j < maxStr.length() - 1) {
                j += 1;
                if (str.charAt(i) == maxStr.charAt(j)) {
                    break;
                }
            }
        }

        /* 0 개수
         * 1~9 : 0개 (1~0을 1의 자리에 놓으면 -> 9)
         * 10~99 : 9개 (1~0을 1의 자리에 놓으면 -> 9 * 10 + 90 = 180) 
         * 100~999 : 180개 (1~0을 1의 자리에 놓으면 180 * 10 + 900 = 2700) 
         * 1000~9999 : 2700개 (1~0을 1의 자리에 놓으면 2700 * 10 + 9000 = 36000) 
         * 10000~99999 : 36000개 (3000자리 까지만 가능 하므로 N의 최대값 아마 10047인데 틀림 -> 30000정도 되는듯)
         */

        if (j < 9) {
            j += 1;
        } else if (j < 189) {
            j -= 9;
            j /= 2;
            j += 10;
        } else if (j < 2889) {
            j -= 189;
            j /= 3;
            j += 100;
        } else if (j < 38889) {
            j -= 2889;
            j /= 4;
            j += 1000;
        } else {
            j -= 38889;
            j /= 5;
            j += 10000;
        }
        System.out.println(j);
    }    
}
