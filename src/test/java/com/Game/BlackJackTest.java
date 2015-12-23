package com.Game;

import com.Card.Card;
import com.Card.CardValue;
import com.Card.Suit;
import com.Game.BlackJack;
import com.Player.Dealer;
import com.Player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Created by woramet on 12/22/15.
 */
public class BlackJackTest {

    Player player;
    Dealer dealer;
    BlackJack blackJack;
    Card aceSpades, fiveHearts, kingClubs;

    @Before
    public void setUp() throws Exception {
        player = new Player("Johny");
        dealer = new Dealer("Elsa");
        blackJack = new BlackJack();

        aceSpades = new Card(Suit.SPADES, CardValue.ACE);
        fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        kingClubs = new Card(Suit.CLUBS, CardValue.KING);
    }

    @After
    public void tearDown() throws Exception {
        player = null;
        dealer = null;
        blackJack = null;

        aceSpades = null;
        fiveHearts = null;
        kingClubs = null;

    }

    //TODO: write unit-test from SE2 final project.
    @Test
    public void testCanPlayerHit() throws Exception {

    }

    @Test
    public void testCanPlayerStand() throws Exception {

    }

    @Test
    public void testCanPlayerTakeAnInsurance() throws Exception {

    }

    @Test
    public void testCanPlayerDouble() throws Exception {

    }

    @Test
    public void testCanPlayerSurrender() throws Exception {

    }

    @Test
    public void testCanPlayerSplit() throws Exception {

    }

    @Test
    public void testIsBlackJack() throws Exception {




        assertEquals(false, blackJack.isBlackJack(player));

        player.drawCard(kingClubs);
        player.drawCard(aceSpades);

        assertEquals(true, blackJack.isBlackJack(player));
    }

    @Test
    public void testDrawKingAndFive_shouldNotBlackJack() throws Exception {
        player.drawCard(kingClubs);
        player.drawCard(fiveHearts);

        assertEquals(false, blackJack.isBlackJack(player));
    }

    @Test
    public void testDrawFiveFiveAce_shouldNotBlackJack() throws Exception {
        assertEquals(false, blackJack.isBlackJack(player));

        player.drawCard(fiveHearts);
        player.drawCard(fiveHearts);
        player.drawCard(aceSpades);

        assertEquals(false, blackJack.isBlackJack(player));
    }
}