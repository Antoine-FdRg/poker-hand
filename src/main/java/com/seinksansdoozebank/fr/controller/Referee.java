package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;

public class Referee {
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

    private CombinaisonValue getBestCombinaison(Hand hand) {
        //TODO test this
        return new CombinaisonValue(Combinaison.HIGHEST_CARD, hand.getBestCard());
    }
}
