import Card.Deck;
import Player.Dealer;
import Player.Player;

import java.util.Scanner;

/**
 * Created by woramet on 12/20/15.
 */
public class BlackJackTable {

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

    public void dealTheInitialCard() {
        System.out.println(player+" receive 2 cards. | "+dealer+" receive one and a folded card.");
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());

        printGameStatus(false);
    }

    public void playerTurn() {

        while (!player.isTurnEnded()) {
            if (player.isBlackJack()) {
                player.setTurnEnded(true);
                System.out.println(player+" BLACKJACK!");
                break;
            }

            player.printAllPossibleAction();
            //TODO: input action.
            int action = reader.nextInt();
            switch (action) {
                case 0:
                    player.setTurnEnded(true);
                    break;
                case 1:
                    player.drawCard(cardDeck.drawCardFromTheTop());
                    break;
                default:
                    break;
            }

        }

    }

    public void dealerTurn() {

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

        }

    }

    public void calculationPhase() {

    }

    public void printGameStatus(boolean isUnfold) {
        System.out.println(player+"'s hand: "+player.getCardsInHand()+"("+player.getHandValue()+"points)");
        if (isUnfold) {
            System.out.println(dealer+"'s hand: "+dealer.getCardsInHand()+"("+dealer.getHandValue()+"points)");
        }
        System.out.println(dealer+"'s hand: "+dealer.getCardsInHand().iterator().next()+", *FOLD*");
    }

}
