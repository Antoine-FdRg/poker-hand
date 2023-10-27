package com.seinksansdoozebank.fr.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hand {
    private static int idCounter = 1;

    private final ArrayList<Card> cards;
    private final int id;

    public Hand(List<String> cards) {
        this.id = idCounter++;
        this.cards = new ArrayList<>();
        for (String card : cards) {
            this.cards.add(new Card(Rank.getRankFromSymbol(card)));
        }
        //sorting cards
        // this.cards.sort(Comparator.comparing(Card::getRank));
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getID() {
        return id;
    }

    public Card getBestCard() {
        // return cards.get(cards.size() - 1);
        return this.getSortedCards().get(cards.size() - 1);
    }

    public List<Card> getSortedCards() {
        List<Card> sortedCards = new ArrayList<>(cards);
        sortedCards.sort(Comparator.comparing(Card::getRank));
        return sortedCards;
    }

    public static void resetIdCounter(){
        idCounter = 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card.getRank().getSymbol()).append(" ");
        }
        return stringBuilder.toString();
    }
}
