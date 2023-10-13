package com.seinksansdoozebank.fr.model;

public class Card {
    private Rank value;
    public Card(Rank value) {
        this.value = value;
    }

    public Rank getValue() {
        return value;
    }
}
