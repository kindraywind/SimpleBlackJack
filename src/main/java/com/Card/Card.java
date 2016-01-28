package com.Card;

/**
 * Created by woramet on 12/20/15.
 */
public class Card implements Comparable<Card>{

    private Suit suit;
    private CardValue value;

    public Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public int getIntValue() {
        return value.getCardValue();
    }

    @Override
    public String toString() {
        return value.toString()+suit.toString();
    }

    public int compareTo(Card o) {
        return this.value.getCardValue() - o.value.getCardValue();
    }
}

