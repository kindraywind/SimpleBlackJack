package com.Player;

import com.Card.Card;
import com.Card.CardValue;
import com.Card.Suit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woramet on 1/15/16.
 */
public class PlayerTest {

    Player player;
    Card aceSpades;
    Card fiveHearts;
    Card kingClubs;

    @Before
    public void setUp() throws Exception {
        aceSpades = new Card(Suit.SPADES, CardValue.ACE);
        fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        kingClubs = new Card(Suit.CLUBS, CardValue.KING);

        player = new Player("Johny");
    }

    @After
    public void tearDown() throws Exception {
        player = null;
        aceSpades = null;
        fiveHearts = null;
        kingClubs = null;
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Player Johny",player.toString());

    }
}