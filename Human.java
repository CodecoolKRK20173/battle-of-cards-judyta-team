public class Human extends Player {

    public Human(String name) {
        super(name, 100);
    }
    
    @Override
    public void pass() {
        //ustaw player'a na szaro      
    }

    @Override
    public boolean isPassed(String inputScanner) {
        if (inputScanner.equals("p")) {
            return true;
        } else if (inputScanner.equals("n")) {
            return false;
        }
        else return true;
    }
}