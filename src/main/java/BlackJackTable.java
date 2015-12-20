import Card.Deck;
import Player.Dealer;
import Player.Gambler;

/**
 * Created by woramet on 12/20/15.
 */
public class BlackJackTable {

    private Dealer dealer;
    private Gambler gambler;
    private Deck cardDeck;

    public BlackJackTable(Dealer dealer, Gambler gambler) {
        this.dealer = dealer;
        this.gambler = gambler;
    }

    public void startTheGame() {

    }
}
