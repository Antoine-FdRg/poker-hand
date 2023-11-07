package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Optional<List<Card>> response = this.searchStraight(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.STRAIGHT, response.get());
        }
        response=this.searchPair(hand);
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
        List<Card> cards = hand.getCards();
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
        // if the index is equal to the cards size then the hand is a straight
        if (index == cardsSize) {
            return Optional.of(cards);
        }
        return Optional.empty();
    }

    /**
     * Search if the hand is a pair
     *
     * @param hand the hand
     * @return the list of cards if the hand is a pair, empty optional otherwise
     */
    protected Optional<List<Card>> searchPair(Hand hand) {
        Map<Card, Integer> map = hand.getCards().stream()
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> Collections.frequency(hand.getCards(), v))
                );
        List<Card> cards = map.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .toList();

        if (cards.size() == 1){
            // remove the card who's in the pair and sort the other card descending
            List<Card> list = new java.util.ArrayList<>(hand.getCards().stream()
                    .filter(card -> !card.equals(cards.get(0)))
                    .sorted(Collections.reverseOrder())
                    .toList());
            // add the card who's in the pair at the beginning of the list
            list.add(0, cards.get(0));
            return Optional.of(list);
        }

        return Optional.empty();
    }
}
