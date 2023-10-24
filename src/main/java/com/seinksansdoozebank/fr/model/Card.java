package com.seinksansdoozebank.fr.model;

public class Card implements Comparable<Card> {
    private final Rank rank;
    public Card(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }
    @Override
    public int compareTo(Card o) {
        //TODO test this
        return this.rank.compareTo(o.rank);
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                '}';
    }
}
