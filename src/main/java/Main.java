import Game.BlackJackTable;
import Player.*;

/**
 * Created by woramet on 12/20/15.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello world");

        Dealer elsa = new Dealer("Elsa");
        Player johny = new Player("Johny");

        BlackJackTable bjTable = new BlackJackTable(elsa, johny);
        bjTable.startTheGame();
    }
}

