package com.seinksansdoozebank.fr.model;

public class CombinaisonValue {
    private final Combinaison combinaison;
    private final Card cards;

    public CombinaisonValue(Combinaison combinaison, Card cards) {
        this.combinaison = combinaison;
        this.cards = cards;
    }

    /**
     * Compare two combinaison values
     * @param combinaison2 the combinaison value to compare
     * @return 1 if the combinaison value is greater, -1 if the combinaison value is lower, 0 if they are equals
     */
    public int compareTo(CombinaisonValue combinaison2) {
        int result = this.combinaison.compareTo(combinaison2.combinaison);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return this.cards.compareTo(combinaison2.cards);
        }
    }

    public Combinaison getCombinaison() {
        return combinaison;
    }

    public Card getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "CombinaisonValue{" +
                "combinaison=" + combinaison +
                ", cards=" + cards +
                '}';
    }
}
