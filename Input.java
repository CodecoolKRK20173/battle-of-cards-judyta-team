import java.util.*;


public class Input {
    private String input;

    public Input() {
        Scanner input = new Scanner(System.in);
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int stringToInt(String input) {
        int inputInt = Integer.parseInt(input);
        return inputInt;
    }
    
}



