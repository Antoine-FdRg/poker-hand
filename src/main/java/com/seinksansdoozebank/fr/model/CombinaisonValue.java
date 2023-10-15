package com.seinksansdoozebank.fr.model;

public class CombinaisonValue {
    Combinaison combinaison;
    Card cards;

    public CombinaisonValue(Combinaison combinaison, Card cards) {
        this.combinaison = combinaison;
        this.cards = cards;
    }

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

    @Override
    public String toString() {
        return "CombinaisonValue{" +
                "combinaison=" + combinaison +
                ", cards=" + cards +
                '}';
    }
}
