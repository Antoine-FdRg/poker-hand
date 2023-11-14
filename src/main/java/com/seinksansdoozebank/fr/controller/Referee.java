package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.*;

import java.util.*;

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
        Optional<List<Card>> response = this.searchStraightFlush(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, response.get());
        }
        response = this.searchNOfAKind(hand, 4);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.FOUR_OF_A_KIND, response.get());
        }
        response = this.searchFlush(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.FLUSH, response.get());
        }
        response = this.searchStraight(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.STRAIGHT, response.get());
        }
        response = this.searchNOfAKind(hand, 3);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.THREE_OF_A_KIND, response.get());
        }
        response = this.searchTwoPair(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.TWO_PAIR, response.get());
        }
        response = this.searchPair(hand);
        if (response.isPresent()) {
            return new CombinaisonValue(Combinaison.PAIR, response.get());
        }
        return new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand.getBestCard()));
    }

    /**
     * Search if the hand is a straight flush
     *
     * @param hand the hand
     * @return the list of cards if the hand is a straight flush, empty optional otherwise
     */
    protected Optional<List<Card>> searchStraightFlush(Hand hand) {
        Optional<List<Card>> response = this.searchFlush(hand);
        if (response.isPresent()) {
            response = this.searchStraight(hand);
            if (response.isPresent()) {
                return response;
            }
        }
        return Optional.empty();
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
        // if the index is equal to the cards size then the hand is a straight
        if (index == cardsSize) {
            return Optional.of(cards);
        }
        return Optional.empty();
    }

    /**
     * Search if the hand is a flush
     *
     * @param hand the hand
     * @return the list of cards if the hand is a flush, empty optional otherwise
     */
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

    /**
     * Search if the hand is a pair
     *
     * @param hand the hand
     * @return the list of cards if the hand is a pair, empty optional otherwise
     */
    protected Optional<List<Card>> searchPair(Hand hand) {
        List<Card> cardsFilteredByOccurence = CombinaisonValue.getCardsFilteredByOccurence(hand.getCards(), 2);

        if (cardsFilteredByOccurence.size() == 2) { // 2 because the map count card with differents suits as different cards
            // remove the card who's in the pair and sort the other card descending
            List<Card> list = new ArrayList<>(hand.getCards().stream()
                    .filter(card -> !card.equals(cardsFilteredByOccurence.get(0)))
                    .sorted(Collections.reverseOrder())
                    .toList());
            // add the card wich is in the pair at the beginning of the list
            list.add(0, cardsFilteredByOccurence.get(0));
            return Optional.of(list);
        }

        return Optional.empty();
    }

    protected Optional<List<Card>> searchTwoPair(Hand hand) {
        List<Card> cardsFilteredByOccurence = CombinaisonValue.getCardsFilteredByOccurence(hand.getCards(), 2)
                .stream()
                .sorted(Collections.reverseOrder())
                .toList();
        if (cardsFilteredByOccurence.size() == 4) { // 2 because the map count card with differents suits as different cards
            // remove the cards in the pairs and sort the other card descending
            List<Card> list = new ArrayList<>(hand.getCards().stream()
                    .filter(card -> !cardsFilteredByOccurence.contains(card))
                    .sorted(Collections.reverseOrder())
                    .toList());
            // add the card which is in the pair at the beginning of the list
            list.add(0, cardsFilteredByOccurence.get(0));
            list.add(1, cardsFilteredByOccurence.get(2));
            return Optional.of(list);
        }
        return Optional.empty();
    }

    /**
     * Search if the hand gots a N of a kind combination
     *
     * @param hand                     the hand
     * @param numberOfIdentiticalCards the number of identical cards that we search
     * @return an optional list with the card marked by the rank of a four of a kind combination
     */
    public Optional<List<Card>> searchNOfAKind(Hand hand, int numberOfIdentiticalCards) {
        Map<Rank, Integer> cardsCounter = new EnumMap<>(Rank.class);
        List<Card> cards = hand.getCards();
        for (Card card : cards) {
            if (cardsCounter.containsKey(card.getRank())) {
                cardsCounter.replace(card.getRank(), cardsCounter.get(card.getRank()) + 1);
            } else {
                cardsCounter.put(card.getRank(), 1);
            }
        }
        for (Map.Entry<Rank, Integer> entry : cardsCounter.entrySet()) {
            //If one key has as value numberOfIdenticalCards , we return the rank of the identical cards
            if (entry.getValue() == numberOfIdentiticalCards) {
                Card cardNOfAKind = new Card(entry.getKey(), Suit.HEART);
                return Optional.of(List.of(cardNOfAKind));
            }
        }
        return Optional.empty();

    }
}
