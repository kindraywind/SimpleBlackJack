package com.Game;

import com.Game.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woramet on 12/22/15.
 */
public class ActionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFromInteger() throws Exception {
        Action a = Action.fromInteger(1);
        assertEquals(Action.HIT, a);

        a = Action.fromInteger(2);
        assertEquals(Action.STAND, a);

        a = Action.fromInteger(3);
        assertEquals(Action.DOUBLE, a);

        a = Action.fromInteger(4);
        assertEquals(Action.SURRENDER, a);

        a = Action.fromInteger(123);
        assertEquals(Action.INVALID, a);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("2. STAND", Action.STAND.toString());
    }
}