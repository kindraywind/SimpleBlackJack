package com.Game;

import com.Game.BlackJack;
import com.Player.Dealer;
import com.Player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by woramet on 12/22/15.
 */
public class BlackJackTest {

    Player player;
    Dealer dealer;
    BlackJack blackJack;

    @Before
    public void setUp() throws Exception {
        player = new Player("Johny");
        dealer = new Dealer("Elsa");
        blackJack = new BlackJack();
    }

    @After
    public void tearDown() throws Exception {
        player = null;
        dealer = null;
        blackJack = null;

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
}