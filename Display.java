import java.util.ArrayList;

public class Display {

    private ArrayList<Player> players;

    public Display(ArrayList<Player> players) {
        this.players = players;
    }

    public void tablee() {
        PlayerIterator iterator = new PlayerIterator(players);

        StringBuilder playerItems = new StringBuilder();

        while (iterator.hasNext()) {
            
            Player p = iterator.next();

            playerItems.append("Name: " + p.getName())
                       .append(" Coins: " + p.getCoolcoin())
                       .append(" Cards: " + p.getHand())
                       .append("\n");

        }

        System.out.println(playerItems);
    }

    public void table() {
        
    }
}