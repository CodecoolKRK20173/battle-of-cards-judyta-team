import java.util.ArrayList;
import java.util.HashMap;

public class Display {

    private final int MAX_DEFAULT_WIDTH = 10;
    private final int JUDYTA_INDEX = 0;
    
    private int tableWidth;
    private ArrayList<Player> players;
    // private HashMap<ArrayList<Player>, Integer> playersPrintWidth;

    public Display(ArrayList<Player> players) {
        this.players = players;
        this.tableWidth = this.getTableWidth();
    }

    private int getTableWidth() {

        int width = 0;

        PlayerIterator iterator = new PlayerIterator(players);
        
        while (iterator.hasNext()) {
            
            Player p = iterator.next();
            int playerNameLength = p.getName().length();

            if (playerNameLength <= MAX_DEFAULT_WIDTH) {
                width += MAX_DEFAULT_WIDTH;
            }
            else {
                width += playerNameLength;
            }
        }

        return width;
    }

    public void table(int totalBet) {

        StringBuilder table = new StringBuilder();

        table.append(this.formatted(players.get(JUDYTA_INDEX).getName()))    // Name
             .append(this.formatted("Coins: " + players.get(JUDYTA_INDEX).getCoolcoin()))    // Coins
             .append(this.formatted(players.get(JUDYTA_INDEX).getHand().toString())) // Cards
             .append(this.formatted("Score: " + players.get(JUDYTA_INDEX).getScore()))   // Score
             .append(this.formatted("Total bet: " + totalBet) + "\n\n\n");   // Total bet


        table.append(this.playersData());

        System.out.println(table.toString());

    }

    private String playersData() {

        StringBuilder playersData = new StringBuilder();

        return playersData.toString();
    }

    private String formatted(String content) {
        
        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , content) + "\n";

    }

}