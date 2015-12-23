package com.Player;

import com.Card.Card;
import com.Card.CardValue;
import com.Player.Person;
import com.Card.Suit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woramet on 12/20/15.
 */
public class PersonTest {

    Person person;
    Card aceSpades;
    Card fiveHearts;
    Card kingClubs;

    @Before
    public void setUp() throws Exception {
        aceSpades = new Card(Suit.SPADES, CardValue.ACE);
        fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        kingClubs = new Card(Suit.CLUBS, CardValue.KING);

        person = new Person("Johny");
    }

    @After
    public void tearDown() throws Exception {
        person = null;
        aceSpades = null;
        fiveHearts = null;
        kingClubs = null;
    }

    @Test
    public void testDrawCard() throws Exception {
        assertEquals(true, person.getCardsInHand().isEmpty());
        person.drawCard(aceSpades);
        assertEquals(false, person.getCardsInHand().isEmpty());
    }

    @Test
    public void testGetCardsInHand() throws Exception {
        person.drawCard(aceSpades);
        assertEquals(true, person.getCardsInHand().contains(aceSpades));
    }

    @Test
    public void testGetHandValue() throws Exception {
        person.drawCard(aceSpades);
        assertEquals(11, person.getHandValue());

        person.drawCard(fiveHearts);
        assertEquals(16, person.getHandValue());

        person.drawCard(kingClubs);
        assertEquals("must equal 16(Ace1 + 5 + 10)",16, person.getHandValue());
    }

    @Test
    public void testIsTurnEnded() throws Exception {
        assertEquals(false, person.isTurnEnded());
    }



    @Test
    public void testGetName() throws Exception {
        assertEquals("Johny", person.getName());
    }

    @Test
    public void testSetTurnEnded() throws Exception {
        person.setTurnEnded(true);
        assertEquals(true, person.isTurnEnded());

    }
}