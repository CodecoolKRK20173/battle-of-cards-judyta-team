import java.security.Principal;
import java.util.*;

public class Game {
    private ArrayList<Player> players;
    private Pile deck;
    private Int cashPool;

    public Game(Player... players) {
        players = new ArrayList<>(players);
        cashPool = 0;
        deck = createDeck();
        shuffleDeck();
        dealCards();
    }

    // private int getBet() {
    //     System.out.println("Bet my friend! ");
    //     Scanner scanner = new Scanner(System.in);
    //     int bet = 0;
    //     try {
    //         bet = scanner.nextInt();
    //     } catch (Exception e) {
    //         System.out.println("This isn't a number!");
    //     }
    //     return bet;
    // }

    private ArrayList<Card> createDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        for (int i=0; i<4; i++){
            for (int j=2; j<15; j++){
                deck.add(new Card(i, j));
            }
        }
        return deck;
    }

    private void newRound(){
        this.cashPool = 0;
        clearTable();
        dealCards();
    }

    private void dealCards(){
        
        for (int i=1; i=players.length(); i++){
            players.get(i).getHand().add(deck.getTopCard());
            deck.remove(0);
            if (!players.length()==i && !players.get(i).getHand().length()==1){
                players.get(i).getHand().get(getHand().length()).flipCard();
            }
            else {
            }
        }
    }

    private void shuffleDeck(){
        Collections.shuffle(deck.getAllCards());
    }

    private void checkHighestScore(){
        ArrayList<Integer> scoreTable;
        for (Player player : players){
            if (player.getBust()==false){
                scoreTable.add(player.getScore());
            }
        }
        Collections.sort(scoreTable);
        int highestScore = scoreTable.get(0);
        for (Player player : players){
            if (player.getScore()==highestScore){
                player.setWinner(true);
            }
        }
    }

    public Pile getDeck(){
        return this.deck;
    }

    public void setDeck(Pile pile){
        this.deck = pile;
    }

    private void giveCoolcoinsTowWinner(){
        for(Player player : players){
            if(player.getWinner()==true){
                player.setCoolcoin(player.getCoolcoin()+cashPool);
            }
        }
    }
}