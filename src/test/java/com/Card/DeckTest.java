//package com.Card;

import com.Card.Card;
import com.Card.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woramet on 12/20/15.
 */
public class DeckTest {

    Deck deck;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @After
    public void tearDown() throws Exception {
        deck = null;
    }

    @Test
    public void testDeckShouldContain52Cards() throws Exception {
        assertEquals(52, deck.getCardCount());
    }

    @Test
    public void testDrawCardFromTheTop() throws Exception {
        Card card = deck.drawCardFromTheTop();
        assertNotNull(card);
        assertEquals(51, deck.getCardCount());
    }

    @Test
    public void testCardShouldNotBeDrawnWhenDeckEmpty() throws Exception {

        for(int i = 0 ; i < 52 ; i++) {
            deck.drawCardFromTheTop();
        }

        assertEquals(true, deck.isEmpty());
        assertNull(deck.drawCardFromTheTop());

    }

    @Test
    public void testIsEmpty() throws Exception {
        assertEquals(false, deck.isEmpty());
    }
}