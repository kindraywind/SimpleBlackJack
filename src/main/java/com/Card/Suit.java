package com.Card;

public enum Suit
{
    HEARTS("♥"),
    SPADES("♠"),
    CLUBS("♣"),
    DIAMONDS("♦");

    private String symbol;

    private Suit (String symbol)
    {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
