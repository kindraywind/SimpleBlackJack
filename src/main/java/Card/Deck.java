package Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by woramet on 12/20/15.
 */
public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        resetAndShuffle();
    }

    public void resetAndShuffle() {
        cards = new ArrayList<Card>();
        Card c;

        for (Suit s: Suit.values()) {
            for (CardValue v: CardValue.values()) {
                c = new Card(s, v);
                cards.add(c);
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawCardFromTheTop() {
        if (isEmpty()){
            System.out.println("Deck is empty.");
            return null;
        }

        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getCardCount() {
        return cards.size();
    }
}
