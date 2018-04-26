import java.security.Principal;
import java.util.*;

//import Card.Suits;

public class Game {
    private ArrayList<Player> players;
    private Pile deck;
    private int cashPool;
    private Display display;

    public Game(ArrayList<Player> players) {
        this.players = new ArrayList<>(players);
        display = new Display(players);
        cashPool = 0;
        deck = createDeck();
        shuffleDeck();
        dealCards();
        display.table(cashPool);
        placingBets();
        gameLogic();
    }

    public void launch() {
        Display display = new Display(players);
        display.table(cashPool);
    }

    private void placingBets(){
        Iterator<Player> playerIterator = players.iterator();
        playerIterator.forEachRemaining(player -> {
            if(player.equals(players.get(players.size()-1))){
                cashPool = cashPool*2;
                System.out.println("Judyta doubles Cash Pool: " + cashPool);
            }
            else{
                int cash = getInput("Give bet! ");
                player.betCoins(cash);
                cashPool += cash;
                //player.setCoolcoin(player.getCoolcoin()-cash);
                System.out.println("Cash Pool: " + cashPool);
            }            
        });
    }

    private int getInput(String text) {
        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("This isn't a number!");
        }
        return choice;
    }

    private Pile createDeck(){
        Pile deck = new Pile();
        for (Card.Suits suit : Card.Suits.values()) {
            for (int j=2; j<15; j++){
                deck.addCard(new Card(suit, j, deck));
                // System.out.println("rank = " + i + "suit = " + j);
            }
        }
        return deck;
    }

    public void newRound(){
        this.cashPool = 0;
        clearTable();
        shuffleDeck();
        dealCards();
        placingBets();
        gameLogic();
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
        player.setScore(player.getHand().givePiletotalScore());
    }

    private void dealToAI(Player player){
        player.getHand().addCard(deck.getTopCard());
        player.getHand().getTopCard().flip();
        deck.removeCard(deck.getTopCard());
        player.getHand().addCard(deck.getTopCard());
        deck.removeCard(deck.getTopCard());
        player.setScore(player.getHand().getAllCards().get(0).getValue());
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
    private void clearTable(){
        PlayerIterator playerIterator = new PlayerIterator(players);
        while(playerIterator.hasNext()){
            Player player = playerIterator.next();
            clearPlayerPile(player);
        }     
    }

    public void clearPlayerPile(Player player){
        Card topFirstCard = player.getHand().getTopCard();
        deck.addCard(topFirstCard);
        player.getHand().removeCard(topFirstCard);
        Card topSecondCard = player.getHand().getTopCard();
        deck.addCard(topSecondCard);
        player.getHand().removeCard(topSecondCard);
        // System.out.println(deck.getAllCards().size());
    }

    private void gameLogic(){
        Iterator<Player> playerIterator = players.iterator();
        playerIterator.forEachRemaining(player -> {
            while(player.getBust()==false && player.getPass()==false){
                int choice = getInput("1. Hit me!\n2. Pass!");
                switch (choice) {
                    case 1:
                        player.takeCard(deck);
                        player.setScore(player.getHand().givePiletotalScore());
                        display.table(cashPool);
                        if(player.getScore()>21){
                            player.setBust(true);
                        }
                    case 2:
                        player.setPass(true);
                }
                
            }
        });
    }
}