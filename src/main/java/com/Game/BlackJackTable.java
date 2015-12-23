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

        reader = new Scanner(System.in);
    }

    public void startTheGame() {
        System.out.println("==$==$==Game start==$==$==");
        cardDeck.resetAndShuffle();
        blackJack.dealTheInitialCard(cardDeck, player, dealer);
        System.out.println(player+" receive 2 cards. | "+dealer+" receive one and a folded card.");
        printGameStatus(false);


        playerTurn();

        if (status != GameStatus.PLAYER_LOSE) {
            dealerTurn();
        }

        summaryPhase();
    }

    private void playerTurn() {

        while (!player.isTurnEnded()) {
            if (blackJack.isBlackJack(player)) {
                player.setTurnEnded(true);
                System.out.println(player+" BLACKJACK!");
                break;
            } else if (blackJack.is21(player)) {
                player.setTurnEnded(true);
                System.out.println(player+" 21!");
            }

            ArrayList<Action> actions = playerPossibleAction(player);

            for (Action act: actions) {
                System.out.println(act);
            }

            //TODO: input action.

            System.out.println("PLEASE SELECT THE ACTION: ");
            Action playerAction = playerInputsAction(player);

            while(!blackJack.isPlayerDoValidAction(player, dealer, playerAction)) {
                System.out.println("Invalid.\nPLEASE SELECT THE ACTION: ");
                playerAction = playerInputsAction(player);
            }

            blackJack.playerDoAction(player, cardDeck, playerAction);
            player.printPlayerStatus();

            status = blackJack.updateGameStatus(player, dealer);
            if (status == GameStatus.PLAYER_LOSE) {
                player.setTurnEnded(true);
                break;
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

        if (blackJack.canPlayerTakeAnInsurance(player, dealer))
            actions.add(Action.INSURANCE);
        if (blackJack.canPlayerDouble(player))
            actions.add(Action.DOUBLE);
        if (blackJack.canPlayerSurrender(player))
            actions.add(Action.SURRENDER);
        if (blackJack.canPlayerSplit(player))
            actions.add(Action.SPLIT);

        return actions;

    }

    private Action playerInputsAction(Player player) {
            int action = reader.nextInt();
        return Action.fromInteger(action);
    }

    private void dealerTurn() {

        while (!dealer.isTurnEnded()) {
            if (dealer.isBlackJack()) {
                dealer.setTurnEnded(true);
                System.out.println(dealer+" BLACKJACK!");
                break;
            }

            if (dealer.getHandValue() <= 16) {
                dealer.drawCard(cardDeck.drawCardFromTheTop());
            } else {
                dealer.setTurnEnded(true);
            }

            dealer.printDealerStatus();

            status = blackJack.updateGameStatus(player, dealer);
            if (status == GameStatus.DEALER_LOSE) {
                dealer.setTurnEnded(true);
                break;
            }

        }

    }

    private void summaryPhase() {

        if (status == GameStatus.PLAYER_LOSE) {
            System.out.println(dealer+" win");
            return;
        }

        if (status == GameStatus.DEALER_LOSE) {
            System.out.println(dealer+" win");
            return;
        }

        if (player.getHandValue() > dealer.getHandValue()) {
            System.out.println(player+" Win.");
        } else if (player.getHandValue() < dealer.getHandValue()) {
            System.out.println(dealer+" win");
        } else {
            System.out.println("draw");
        }

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
