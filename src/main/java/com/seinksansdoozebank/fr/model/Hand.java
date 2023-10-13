package com.seinksansdoozebank.fr.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final ArrayList<Card> cards;

    public Hand(List<Integer> cards) {
        this.cards = new ArrayList<>();
        for (Integer card : cards) {
            this.cards.add(new Card(Rank.getRank(card)));
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
