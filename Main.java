import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        
        int option = 1;
        while (option != 0) {
            displayMenu();
            try {
                option = Input.getInt("Please choose option: ");
                handleMenu(option, players);
            } catch (InputMismatchException e) {
                System.out.println("Enter digit from menu");
             } 
            //catch (NullPointerException e) {
            //     System.out.println(e);
            // }           
        }
    }

    
    public static void displayMenu() {
        String[] menu = new String[] { "Exit", "Add new Player", "Play new game"};
        for (int i = 0; i < menu.length; i++) {
            System.out.format("[%d] -> %s\n", i, menu[i]);
        }  
    }

    public static void handleMenu(int option, ArrayList<Player> players) {
        switch (option) {
            case 1:
                handleAddPlayer(players);
                break;
            case 2:
                if (players.size()>0) {
                    Game game = new Game(players);
                } else {
                    System.out.println("There is no player. Please enter: Add new Player");
                }
                break;
            default:
                System.out.println("There is no such option");
                break;
        }
    }

    public static void handleAddPlayer(ArrayList<Player> players) {
        String input = Input.getString("Enter your nick: ");
        Player newPlayer = new Human(input);
        players.add(newPlayer);
    }


}