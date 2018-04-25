import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Display {

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

        return (players.size() - 1) * MAX_PLAYER_WIDTH;
    }

    public void table(int totalBet) {

        Player judyta = players.get(judytaIndex);
        StringBuilder table = new StringBuilder();

        table.append(this.formatted(judyta.getName(), tableWidth) + "\n")    // Name
             .append(this.formatted("Coins: " + judyta.getCoolcoin(), tableWidth) + "\n")
             .append(this.formattedCards(tableWidth) + "\n")    // Coins
             .append(this.formatted("Score: " + judyta.getScore(), tableWidth) + "\n")   // Score
             .append(this.formatted("Total bet: " + totalBet, tableWidth) + "\n\n\n");   // Total bet


        table.append(this.playersData());

        System.out.println(table.toString());

    }

    private String playersData() {

        StringBuilder playersData = new StringBuilder();
        ArrayList<Player> humanPlayers = getHumanPlayers();

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> coins = new ArrayList<>();
        ArrayList<Pile> cards = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();

        PlayerIterator iterator = new PlayerIterator(humanPlayers);

        while (iterator.hasNext()) {
            Player player = iterator.next();
            names.add(player.getName());
            coins.add(player.getCoolcoin());
            cards.add(player.getHand());
            scores.add(player.getScore());
        }

        for (String n : names) {
            playersData.append(formatted(n, MAX_PLAYER_WIDTH));
        }

        playersData.append("\n");

        for (Integer c : coins) {
            playersData.append(formatted("Coins: " + c, MAX_PLAYER_WIDTH));
        }

        playersData.append("\n");

        for (Pile c : cards) {
            playersData.append(formatted(c.toString(), MAX_PLAYER_WIDTH));
        }

        playersData.append("\n");

        for (Integer s : scores) {
            playersData.append(formatted("Score: " + s, MAX_PLAYER_WIDTH));
        }

        return playersData.toString();
    }

    private String formatted(String content, int width) {
        
        return String.format("%1$-" + (width / 2)
                            + "s %2$-" + (width / 2)
                            + "s"
                            , " "
                            , content);

    }

    private String formattedCards(int width) {

        Player judyta = players.get(judytaIndex);
        List<Card> cards = judyta.getHand().getAllCards();

        StringBuilder cardsFormat = new StringBuilder();

        cardsFormat.append(String.format("%1$-" + (width / 2) + "s", " ")).append(" ");

        for (Card c : cards) {
            cardsFormat.append(String.format("%1$c", c.getCodePointValue()));
            cardsFormat.append(" ");
        }

        cardsFormat.append(String.format("%1$-" + (MAX_PLAYER_WIDTH - (cards.size() * 2)) + "s", " "));

        return cardsFormat.toString();
    }

    private ArrayList<Player> getHumanPlayers() {

        Player judyta = players.get(judytaIndex);
        ArrayList<Player> humanPlayers = new ArrayList<>(players);
        humanPlayers.remove(judyta);

        return humanPlayers;
    }

}