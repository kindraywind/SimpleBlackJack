import Card.Deck;
import Player.Dealer;
import Player.Player;

/**
 * Created by woramet on 12/20/15.
 */
public class BlackJackTable {

    private Dealer dealer;
    private Player player;
    private Deck cardDeck;

    public BlackJackTable(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
        this.cardDeck = new Deck();
    }

    public void startTheGame() {
        System.out.println("==Game start==");
        cardDeck.resetAndShuffle();

        dealTheInitialCard();
    }

    public void dealTheInitialCard() {
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());

        printGameStatus();
    }

    public void printGameStatus() {
        System.out.println("Player's hand: "+player.getCardsInHand());
        System.out.println("Dealer's hand: "+dealer.getCardsInHand().iterator().next()+", *FOLD*");
    }

}
