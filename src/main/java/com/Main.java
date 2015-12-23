package com;

import com.Game.BlackJackTable;
import com.Player.Dealer;
import com.Player.Player;

import java.io.Reader;
import java.util.Scanner;

/**
 * Created by woramet on 12/20/15.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here

        boolean isRestart = true;

        Dealer elsa = new Dealer("Elsa");
        Player johny = new Player("Johny");
        Scanner reader = new Scanner(System.in);

        BlackJackTable bjTable = new BlackJackTable(elsa, johny);

        while (isRestart) {
            elsa = new Dealer("Elsa");
            johny = new Player("Johny");
            bjTable = new BlackJackTable(elsa, johny);
            bjTable.startTheGame();

            System.out.println("type y to restart or any to exit.");
            String input = reader.nextLine();

            if (input.equalsIgnoreCase("y")) {
                isRestart = true;
            } else {
                isRestart = false;
            }

        }

    }
}

