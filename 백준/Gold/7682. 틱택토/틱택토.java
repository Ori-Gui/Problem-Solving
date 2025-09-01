import java.io.*;

public class Main {
    static int[][] lines = {
        {0,1,2}, {3,4,5}, {6,7,8},
        {0,3,6}, {1,4,7}, {2,5,8},
        {0,4,8}, {2,4,6}
    };

    static boolean isWin(boolean[] b) {
        for (int[] l : lines) {
            if (b[l[0]] && b[l[1]] && b[l[2]]) return true;
        }
        return false;
    }

    static boolean game(char[] board) {
        boolean[] xt = new boolean[9];
        boolean[] ot = new boolean[9];
        int xCnt = 0;
        int oCnt = 0;

        for (int i = 0; i < 9; i++) {
            if (board[i] == 'X') { 
                xt[i] = true; 
                xCnt += 1;
            } else if (board[i] == 'O') { 
                ot[i] = true;
                oCnt += 1;
            }
        }

        if (!(xCnt == oCnt || xCnt == oCnt + 1)) return false;

        boolean xWin = isWin(xt);
        boolean oWin = isWin(ot);

        if (xWin && oWin) return false;
        if (xWin) return xCnt == oCnt + 1;
        if (oWin) return xCnt == oCnt;

        return xCnt == 5 && oCnt == 4;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;

            char[] ttt = str.toCharArray();
            System.out.println(game(ttt) ? "valid" : "invalid");
        }
    }
}
