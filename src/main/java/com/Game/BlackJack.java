package com.Game;

import com.Card.Card;
import com.Card.CardValue;
import com.Card.Deck;
import com.Player.Dealer;
import com.Player.Person;
import com.Player.Player;

import java.util.Collections;
import java.util.Iterator;

/**
 * Created by woramet on 12/22/15.
 */
public class BlackJack {

    public BlackJack() {

    }

    public void dealTheInitialCard(Deck cardDeck, Player player, Dealer dealer) {
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());
        player.drawCard(cardDeck.drawCardFromTheTop());
        dealer.drawCard(cardDeck.drawCardFromTheTop());
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


    public void playerDoAction(Player player, Deck cardDeck, Action action) {
        switch (action) {
            case HIT:
                player.drawCard(cardDeck.drawCardFromTheTop());
                break;
            case STAND:
                player.setTurnEnded(true);
                break;
            case INSURANCE:
                player.setTurnEnded(true);
                break;
            case DOUBLE:
                player.drawCard(cardDeck.drawCardFromTheTop());
                player.setTurnEnded(true);
                break;
            case SURRENDER:
                player.setTurnEnded(true);
                break;
            case SPLIT:
                //split
                break;
            default:
                break;
        }
    }

    public boolean isPlayerDoValidAction(Player player, Dealer dealer, Action action) {
        boolean isValid;

        switch (action) {
            case HIT:
                isValid = canPlayerHit(player);
                break;
            case STAND:
                isValid = canPlayerStand(player);
                break;
            case INSURANCE:
                isValid = canPlayerTakeAnInsurance(player, dealer);
                break;
            case DOUBLE:
                isValid = canPlayerDouble(player);
                break;
            case SURRENDER:
                isValid = canPlayerSurrender(player);
                break;
            case SPLIT:
                isValid = canPlayerSplit(player);
                break;
            default:
                isValid = false;
                break;
        }

        return isValid;
    }

    public GameStatus updateGameStatus(Player player, Dealer dealer) {

        if (player.getHandValue() > 21) {
            //TODO: enum game state.
            return GameStatus.PLAYER_LOSE;
        }

        if (dealer.getHandValue() > 21) {
            return GameStatus.DEALER_LOSE;
        }

        return GameStatus.NONE;
    }

    public boolean is21(Person person) {
            return (person.getHandValue() == 21);
    }

    public boolean isBlackJack(Person person) {
        return  (person.getCardsInHand().size() == 2 && person.getHandValue() == 21);
    }
}
