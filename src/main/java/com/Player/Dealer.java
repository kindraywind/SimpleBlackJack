package com.Player;

/**
 * Created by woramet on 12/20/15.
 */
public class Dealer extends Person {

    public Dealer(String name) {
        super(name);
    }

    public void printDealerStatusWithFoldedCard() {
        System.out.println(this+"'s hand: "+getCardsInHand().iterator().next()+", *hole*");
    }

    public void printDealerStatus() {
        if (getHandValue() > 21) {
            System.out.println(this+"'s hand: "+getCardsInHand()+"("+getHandValue()+"points) *BUSTS!");
        } else {
            System.out.println(this+"'s hand: "+getCardsInHand()+"("+getHandValue()+"points)");
        }
    }

    @Override
    public String toString() {
        return "Dealer "+getName();
    }
}
