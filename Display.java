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

        table.append(this.putFormatted(players.get(JUDYTA_INDEX).getName()))    // Name
             .append(this.putFormatted("Coins: " + players.get(JUDYTA_INDEX).getCoolcoin()))    // Coins
             .append(this.putFormatted(players.get(JUDYTA_INDEX).getHand().toString())) // Cards
             .append(this.putFormatted("Score: " + players.get(JUDYTA_INDEX).getScore()))   // Score
             .append(this.putFormatted("Total bet: " + totalBet) + "\n\n\n");   // Total bet

        System.out.println(table.toString());

    }

    private String putFormatted(String content) {
        
        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , content) + "\n";

    }

    private String putJudytaName() {

        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , players.get(JUDYTA_INDEX).getName());
    }

    private String putJudytaCoins() {

        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , "Coins: " + players.get(JUDYTA_INDEX).getCoolcoin());
    }

    private String putJudytaCards() {
        
        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , players.get(JUDYTA_INDEX).getHand());
    }

    private String putJudytaScore() {
        
        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , "Score: " + players.get(JUDYTA_INDEX).getScore());
    }

    private String putTotalBet(int totalBet) {

        Integer tempWidth = tableWidth / 2;
        return String.format("%1$-" + tempWidth.toString()
                            + "s %2$-" + tempWidth.toString()
                            + "s"
                            , " "
                            , "Total bet: " + totalBet);
    }

}