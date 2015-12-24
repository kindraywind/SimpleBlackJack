package com.Player;

import com.Card.Card;
import com.Card.CardValue;

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

        if (getHandValue() >= 21) {
            setTurnEnded(true);
        }
    }

    public Collection<Card> getCardsInHand() {
        return Collections.unmodifiableCollection(hands);
    }

    public int getHandValue() {
        int value = 0;
        Collections.sort(hands);

        for (Card c: hands) {
            value += c.getIntValue();

            if (value > 21 && c.getValue() == CardValue.ACE) {
                value -= 10;
            }
        }

        return value;
    }

    public boolean isTurnEnded() {
        return isTurnEnded;
    }

    public void setTurnEnded(boolean turnEnded) {
        isTurnEnded = turnEnded;
    }



    public String getName() {
        return name;
    }
}
