package Player;

import Card.Card;

import java.util.Collection;

/**
 * Created by woramet on 12/20/15.
 */
public interface Person {

    void drawCard(Card card);

    Collection<Card> getCardsInHand();

    int getHandValue();

    boolean isTurnEnded();
}
