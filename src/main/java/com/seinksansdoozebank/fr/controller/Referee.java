package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.*;

import java.util.List;

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
        if(this.searchThreeOfAKind(hand)){
            return new CombinaisonValue(Combinaison.THREE_OF_A_KIND,hand);
        }
        if (hand.getCards().size() != 1 && this.isStraight(hand)) {
            return new CombinaisonValue(Combinaison.STRAIGHT, hand);
        }

        return new CombinaisonValue(Combinaison.HIGHEST_CARD, hand);
    }

    /**
     * Check if the hand is a straight
     *
     * @param hand the hand
     * @return true if the hand is a straight, false otherwise
     */
    private boolean isStraight(Hand hand) {
        List<Card> cards = hand.getSortedCards();
        // if the last card is an ACE put it at first
        if (cards.get(cards.size() - 1).getRank().equals(Rank.ACE)) {
            cards.add(0, cards.remove(cards.size() - 1));
        }
        int cardsSize = cards.size();
        Card previousCard = cards.get(0);
        int index = 1;
        // if the index is less than the size of the list and the previous card is the precedent value of the current card
        while (index < cardsSize &&
                (previousCard.compareTo(cards.get(index)) == -1) ||
                (previousCard.getRank().equals(Rank.ACE) && cards.get(index).getRank().equals(Rank.TWO))) {
            previousCard = cards.get(index);
            index++;
        }
        // if the index is equal to the cards size so the hand is a straight
        return index == cardsSize;
    }

    private boolean searchThreeOfAKind(Hand hand){
        int compteur=0;
        for ( Card card: hand.getCards()){
            if( compteur==3 ){ return true ; }
            else { compteur = 0; }
            for(Card card1: hand.getCards()){
                if(card1.getRank().equals(card.getRank())){ compteur+=1;}
            }
        }
        return false;
    }
}
