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

        Dealer elsa;
        Player player;
        Scanner reader = new Scanner(System.in);

        System.out.println("Please enter your name:");
        String name = reader.nextLine();

        BlackJackTable bjTable;

        while (isRestart) {
            elsa = new Dealer("Elsa");
            player = new Player(name);
            bjTable = new BlackJackTable(elsa, player);
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

