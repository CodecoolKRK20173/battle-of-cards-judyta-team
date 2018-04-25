import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();

        Player p1 = new Human("Michal");
        Player p2 = new Human("Wojtek");

        Player judyta = new JudytaBot(200);

        players.add(p1);
        players.add(p2);
        players.add(judyta);

        Game game = new Game(players);
        game.lanuch();
        game.clearTable();

    }
}