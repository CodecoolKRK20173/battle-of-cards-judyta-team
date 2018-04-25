import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class PlayerIterator implements Iterator<Player> {
    private int index;
    private List<Player> players;
    private int maxIndex;

    public PlayerIterator(ArrayList<Player> players) {
        this.setIndex(0);
        this.setPlayers(players);
        this.setMaxIndex(players.size());
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public int getMaxIndex() {
        return this.maxIndex;
    }

    public boolean hasNext() {
        int nextIndex = this.getIndex();
        int maxIndex = getMaxIndex();

        if (nextIndex == maxIndex) {
            setIndex(0);
            return false;
        } else {
            return true;
        }
    }

    public Player next() {
        int nextIndex = this.getIndex();
        Player player = this.players.get(nextIndex);
        nextIndex ++;
        this.setIndex(nextIndex);
        return player;
    }

}