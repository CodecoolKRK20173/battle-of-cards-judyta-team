public class Card {
    private int suit;
    private int rank;
    private boolean isFaceDown;
    private int value;
    private Pile pile;

    public Card(int suit, int rank, Pile pile){
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
            this.rank = this.value;
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
        return "The " + "Rank" + this.rank + " of " + "Suit" + this.suit;
    }
    public int getCodePointValue() {
        return 127137;
    }

    public void changePileToDest(Pile destPile) {
        destPile.addCard(this);
        pile.removeCard(this);
        pile = destPile;
    }
}