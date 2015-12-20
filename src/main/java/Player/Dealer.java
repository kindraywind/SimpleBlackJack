package Player;

import Card.Card;

import java.util.Collection;

/**
 * Created by woramet on 12/20/15.
 */
public class Dealer extends Person {

    public Dealer(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Dealer "+getName();
    }
}
