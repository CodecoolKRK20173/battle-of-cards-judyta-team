import java.util.ArrayList;

public class Display {

    private ArrayList<Player> players;

    public Display(ArrayList<Player> players) {
        this.players = players;
    }

    public void table() {
        PlayerIterator iterator = new PlayerIterator(players);

        while (iterator.hasNext()) {
            
            Player p = iterator.next();
            StringBuilder playerItems = new StringBuilder();

            playerItems.append("Name: " + p.getName())
                       .append(" Coins: " + p.getCoolcoin())
                       .append(" Cards: " + p.getHand());

        }
    }
}