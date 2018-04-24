public abstract class Player {
    private String name;
    private int coolcoin;
    private Pile hand;
    private Game game;
    private int score;
    private boolean bust = false;
    private boolean winner = false;

    
    public Player(String name, int cash) {
        setName(name);
        setCoolcoin(cash);
        this.hand = new Pile();
        game = null;
    }

    public String getName() {
        return this.name;
    }

    public int getCoolcoin() {
        return this.coolcoin;
    }

    public Pile getHand() {
        return this.hand;
    }

    public int getScore() {
        return this.score;
    }

    public boolean getBust() {
        return this.bust;
    }

    public boolean getWinner() {
        return this.winner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoolcoin(int cash) {
        this.coolcoin = cash;
    }

    public void setGame(Game game) {
        this.game = game;
    } 

    public void setScore(int score) {
        this.score = score;
    } 

    public void setBust(boolean bust) {
        this.bust = bust;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    } 

    public void betCoins(int cash) {
        //System.out.println("Bet game!")
        //Scanner input = new Scanner(System.in);
        // int cash = input.nextInt();
        int coolcoin = getCoolcoin();
        setCoolcoin(coolcoin-cash);
        game.setCashPool(game.getCashPool() + cash);
    }

    public void takeCard() {
        Pile deck = game.getDeck();
        Card cardToHit = deck.getTopCard();
        cardToHit.changePileToDest(deck);
    }

    public abstract void pass();

}