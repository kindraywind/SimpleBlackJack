package com.Player;

/**
 * Created by woramet on 12/20/15.
 */
public class Player extends Person{

    public Player(String name) {
        super(name);
    }

    public void printPlayerStatus() {
        if (getHandValue() > 21) {
            System.out.println(this+"'s hand: "+getCardsInHand()+"("+getHandValue()+"points) *BUSTS!");
        } else {
            System.out.println(this+"'s hand: "+getCardsInHand()+"("+getHandValue()+"points)");
        }
    }

    @Override
    public String toString() {
        return "Player "+getName();
    }
}
