package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.Rank;
import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Referee {

    /**
     * Compare two hands and return the winner
     *
     * @param hand1 the first hand
     * @param hand2 the second hand
     * @return the winner
     */
    public Victory compareHands(Hand hand1, Hand hand2) {
        CombinaisonValue combinaison1 = getBestCombinaison(hand1);
        CombinaisonValue combinaison2 = getBestCombinaison(hand2);
        int result = combinaison1.compareTo(combinaison2);
        if (result > 0) {
            return new Victory(hand1, combinaison1);
        } else if (result < 0) {
            return new Victory(hand2, combinaison2);
        } else {
            return null;
        }

    }

    /**
     * Get the best combinaison of a hand
     *
     * @param hand the hand
     * @return the best combinaison
     */
    protected CombinaisonValue getBestCombinaison(Hand hand) {
        Optional<List<Card>> response = this.searchFlush(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.FLUSH, response.get());
        }
        response = this.searchStraight(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.STRAIGHT, response.get());
        }
        return new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand.getBestCard()));
    }

    /**
     * Search if the hand is a straight
     *
     * @param hand the hand
     * @return the list of cards if the hand is a straight, empty optional otherwise
     */
    public Optional<List<Card>> searchStraight(Hand hand) {
        // Sorted list of cards
        // Get a copy of the list of cards
        List<Card> cards = new ArrayList<>(List.copyOf(hand.getCards()));
        cards.sort(Card::compareTo);
        // if the first Card is a TWO and the last one is an ACE put the ACE at the beginning of the list
        if (cards.get(0).getRank().equals(Rank.TWO) && cards.get(cards.size() - 1).getRank().equals(Rank.ACE)) {
            cards.add(0, cards.remove(cards.size() - 1));
        }
        int cardsSize = cards.size();
        Card previousCard = cards.get(0);
        int index = 1;
        // while the index is inferior to the cards size and
        // the previous card is inferior to the current card or
        // the previous card is an ACE and the current card is a TWO or
        // the previous card is a KING and the current card is an ACE
        while (index < cardsSize && (previousCard.compareTo(cards.get(index)) == -1) || index < cardsSize && ((previousCard.getRank().equals(Rank.ACE) && cards.get(index).getRank().equals(Rank.TWO)))) {
            previousCard = cards.get(index);
            index++;
        }
        // if the index is equal to the cards size so the hand is a straight
        if (index == cardsSize) {
            return Optional.of(cards);
        }
        return Optional.empty();
    }

    public Optional<List<Card>> searchFlush(Hand hand) {
        List<Card> cards = hand.getCards();
        int cardsSize = cards.size();
        Card previousCard = cards.get(0);
        int index = 1;
        while (index < cardsSize && previousCard.getSuit().equals(cards.get(index).getSuit())) {
            previousCard = cards.get(index);
            index++;
        }
        if (index == cardsSize) {
            return Optional.of(cards);
        }
        return Optional.empty();
    }
}
