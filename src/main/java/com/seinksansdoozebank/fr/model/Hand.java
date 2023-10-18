package com.seinksansdoozebank.fr.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hand {

    private final ArrayList<Card> cards;
    private final int id;

    public Hand(List<String> cards, int id) {
        this.id = id;
        //TODO make id automaticaly managed by the Hand class
        this.cards = new ArrayList<>();
        for (String card : cards) {
            this.cards.add(new Card(Rank.getRankFromSymbol(card)));
        }
        //sorting cards
        this.cards.sort(Comparator.comparing(Card::getRank));
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getID() {
        return id;
    }

    public Card getBestCard() {
        return cards.get(cards.size() - 1);
    }
}
