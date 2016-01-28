package com.Game;

import com.Card.Deck;
import com.Player.Dealer;
import com.Player.Player;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by woramet on 12/20/15.
 */
public class BlackJackTable {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Dealer dealer;
    private Player player;
    private Deck cardDeck;
    private BlackJack blackJack;
    private GameStatus status;
    Scanner reader;

    public BlackJackTable(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
        this.cardDeck = new Deck();
        this.blackJack = new BlackJack();
        this.status = GameStatus.NONE;

        reader = new Scanner(System.in);
    }

    public void startTheGame() {
        System.out.println("==$==$==Game start==$==$==");
        cardDeck.resetAndShuffle();
        blackJack.dealTheInitialCard(cardDeck, player, dealer);
        System.out.println(player+" receives 2 cards. | "+dealer+" receives one and a hole card.");
        printGameStatus(false);


        playerTurn();

        if (status != GameStatus.PLAYER_LOSE) {
            dealerTurn();
        }

        summaryPhase();
    }

    private void playerTurn() {

        while (!player.isTurnEnded()) {

            System.out.println("===="+player+"'s turn====");

            if (blackJack.isBlackJack(player)) {
                player.setTurnEnded(true);
                System.out.println(player+" BLACKJACK!");
                break;
            } else {
                for (Action act: playerPossibleAction(player)) {
                    System.out.println(act);
                }

                System.out.println("PLEASE SELECT THE ACTION: ");
                Action playerAction = playerInputsAction(player);

                while(!blackJack.isPlayerDoValidAction(player, dealer, playerAction)) {
                    System.out.println("Invalid.\nPLEASE SELECT THE ACTION: ");
                    playerAction = playerInputsAction(player);
                }

                System.out.println(player+" "+playerAction.name());
                status = blackJack.playerDoAction(player, cardDeck, playerAction);
                player.printPlayerStatus();
                status = blackJack.updateGameStatus(player, dealer, status);

                if (blackJack.isShouldEnd(player)) {
                    if (blackJack.is21(player)) {
                        System.out.println(player+" 21!");
                    }
                    player.setTurnEnded(true);
                    return;
                }
            }

        }

    }

    private ArrayList<Action> playerPossibleAction(Player player) {

        ArrayList<Action> actions = new ArrayList<Action>();

        if (player.isTurnEnded())
            return actions;

        if (blackJack.canPlayerHit(player))
            actions.add(Action.HIT);

        if (blackJack.canPlayerStand(player))
            actions.add(Action.STAND);

        if (blackJack.canPlayerDouble(player))
            actions.add(Action.DOUBLE);
        if (blackJack.canPlayerSurrender(player))
            actions.add(Action.SURRENDER);

        return actions;

    }

    private Action playerInputsAction(Player player) {
            int action = reader.nextInt();
        return Action.fromInteger(action);
    }

    private void dealerTurn() {

        while (!dealer.isTurnEnded()) {

            System.out.println("===="+dealer+"'s turn====");

            if (blackJack.isBlackJack(dealer)) {
                dealer.setTurnEnded(true);
                System.out.println(dealer+" BLACKJACK!");
                return;
            } else if (blackJack.is21(dealer)) {
                dealer.setTurnEnded(true);
                System.out.println(dealer+" 21!");
                return;
            }

            if (dealer.getHandValue() <= 16 || player.getHandValue() > dealer.getHandValue()) {
                System.out.println(dealer+" "+Action.HIT.name());
                dealer.drawCard(cardDeck.drawCardFromTheTop());
            } else {
                System.out.println(dealer+" "+Action.STAND.name());
                dealer.setTurnEnded(true);
            }

            dealer.printDealerStatus();

            status = blackJack.updateGameStatus(player, dealer, status);
            if (status == GameStatus.DEALER_LOSE) {
                dealer.setTurnEnded(true);
                return;
            }

        }

    }

    private void summaryPhase() {
        if (status == GameStatus.PLAYER_LOSE) {
            System.out.println(dealer+" win");
            return;
        }

        if (status == GameStatus.DEALER_LOSE) {
            System.out.println(player+" win");
            return;
        }

        if (player.getHandValue() > dealer.getHandValue()) {
            System.out.println(player+" win.");
        } else if (player.getHandValue() < dealer.getHandValue()) {
            System.out.println(dealer+" win");
        } else {
            System.out.println("draw");
        }

        System.out.println("====GAME OVER====");

    }

    public void printGameStatus(boolean isUnfold) {
        player.printPlayerStatus();
        if (isUnfold) {
            dealer.printDealerStatus();
        } else {
            dealer.printDealerStatusWithFoldedCard();
        }
    }

}
