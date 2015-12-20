package Player;

import Card.Card;
import Card.CardValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by woramet on 12/20/15.
 */
public class Player extends Person{

    public Player(String name) {
        super(name);
    }

    public boolean isCanHit() {
        return true;
    }

    @Override
    public String toString() {
        return "Player: "+getName();
    }
}
