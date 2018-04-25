import java.util.ArrayList;
import java.util.HashMap;

public class Display {

    private final int MAX_DEFAULT_WIDTH = 10;
    
    private int judytaIndex;
    private int tableWidth;
    private ArrayList<Player> players;
    // private HashMap<ArrayList<Player>, Integer> playersPrintWidth;

    public Display(ArrayList<Player> players) {

        this.players = players;
        this.tableWidth = this.getTableWidth();
        this.judytaIndex = players.indexOf(players.get(players.size() - 1));

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

        table.append(this.formatted(players.get(judytaIndex).getName()))    // Name
             .append(this.formatted("Coins: " + players.get(judytaIndex).getCoolcoin()))    // Coins
             .append(this.formatted(players.get(judytaIndex).getHand().toString())) // Cards
             .append(this.formatted("Score: " + players.get(judytaIndex).getScore()))   // Score
             .append(this.formatted("Total bet: " + totalBet) + "\n\n\n");   // Total bet


        table.append(this.playersData());

        System.out.println(table.toString());

    }

    private String playersData() {

        StringBuilder playersData = new StringBuilder();
        
        Player judyta = players.get(judytaIndex);
        ArrayList<Player> humanPlayers = new ArrayList<>(players);
        humanPlayers.remove(judyta);

        for (int i = 0; i < humanPlayers.size() ; i++) {

            PlayerIterator iterator = new PlayerIterator(humanPlayers);

            while (iterator.hasNext()) {
                Player player = iterator.next();
            }

        }

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