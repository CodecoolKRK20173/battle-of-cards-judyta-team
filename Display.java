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

        StringBuilder table = new StringBuilder();

        int tableWidth = this.getTableWidth();

        table.append(this.putJudytaName())
             .append(this.putJudytaCoins())
             .append(this.putJudytaCards())
             .append(this.putJudytaScore());

        System.out.println(table.toString());

    }
}