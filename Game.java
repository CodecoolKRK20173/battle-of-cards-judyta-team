import java.security.Principal;
import java.util.*;

public class Game {
    private ArrayList<Player> players;
    private Pile deck;
    private int cashPool;

    public Game(ArrayList<Player> players) {
        this.players = new ArrayList<>(players);
        cashPool = 0;
        deck = createDeck();
        shuffleDeck();
        dealCards();
    }

    public void lanuch() {
        Display display = new Display(players);
        display.table(cashPool);
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

    private Pile createDeck(){
        Pile deck = new Pile();
        for (int i=0; i<4; i++){
            for (int j=2; j<15; j++){
                deck.addCard(new Card(i, j, deck));
            }
        }
        return deck;
    }

    private void newRound(){
        this.cashPool = 0;
        // clearTable();
        dealCards();
    }

    private void dealCards(){
        //while (playerIterator.hasNext()) {
        //player = playerIterator.next()
        //}
        // for (int i=1; i<players.size(); i++){
        //     players.get(i).getHand().addCard(deck.getTopCard());
        //     deck.removeCard(deck.getTopCard());
        //     if (players.size()!=i && players.get(i).getHand().getAllCards().size()!=1){
        //         players.get(i).getHand().getTopCard().flip();
        //     }
        //     else {
        //     }
        // }
        Iterator<Player> playerIterator = players.iterator();
        playerIterator.forEachRemaining(player -> {
            if(player.equals(players.get(players.size()-1))){
                player.getHand().addCard(deck.getTopCard());
                player.getHand().getTopCard().flip();
                player.getHand().addCard(deck.getTopCard());
            }
            else {
                player.getHand().addCard(deck.getTopCard());
                player.getHand().getTopCard().flip();
                player.getHand().addCard(deck.getTopCard());
                player.getHand().getTopCard().flip();
            }
        });
    }

    private void shuffleDeck(){
        Collections.shuffle(deck.getAllCards());
    }

    private void checkHighestScore(){
        ArrayList<Integer> scoreTable = new ArrayList<>();
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

    public int getCashPool() {
        return cashPool;
    }

    public void setCashPool(int cash) {
        cashPool = cash;
    }
}