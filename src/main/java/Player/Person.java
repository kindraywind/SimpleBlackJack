package Player;

import Card.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by woramet on 12/20/15.
 */
public class Person {

    ArrayList<Card> hands;
    String name;
    boolean isTurnEnded;

    public Person(String name) {
        this.name = name;
        hands = new ArrayList<Card>();
        isTurnEnded = false;
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
        int value = 0;
        Collections.sort(hands);

        for (Card c: hands) {
            value += c.getValue().getCardValue();

            if (value > 21 && c.getValue() == CardValue.ACE) {
                value -= 10;
            }
        }

        return value;
    }

    public boolean isTurnEnded() {
        return isTurnEnded;
    }

    public boolean isBlackJack() {
        return  (getCardsInHand().size() == 2 && getHandValue() == 21);
    }

    public String getName() {
        return name;
    }
}
