public class JudytaBot extends Player {

    public JudytaBot(int cash) {
        super("Judyta", cash);
    }

    @Override
    public void pass() {
        //game over;
    }

    @Override
    public boolean isPassed(String inputScaner) {
        return false;
    }

}