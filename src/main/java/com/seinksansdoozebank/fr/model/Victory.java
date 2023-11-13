package com.seinksansdoozebank.fr.model;

public class Victory {
    Hand hand;
    CombinaisonValue combinaisonValue;

    public Victory(Hand hand, CombinaisonValue combinaisonValue) {
        this.hand = hand;
        this.combinaisonValue = combinaisonValue;
    }

    public Hand getHand() {
        return hand;
    }

    public CombinaisonValue getCombinaisonValue() {
        return combinaisonValue;
    }

    @Override
    public String toString() {
        return "Victory with hand:" + hand +
                ", combinaisonValue=" + combinaisonValue;
    }
}
