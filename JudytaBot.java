public class JudytaBot extends Player {

    public JudytaBot(int cash) {
        super("Judyta", cash);
    }

    @Override
    public void pass() {
    }

    @Override
    public boolean isPassed() {
        return true;
    }

}