package Game;

import Card.Card;
import Card.CardValue;
import Player.*;

import java.util.Iterator;

/**
 * Created by woramet on 12/22/15.
 */
public class BlackJack {

    public BlackJack() {

    }

    public boolean canPlayerHit(Player player) {
        return (player.getHandValue() != 21) && !player.isTurnEnded();
    }

    public boolean canPlayerStand(Player player) {
        return !player.isTurnEnded();
    }

    public boolean canPlayerTakeAnInsurance(Player player, Dealer dealer) {
        return  (dealer.getCardsInHand().iterator().next().getValue() == CardValue.ACE) && !player.isTurnEnded();
    }

    public boolean canPlayerDouble(Player player) {
        return  (player.getCardsInHand().size() == 2) && !player.isTurnEnded();

    }

    public boolean canPlayerSurrender(Player player) {
        return  (player.getCardsInHand().size() == 2) && !player.isTurnEnded();

    }

    public boolean canPlayerSplit(Player player) {
        Iterator<Card> playerHand = player.getCardsInHand().iterator();
        Card first = playerHand.next();
        Card second = playerHand.next();

        return  (player.getCardsInHand().size() == 2 && first.getValue().getCardValue() == second.getValue().getCardValue())
                && !player.isTurnEnded();
    }

}
