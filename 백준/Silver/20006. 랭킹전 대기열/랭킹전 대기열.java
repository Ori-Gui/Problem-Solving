import java.io.*;
import java.util.*;

public class Main {
    static int p, m;
    static List<Room> rooms = new ArrayList<>();

    static class Player {
        int lv;
        String name;

        Player(int lv, String name) {
            this.lv = lv;
            this.name = name;
        }
    }

    static class Room {
        int minLv; 
        int maxLv;
        List<Player> players = new ArrayList<>();

        Room(int lv) {
            this.minLv = lv - 10;
            this.maxLv = lv + 10;
        }

        boolean canEnter(int lv) {
            return lv >= minLv && lv <= maxLv && players.size() < m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int lv = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            boolean joined = false;
            for (Room room : rooms) {
                if (room.canEnter(lv)) {
                    room.players.add(new Player(lv, name));
                    joined = true;
                    break;
                }
            }
            if (!joined) {
                Room newRoom = new Room(lv);
                newRoom.players.add(new Player(lv, name));
                rooms.add(newRoom);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Room room : rooms) {
            if (room.players.size() == m) {
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }
            room.players.sort(Comparator.comparing(p -> p.name));
            for (Player pl : room.players) {
                sb.append(pl.lv).append(" ").append(pl.name).append("\n");
            }
        }

        System.out.print(sb);
    }
}
