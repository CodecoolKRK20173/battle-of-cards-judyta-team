import java.util.ArrayList;
import java.util.HashMap;

public class Display {

    private final int MAX_DEFAULT_WIDTH = 10;
    private final int MAX_PLAYER_WIDTH = 18;
    
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
        ArrayList<Players> humanPlayers = getHumanPlayers();

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> coins = new ArrayList<>();
        ArrayList<Pile> cards = new ArrayList<>();
        ArrayList<String> scores = new ArrayList<>();

        PlayerIterator iterator = new PlayerIterator(humanPlayers);

        while (iterator.hasNext()) {
            Player player = iterator.next();
            names.add(player.getName());
            coins.add((String) player.getCoolcoin());
            cards.add(player.getHand());
            scores.add((String) player.getScore());
        }

        for (String n : names) {
            playersData.append(formatted(n, MAX_PLAYER_WIDTH));
        }

        playersData.append("\n");

        for (String c : coins) {
            playersData.append(formatted("Coins: " + c, MAX_PLAYER_WIDTH));
        }

        playersData.append("\n");

        for (String c : cards) {
            playersData.append(formatted(c, MAX_PLAYER_WIDTH));
        }

        playersData.append("\n");

        for (String s : scores) {
            playersData.append(formatted("Score: " + s, MAX_PLAYER_WIDTH));
        }

        return playersData.toString();
    }

    private String formatted(String content) {
        
        return String.format("%1$-" + (tableWidth / 2)
                            + "s %2$-" + (tableWidth / 2)
                            + "s"
                            , " "
                            , content) + "\n";

    }

    private ArrayList<Player> getHumanPlayers() {

        Player judyta = players.get(judytaIndex);
        ArrayList<Player> humanPlayers = new ArrayList<>(players);
        humanPlayers.remove(judyta);

        return humanPlayers;
    }

}