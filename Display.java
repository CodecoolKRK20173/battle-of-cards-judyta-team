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

    public void table() {

        StringBuilder table = new StringBuilder();

        table.append(this.putJudytaName() + "\n")
             .append(this.putJudytaCoins() + "\n")
             .append(this.putJudytaCards() + "\n")
             .append(this.putJudytaScore() + "\n\n")
             .append(this.putTotalBet() + "\n\n");

        System.out.println(table.toString());

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

}