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
        return this.rank.compareTo(o.rank);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card card) {
            return this.rank.equals(card.rank);
        }
        return false;
    }

    public int hashCode() {
        return this.rank.hashCode();
    }

    @Override
    public String toString() {
        return rank.getName();
    }
}
