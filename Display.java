import java.util.ArrayList;

public class Display {

    private final int MAX_DEFAULT_WIDTH = 10;
    
    private int tableWidth;
    private ArrayList<Player> players;

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

        table.append(this.putJudytaName())
             .append(this.putJudytaCoins())
             .append(this.putJudytaCards())
             .append(this.putJudytaScore());

        System.out.println(table.toString());

    }

    private String putJudytaName() {
        return "";
    }

}