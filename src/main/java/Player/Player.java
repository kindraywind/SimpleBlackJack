package Player;

import Card.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by woramet on 12/20/15.
 */
public class Player implements Person{

    private ArrayList<Card> hands;
    private String name;

    public Player(String name) {
        this.name = name;
        hands = new ArrayList<Card>();
    }

    public void drawCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Cannot draw null card.");
        }
        hands.add(card);
    }

    public Collection<Card> getCardsInHand() {
        return Collections.unmodifiableCollection(hands);
    }

    public int getHandValue() {

        Collections.sort(hands);

        for (Card c: hands) {

        }

        return 0;
    }

    public boolean isTurnEnded() {
        return false;
    }

    public String getName() {
        return name;
    }
}
