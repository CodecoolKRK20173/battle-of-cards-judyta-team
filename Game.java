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
        cashPool = 0;
        deck = createDeck();
        display = new Display(players);

    }

    public void launch() {

        int moneyChecker = 1;
        while (moneyChecker != 0) {
            newRound();

            for (Player player : players) {

               
                gameLogic(player);
                display.table(cashPool);

            }

            for(Player player : players){
                moneyChecker = moneyChecker*player.getCoolcoin();
            }

            
        }
    }

    private void placingBets(){
        Iterator<Player> playerIterator = players.iterator();
        playerIterator.forEachRemaining(player -> {
            display.table(cashPool);
            if(player.equals(players.get(players.size()-1))){
                cashPool = cashPool*2;
                System.out.println("Judyta doubles Cash Pool: " + cashPool);
            }
            else{
                int cash = betCondition(player);
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

        for(int i = 0; i < player.getHand().getSize(); i++){
            Card topFirstCard = player.getHand().getTopCard();
            deck.addCard(topFirstCard);
            player.getHand().removeCard(topFirstCard);
        }   
    }

    private void gameLogic(Player player){

        System.out.println(player.getName() + "'s turn!");
        if(player.getName()!="Judyta"){
            while(player.getBust()==false && player.getPass()==false){
                int choice = getInput("1. Hit me!\n2. Pass!");
                switch (choice) {
                    case 1:
                        player.takeCard(deck);
                        player.setScore(player.getHand().givePiletotalScore());
                        display.table(cashPool);
                        System.out.println(player.getScore());
                        if(player.getScore()>21){
                            player.setBust(true);
                            player.setScore(0);
                            System.out.println(player.getName() + " busted! - Your score was too high");
                        }
                        break;
                    case 2:
                        player.setPass(true);
                        System.out.println(player.getName() + " passed!");
                        break;
                }
            }   
        } else {
            handleJudytaBot(player);
            }

        
    }

    private void handleJudytaBot(Player player) {
        while(player.getBust()==false && player.getPass()==false) {
            player.getHand().getTopCard().flip();
            player.setScore(player.getHand().givePiletotalScore());
            System.out.println(player.getScore());
            int botScore = player.getScore();

            if (botScore > 21) {
                player.setBust(true);
                System.out.println(player.getName() + " busted!");
            } else if (botScore >= 20) {
                player.setPass(true);
                System.out.println(player.getName() + " passed!");
            } else {
                Player playerWithHighestScore = playerWithHighestScore();
                if (player.getScore() > playerWithHighestScore.getScore()) {
                    player.setPass(true);
                    System.out.println(player.getName() + " passed 2!");
                } else {
                    player.takeCard(deck);
                    player.setScore(player.getHand().givePiletotalScore());
                    System.out.println(player.getName() + " Hit a card");
                }

            } 
        }
    }

    private Player playerWithHighestScore() {

        ArrayList<Player> humanPlayers = display.getHumanPlayers();
        PlayerIterator playerIterator = new PlayerIterator(humanPlayers);
        Player playerWithHighestScore = null;
        int highestScore = 0;
        do {
            Player player = playerIterator.next();
            if (player.getScore() >= highestScore) {
                highestScore = player.getScore();
                playerWithHighestScore = player;
                }
        } while (playerIterator.hasNext());
        return playerWithHighestScore;
    }
    private int betCondition(Player player){

        while(true){
            int cash = getInput("Give bet! ");
            if(cash > player.getCoolcoin() ||  cash < 0){
                System.out.println("Not enough money");
            }
            else if(cash == 0);
            else{
                return cash;
            }
        }   
    }
}
