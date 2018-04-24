import java.util.*;

import com.codecool.klondike.Card;

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

    private int getBet() {
        System.out.println("Bet my friend! ");
        Scanner scanner = new Scanner(System.in);
        int bet = 0;
        try {
            bet = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("This isn't a number!");
        }
        return bet;
    }

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

    private void dealCards(ArrayList<Card> deck){
        for (int i=1; i=players.length(); i++){
            playerPiles.get(i).add(deck.getTopCard());
            deck.remove(0);
            if (i=players.length() && playerPiles.get(i).length()==1){
            }
            else {
                playerPiles.get(i).get()
            }
        }
    }

    private void shuffleDeck(ArrayList<Card> deck){
        Collections.shuffle(deck);
    }
}