import java.io.*;
import java.util.*;

public class Main {
    // 1년 1월 1일 ~ (year, month, day)까지의 총 일수 계산
    static long toDays(int year, int month, int day) {
        long y = year - 1L;
        long days = y * 365L + (y / 4) - (y / 100) + (y / 400);
        int[] mdays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 1; i < month; i++) {
            days += mdays[i - 1];
        }
        if (month > 2 && isLeap(year)) days += 1;
        days += day;
        return days;
    }
    
    // 윤년
    static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    // 천년
    static boolean check1000(int ty, int tm, int td, int dy, int dm, int dd) {
        // 연도 비교
        if (dy > ty + 1000) return true;
        if (dy < ty + 1000) return false;
        // 월 비교
        if (dm > tm) return true;
        if (dm < tm) return false;
        // 일 비교
        return dd >= td;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int ty = Integer.parseInt(st.nextToken());
        int tm = Integer.parseInt(st.nextToken());
        int td = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int dy = Integer.parseInt(st.nextToken());
        int dm = Integer.parseInt(st.nextToken());
        int dd = Integer.parseInt(st.nextToken());
        
        if (check1000(ty, tm, td, dy, dm, dd)) {
            System.out.println("gg");
        } else {
            long start = toDays(ty, tm, td);
            long target = toDays(dy, dm, dd);
            long diff = target - start;
            
            System.out.println("D-" + diff);
        }
    }
}
