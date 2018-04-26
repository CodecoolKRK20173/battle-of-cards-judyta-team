import java.util.*;

public class Card {
    private Suits suit;
    private int rank;
    private boolean isFaceDown;
    private int value;
    private Pile pile;

    public Card(Suits suit, int rank, Pile pile){
        this.suit = suit;
        this.rank = rank;
        this.isFaceDown = true;
        setValue();
        this.pile = pile;
    }
    public void flip(){
        if(isFaceDown == true){
            this.isFaceDown = false;
        }
        else{
            this.isFaceDown = true;
        }
    }
    public boolean isFaceDown(){
        return this.isFaceDown;
    }
    private void setValue(){
        if(rank < 10){
            this.value = this.rank;
        }
        else if(rank >= 10 && rank < 14){
                this.value = 10;
        }
        else{
            this.value = 11;
        }
    }
    public int getValue(){
        return this.value;
    }
    public String toString(){
        //String cartToPrint = "";
        return "" + this.rank + this.getIcon() + " ";
    }
    public int getCodePointValue() {
        return 127137;
    }

    public void changePileToDest(Pile destPile) {
        destPile.addCard(this);
        pile.removeCard(this);
        pile = destPile;
    }

    public enum Suits {
        DIAMOND,
        HEART,
        PEAK,
        CLUB
    }

    public String getIcon() {
        Map<Suits, String> suitIcon = new HashMap<Suits, String>();
 
        suitIcon.put(Suits.DIAMOND, "\u2666");
        suitIcon.put(Suits.HEART, "\u2665");
        suitIcon.put(Suits.PEAK, "\u2660");
        suitIcon.put(Suits.CLUB, "\u2663");

        //karo "\u2666"
        //kier "\u2665"
        //pik "\u2660"
        //trefl "\u2663"
        return suitIcon.get(this.suit);
    }

}