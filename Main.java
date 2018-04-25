import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();

        Player p1 = new Human("Michal");
        Player p2 = new Human("Wojtek");

        players.add(p1);
        players.add(p2);

        Game game = new Game(players);
        game.lanuch();

    }
}