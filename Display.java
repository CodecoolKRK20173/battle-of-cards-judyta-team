import java.util.ArrayList;

public class Display {

    private ArrayList<Player> players;

    public Display(ArrayList<Player> players) {
        this.players = players;
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

    private int getTableWidth() {

        int width = 0;

        return 0;
    }
}