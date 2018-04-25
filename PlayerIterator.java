import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class PlayerIterator implements Iterator<Player> {
    private int index;
    private List<Player> players;
   // private int maxIndex;

    public PlayerIterator(ArrayList<Player> players) {
        this.setIndex(0);
        this.setPlayers(players);
        //this.setMaxIndex(players.size());
    }

    private void setIndex(int index) {
        this.index = index;
    }

    private int getIndex() {
        return this.index;
    }

    private void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean hasNext() {
        return index < players.size();
    }

    public Player next() {
        int nextIndex = this.getIndex();
        Player player = this.players.get(nextIndex);
        nextIndex ++;
        this.setIndex(nextIndex);
        return player;
    }
}