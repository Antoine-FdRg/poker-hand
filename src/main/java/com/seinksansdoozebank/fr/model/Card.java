package com.seinksansdoozebank.fr.model;

public class Card implements Comparable<Card> {
    private final Rank rank;

    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
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
        return this.rank + " de " + this.suit;
    }
}
