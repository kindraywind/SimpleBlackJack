package com.Game;

import com.Card.Card;
import com.Card.CardValue;
import com.Card.Deck;
import com.Card.Suit;
import com.Player.Dealer;
import com.Player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


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

    @Test
    public void testDealTheInitialCard() throws Exception {
        Deck deck = new Deck();
        blackJack.dealTheInitialCard(deck, player, dealer);

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(2, dealer.getCardsInHand().size());
    }

    //TODO: write unit-test from SE2 final project.

    private void variant1ExpectedResult() {
        assertTrue(blackJack.canPlayerHit(player));
        assertTrue(blackJack.canPlayerStand(player));
        assertTrue(blackJack.canPlayerDouble(player));
        assertTrue(blackJack.canPlayerSurrender(player));
        assertFalse(blackJack.isShouldEnd(player));
        assertFalse(blackJack.isBlackJack(player));
    }

    private void variant2ExpectedResult() {
        assertTrue(blackJack.canPlayerHit(player));
        assertTrue(blackJack.canPlayerStand(player));
        assertTrue(blackJack.canPlayerDouble(player));
        assertFalse(blackJack.canPlayerSurrender(player));
        assertFalse(blackJack.isShouldEnd(player));
        assertFalse(blackJack.isBlackJack(player));
    }

    private void variant3ExpectedResult() {
        assertFalse(blackJack.canPlayerHit(player));
        assertFalse(blackJack.canPlayerStand(player));
        assertFalse(blackJack.canPlayerDouble(player));
        assertFalse(blackJack.canPlayerSurrender(player));
        assertTrue(blackJack.isShouldEnd(player));
        assertTrue(blackJack.isBlackJack(player));
    }

    private void variant4ExpectedResult() {
        assertTrue(blackJack.canPlayerHit(player));
        assertTrue(blackJack.canPlayerStand(player));
        assertFalse(blackJack.canPlayerDouble(player));
        assertFalse(blackJack.canPlayerSurrender(player));
        assertFalse(blackJack.isShouldEnd(player));
        assertFalse(blackJack.isBlackJack(player));
    }

    private void variant5ExpectedResult() {
        assertFalse(blackJack.canPlayerHit(player));
        assertFalse(blackJack.canPlayerStand(player));
        assertFalse(blackJack.canPlayerDouble(player));
        assertFalse(blackJack.canPlayerSurrender(player));
        assertTrue(blackJack.isShouldEnd(player));
        assertFalse(blackJack.isBlackJack(player));
    }

    private void illegalStatus() {
        assertFalse(blackJack.canPlayerHit(player));
        assertFalse(blackJack.canPlayerStand(player));
        assertFalse(blackJack.canPlayerDouble(player));
        assertFalse(blackJack.canPlayerSurrender(player));
        assertFalse(blackJack.isShouldEnd(player));
        assertFalse(blackJack.isBlackJack(player));
    }

    @Test
    public void testV1_1() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.FOUR));
        player.drawCard(new Card(Suit.SPADES, CardValue.SIX));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(10, player.getHandValue());

        variant1ExpectedResult();
    }

    @Test
    public void testV1_2() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TEN));

        assertEquals(1, player.getCardsInHand().size());
        assertEquals(10, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV1_3() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.HEARTS, CardValue.TWO));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(4, player.getHandValue());

        variant1ExpectedResult();
    }

    @Test
    public void testV1_4() throws Exception {

        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(1).when(c).getIntValue();

        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(c);

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(3, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));

    }

    @Test
    public void testV1_5() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.QUEEN));
        player.drawCard(new Card(Suit.SPADES, CardValue.SIX));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(16, player.getHandValue());

        variant1ExpectedResult();
    }

    @Test
    public void testV2_1() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.SPADES, CardValue.SEVEN));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(18, player.getHandValue());

        variant2ExpectedResult();
    }

    @Test
    public void testV2_2() throws Exception {
        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(18).when(c).getIntValue();

        player.drawCard(c);

        assertEquals(1, player.getCardsInHand().size());
        assertEquals(18, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV2_3() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TEN));
        player.drawCard(new Card(Suit.SPADES, CardValue.SEVEN));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(17, player.getHandValue());

        variant2ExpectedResult();
    }

    @Test
    public void testV2_4() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.KING));
        player.drawCard(new Card(Suit.SPADES, CardValue.JACK));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(20, player.getHandValue());

        variant2ExpectedResult();
    }

    @Test
    public void testV3_1() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.SPADES, CardValue.JACK));

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(21, player.getHandValue());

        variant3ExpectedResult();
    }

    @Test
    public void testV3_2() throws Exception {
        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(21).when(c).getIntValue();

        player.drawCard(c);

        assertEquals(1, player.getCardsInHand().size());
        assertEquals(21, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV4_1() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.FIVE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.FIVE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.EIGHT));

        assertEquals(3, player.getCardsInHand().size());
        assertEquals(18, player.getHandValue());

        variant4ExpectedResult();
    }

    @Test
    public void testV4_2() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TWO));
        player.drawCard(new Card(Suit.HEARTS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.THREE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.THREE));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.ACE));
        player.drawCard(new Card(Suit.HEARTS, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.ACE));

        assertEquals(10, player.getCardsInHand().size());
        assertEquals(18, player.getHandValue());

        variant4ExpectedResult();
    }

    @Test
    public void testV4_3() throws Exception {

        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TWO));
        player.drawCard(new Card(Suit.HEARTS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.THREE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.THREE));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.ACE));
        player.drawCard(new Card(Suit.HEARTS, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.ACE));

        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(0).when(c).getIntValue();

        player.drawCard(c);

        assertEquals(11, player.getCardsInHand().size());
        assertEquals(18, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV4_4() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TWO));
        player.drawCard(new Card(Suit.HEARTS, CardValue.TWO));

        assertEquals(3, player.getCardsInHand().size());
        assertEquals(6, player.getHandValue());

        variant4ExpectedResult();
    }

    @Test
    public void testV4_5() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TWO));

        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(1).when(c).getIntValue();

        player.drawCard(c);

        assertEquals(3, player.getCardsInHand().size());
        assertEquals(5, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV4_6() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.SPADES, CardValue.NINE));
        player.drawCard(new Card(Suit.SPADES, CardValue.JACK));

        assertEquals(3, player.getCardsInHand().size());
        assertEquals(20, player.getHandValue());

        variant4ExpectedResult();
    }

    @Test
    public void testV5_1() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.KING));
        player.drawCard(new Card(Suit.SPADES, CardValue.QUEEN));
        player.drawCard(new Card(Suit.SPADES, CardValue.FIVE));

        assertEquals(3, player.getCardsInHand().size());
        assertEquals(25, player.getHandValue());

        variant5ExpectedResult();
    }

    @Test
    public void testV5_2() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.JACK));

        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(15).when(c).getIntValue();

        player.drawCard(c);

        assertEquals(2, player.getCardsInHand().size());
        assertEquals(25, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV5_3() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TWO));
        player.drawCard(new Card(Suit.HEARTS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.THREE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.THREE));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.ACE));
        player.drawCard(new Card(Suit.HEARTS, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.SEVEN));

        assertEquals(11, player.getCardsInHand().size());
        assertEquals(25, player.getHandValue());

        variant5ExpectedResult();
    }

    @Test
    public void testV5_4() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.TWO));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TWO));
        player.drawCard(new Card(Suit.HEARTS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.TWO));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.THREE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.THREE));
        player.drawCard(new Card(Suit.HEARTS, CardValue.THREE));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.ACE));
        player.drawCard(new Card(Suit.HEARTS, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.ACE));

        player.drawCard(new Card(Suit.DIAMONDS, CardValue.FOUR));

        assertEquals(12, player.getCardsInHand().size());
        assertEquals(25, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
    }

    @Test
    public void testV5_5() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.SEVEN));
        player.drawCard(new Card(Suit.HEARTS, CardValue.SEVEN));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.FIVE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.ACE));

        assertEquals(5, player.getCardsInHand().size());
        assertEquals(21, player.getHandValue());

        variant5ExpectedResult();
    }

    @Test
    public void testV5_6() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.SEVEN));
        player.drawCard(new Card(Suit.HEARTS, CardValue.SEVEN));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.FIVE));
        player.drawCard(new Card(Suit.CLUBS, CardValue.TEN));

        assertEquals(5, player.getCardsInHand().size());
        assertEquals(30, player.getHandValue());

        variant5ExpectedResult();
    }

    @Test
    public void testV5_7() throws Exception {
        player.drawCard(new Card(Suit.SPADES, CardValue.SEVEN));
        player.drawCard(new Card(Suit.HEARTS, CardValue.SEVEN));
        player.drawCard(new Card(Suit.SPADES, CardValue.ACE));
        player.drawCard(new Card(Suit.DIAMONDS, CardValue.FIVE));

        Card c = mock(Card.class);
        when(c.getSuit()).thenReturn(Suit.DIAMONDS);
        doReturn(11).when(c).getIntValue();

        player.drawCard(c);

        assertEquals(5, player.getCardsInHand().size());
        assertEquals(31, player.getHandValue());

        illegalStatus();
        assertTrue(blackJack.isImpossibleHand(player));
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