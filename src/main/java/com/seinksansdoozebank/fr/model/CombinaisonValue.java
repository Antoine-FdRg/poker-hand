package com.seinksansdoozebank.fr.model;

import java.util.List;

public class CombinaisonValue {
    private final Combinaison combinaison;
    private final List<Card> cards;

    public CombinaisonValue(Combinaison combinaison, List<Card> cards) {
        this.combinaison = combinaison;
        this.cards = cards;
    }

    /**
     * Compare two combinaison values
     *
     * @param combinaison2 the combinaison value to compare
     * @return 1 if the combinaison value is greater, -1 if the combinaison value is lower, 0 if they are equals
     */
    public int compareTo(CombinaisonValue combinaison2) {
        int result = this.combinaison.compareTo(combinaison2.getCombinaison());
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return this.getBestCard().compareTo(combinaison2.getBestCard());
        }
    }

    /**
     * Merci d'ajouter les conditions de victoire suivantes au fur et à mesure de leur implémentation :
     * "La main 1 gagne avec carte la plus élevée : As"
     * "La main 2 gagne avec paire de 5"
     * "La main 1 gagne avec double paire : As et 5"
     * "La main 2 gagne avec brelan de 5"
     * "La main 1 gagne avec suite"
     * "La main 2 gagne avec couleur"
     * "La main 1 gagne avec full : As par les 5"
     * "La main 2 gagne avec carré de 5"
     * "La main 1 gagne avec quinte flush"
     *
     * @return the victory condition string that must be printed
     */
    @Override
    public String toString() {
        StringBuilder victoryCondition = new StringBuilder();
        switch (this.combinaison) {
            case HIGHEST_CARD:
                victoryCondition.append("carte la plus élevée : ").append(this.getBestCard().getRank().getName());
                break;
            case STRAIGHT:
                int size = this.cards.size();
                if (this.cards.get(size - 1).getRank().equals(Rank.ACE)) {
                    victoryCondition.append("Quinte Broadway");
                } else if (this.cards.get(0).getRank().equals(Rank.ACE)) {
                    victoryCondition.append("Quinte à l'As");
                } else if (this.cards.get(size - 1).getRank().equals(Rank.FIVE)) {
                    victoryCondition.append("Quinte à 5");
                } else {
                    victoryCondition.append("Quinte de ").append(this.cards.get(size - 1).getRank().getName());
                }
                break;
            default:
                victoryCondition.append(this.combinaison.getName()).append(" : ").append(this.getBestCard().getRank().getName());
                break;
        }
        return victoryCondition.toString();
    }

    /**
     * Get the string representation of the straight
     * The straight is represented by the cards symbols separated by a space
     *
     * @return the string representation of the straight
     */
    String toStringStraight() {
        List<Card> cards = this.getCards();
        StringBuilder stringBuilder = new StringBuilder();
        if (cards.contains(new Card(Rank.ACE, Suit.CLUB)) || cards.contains(new Card(Rank.ACE, Suit.DIAMOND)) || cards.contains(new Card(Rank.ACE, Suit.HEART)) || cards.contains(new Card(Rank.ACE, Suit.SPADE)))
        {
            stringBuilder.append("A ");
        }
        // if there is a ACE in the hand, the ACE is set to the first card
        for (Card card : cards) {
            if (card.getRank().equals(Rank.ACE)) {
                continue;
            }
            stringBuilder.append(card.getRank().getSymbol()).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Get the best card of the combinaison
     *
     * @return the best card of the combinaison
     */
    public Card getBestCard() {
        // if the combinaison is a straight so the best card is the last card of the hand.
        // And if there is an ACE in the hand, the ACE is NOT the best card.
        if (this.combinaison.equals(Combinaison.STRAIGHT)) {
            List<Card> cards = this.getCards();
            if (cards.contains(new Card(Rank.ACE, Suit.CLUB)) || cards.contains(new Card(Rank.ACE, Suit.DIAMOND)) || cards.contains(new Card(Rank.ACE, Suit.HEART)) || cards.contains(new Card(Rank.ACE, Suit.SPADE)))
            {
                return cards.get(cards.size() - 2);
            }
            return this.getCards().get(this.getCards().size() - 1);
        } return this.getBestCard();
    }

    /**
     * Get the cards of the combinaison
     *
     * @return the cards of the combinaison
     */
    public List<Card> getCards() {
        return this.cards;
    }

    /**
     * Get the combinaison
     *
     * @return the combinaison
     */
    public Combinaison getCombinaison() {
        return this.combinaison;
    }
}
