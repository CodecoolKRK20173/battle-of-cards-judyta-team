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
        launch();
        placingBets();
    }

    public void launch() {
        Display display = new Display(players);
        display.table();
    }

    private void placingBets(){
        Iterator<Player> playerIterator = players.iterator();
        playerIterator.forEachRemaining(player -> {
            if(player.equals(players.get(players.size()-1))){
                cashPool = cashPool*2;
            }
            else{
                int cash = getInput("Give bet! ");
                // player.betCoins(cash);
                cashPool += cash;
                player.setCoolcoin(player.getCoolcoin()-cash);
            }
            System.out.println("Cash Pool: " + cashPool);
        });
    }

    private int getInput(String text) {
        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        int bet = 0;
        try {
            bet = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("This isn't a number!");
        }
        return bet;
    }

    private Pile createDeck(){
        Pile deck = new Pile();
        for (int i=1; i<5; i++){
            for (int j=2; j<15; j++){
                deck.addCard(new Card(i, j, deck));
                // System.out.println("rank = " + i + "suit = " + j);
            }
        }
        return deck;
    }

    private void newRound(){
        this.cashPool = 0;
        // clearTable();
        shuffleDeck();
        dealCards();
        placingBets();
    }

    private void dealCards(){
        Iterator<Player> playerIterator = players.iterator();
        playerIterator.forEachRemaining(player -> {
            if(player.equals(players.get(players.size()-1))){
                dealToAI(player);
            }
            else {
                dealToPlayer(player);
            }
            // System.out.println(deck.getAllCards().size());
        });
    }

    private void dealToPlayer(Player player){
        player.getHand().addCard(deck.getTopCard());
        player.getHand().getTopCard().flip();
        deck.removeCard(deck.getTopCard());
        player.getHand().addCard(deck.getTopCard());
        player.getHand().getTopCard().flip();
        deck.removeCard(deck.getTopCard());
    }

    private void dealToAI(Player player){
        player.getHand().addCard(deck.getTopCard());
        player.getHand().getTopCard().flip();
        deck.removeCard(deck.getTopCard());
        player.getHand().addCard(deck.getTopCard());
        deck.removeCard(deck.getTopCard());
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