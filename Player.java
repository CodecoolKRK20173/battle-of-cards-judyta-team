public abstract class Player {
    private String name;
    private int coolcoin;
    private Pile hand;
    
    public Player(String name, int cash) {
        setName(name);
        setCoolcoin(cash);
        this.hand = new Pile();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoolcoin(int cash) {
        this.coolcoin = cash;
    }

    public int getCoolcoin() {
        return this.coolcoin;
    }

    public void placingBets(int cash) {
        //System.out.println("Bet game!")
        //Scanner input = new Scanner(System.in);
        // int cash = input.nextInt();
        int coolcoin = getCoolcoin();
        setCoolcoin(coolcoin-cash);
        Game.addToCashPool(cash);
        
    }

    public void hit() {
        hand.addCard(Pile.getTopCard());
    }

    public abstract void keepHand();


}