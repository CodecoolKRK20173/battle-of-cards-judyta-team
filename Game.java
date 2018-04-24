import javax.naming.InitialContext;
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

}