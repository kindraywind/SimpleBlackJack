package Card;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by woramet on 12/20/15.
 */
public class CardTest {

    Card aceSpade;

    @org.junit.Before
    public void setUp() throws Exception {
        aceSpade = new Card(Suit.SPADES, CardValue.ACE);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        aceSpade = null;
    }

    @org.junit.Test
    public void testGetSuit() throws Exception {
        assertEquals(Suit.SPADES, aceSpade.getSuit());
    }

    @org.junit.Test
    public void testGetValue() throws Exception {
        assertEquals(CardValue.ACE, aceSpade.getValue());

    }

    @org.junit.Test
    public void testToString() throws Exception {
        assertEquals("ACE♠", aceSpade.toString());

    }

    @org.junit.Test
    public void testCompareTo() throws Exception {
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);

        ArrayList<Card> list = new ArrayList<Card>();
        list.add(aceSpade);
        list.add(twoClubs);

        Collections.sort(list);

        assertEquals(twoClubs, list.get(0));
    }
}