public class Card {
    private int suit;
    private int rank;
    private boolean isFaceDown;
    private int value;

    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
        this.isFaceDown = true;
        setValue();
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
}