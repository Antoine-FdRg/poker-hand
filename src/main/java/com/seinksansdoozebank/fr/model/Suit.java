package com.seinksansdoozebank.fr.model;

public enum Suit implements Comparable<Suit> {
    HEART("Co","Coeur"),
    SPADE("Pi","Pique"),
    DIAMOND("Ca","Carreau"),
    CLUB("Tr","Trèfle");
    private final String symbol;
    private final String name;


    Suit(String symbol,String name) {
        this.symbol = symbol;
        this.name = name;
    }

    /**
     * Get the suit from the symbol
     *
     * @param symbol the symbol of the suit
     * @return the suit
     */
    public static Suit getSuitFromSymbol(String symbol) {
        for (Suit suit : Suit.values()) {
            if (symbol.contains(suit.getSymbol())) {
                return suit;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
