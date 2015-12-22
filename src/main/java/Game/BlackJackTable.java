package Game;

import Card.Card;
import Card.CardValue;
import Card.Deck;
import Player.Dealer;
import Player.Player;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by woramet on 12/20/15.
 */
public class BlackJackTable {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Dealer dealer;
    private Player player;
    private Deck cardDeck;
    Scanner reader;

    public BlackJackTable(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
        this.cardDeck = new Deck();

        reader = new Scanner(System.in);
    }

    public void startTheGame() {
        System.out.println("==$==$==Game start==$==$==");
        cardDeck.resetAndShuffle();

        dealTheInitialCard();
        playerTurn();
        dealerTurn();
        calculationPhase();
    }

    private void dealTheInitialCard() {
        System.out.println(player+" receive 2 cards. | "+dealer+" receive one and a folded card.");
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());

        printGameStatus(false);
    }

    private void playerTurn() {

        while (!player.isTurnEnded()) {
            if (player.isBlackJack()) {
                player.setTurnEnded(true);
                System.out.println(player+" BLACKJACK!");
                break;
            }

            ArrayList<Action> actions = playerPossibleAction(player);

            for (Action act: actions) {
                System.out.println(act);
            }

            //TODO: input action.
            int action = reader.nextInt();
            switch (action) {
                case 1:
                    player.drawCard(cardDeck.drawCardFromTheTop());
                    break;
                case 2:
                    player.setTurnEnded(true);
                    break;
                case 3:
                    player.setTurnEnded(true);
                    break;
                case 4:
                    player.drawCard(cardDeck.drawCardFromTheTop());
                    player.setTurnEnded(true);
                    break;
                case 5:
                    player.setTurnEnded(true);
                    break;
                case 6:
                    //split
                    break;
                default:
                    break;
            }
            player.printPlayerStatus();

        }

    }

    public ArrayList<Action> playerPossibleAction(Player player) {

        ArrayList<Action> actions = new ArrayList<Action>();

        Integer canHit = 1;
        Integer canStand = 2;
        Integer canInsurance = 3;
        Integer canDouble = 4;
        Integer canSurrender = 5;
        Integer cansplit = 6;

        if (player.isTurnEnded())
            return actions;

        if (player.getHandValue() != 21)
            actions.add(Action.HIT);

        actions.add(Action.STAND);

        if (dealer.getCardsInHand().iterator().next().getValue() == CardValue.ACE)
            actions.add(Action.INSURANCE);
        if (player.getCardsInHand().size() == 2)
            actions.add(Action.DOUBLE);
        if (dealer.getCardsInHand().iterator().next().getValue().getCardValue() >= CardValue.NINE.getCardValue())
            actions.add(Action.SURRENDER);

        Iterator<Card> playerHand = player.getCardsInHand().iterator();
        Card first = playerHand.next();
        Card second = playerHand.next();

        if (player.getCardsInHand().size() == 2 && first.getValue().getCardValue() == second.getValue().getCardValue())
            actions.add(Action.SPLIT);

        return actions;

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

        }

    }

    private void calculationPhase() {

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
