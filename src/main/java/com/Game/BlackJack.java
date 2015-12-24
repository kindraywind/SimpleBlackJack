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
        if (isImpossibleHand(player)) return false;

        return (player.getHandValue() <= 20) && (player.getCardsInHand().size() >= 2) && !player.isTurnEnded();
    }

    public boolean canPlayerStand(Player player) {
        if (isImpossibleHand(player)) return false;

        return !player.isTurnEnded() && (player.getCardsInHand().size() >= 2) ;
    }

    public boolean canPlayerTakeAnInsurance(Player player, Dealer dealer) {
        if (isImpossibleHand(player)) return false;

        return  (dealer.getCardsInHand().iterator().next().getValue() == CardValue.ACE) && !player.isTurnEnded();
    }

    public boolean canPlayerDouble(Player player) {
        if (isImpossibleHand(player)) return false;

        return  (player.getCardsInHand().size() == 2) && !player.isTurnEnded();

    }

    public boolean canPlayerSurrender(Player player) {
        if (isImpossibleHand(player)) return false;

        return (player.getCardsInHand().size() == 2) && (player.getHandValue() <=16)  && !player.isTurnEnded();

    }

    public boolean canPlayerSplit(Player player) {
        if (isImpossibleHand(player)) return false;

        Iterator<Card> playerHand = player.getCardsInHand().iterator();
        Card first = playerHand.next();
        Card second = playerHand.next();

        return  (player.getCardsInHand().size() == 2 && first.getIntValue() == second.getIntValue())
                && !player.isTurnEnded();
    }


    public GameStatus playerDoAction(Player player, Deck cardDeck, Action action) {

        GameStatus status = GameStatus.NONE;

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
                status = GameStatus.PLAYER_LOSE;
                break;
            case SPLIT:
                //split
                break;
            default:
                status = GameStatus.NONE;
                break;
        }
        return status;
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

        if (isBusts(player)) {
            //TODO: enum game state.
            return GameStatus.PLAYER_LOSE;
        }

        if (isBusts(dealer)) {
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

    public boolean isBusts(Person person) {
        return person.getHandValue() > 21;
    }

    public boolean isShouldEnd(Person person) {
        if (isImpossibleHand(person)) return false;

        return (is21(person) || isBlackJack(person) || isBusts(person));
    }

    public boolean isImpossibleHand(Person person) {
        //minimal version.
        return (person.getCardsInHand().size() <=4 && person.getHandValue() < person.getCardsInHand().size()*2) ||
                person.getCardsInHand().size() == 2 && person.getHandValue() > 21 ||
                person.getHandValue() >30 ||
                person.getCardsInHand().size() < 2 ||
                person.getCardsInHand().size() > 10 && person.getHandValue() < 21 ||
                person.getCardsInHand().size() > 11 && person.getHandValue() >= 21
                ;
    }
}
