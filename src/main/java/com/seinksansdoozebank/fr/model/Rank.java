package com.seinksansdoozebank.fr.model;

public enum Rank implements Comparable<Rank> {
    TWO("2", "2"),
    THREE("3", "3"),
    FOUR("4", "4"),
    FIVE("5", "5"),
    SIX("6", "6"),
    SEVEN("7", "7"),
    EIGHT("8", "8"),
    NINE("9", "9"),
    TEN("10", "10"),
    JACK("V", "Valet"),
    QUEEN("D", "Dame"),
    KING("R", "Roi"),
    ACE("A", "As");

    private final String symbol;
    private final String name;

    Rank(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    /**
     * Get the rank from the symbol
     *
     * @param symbol the symbol of the rank
     * @return the rank
     */
    public static Rank getRankFromSymbol(String symbol) {
        for (Rank rank : Rank.values()) {
            if (rank.getSymbol().equals(symbol)) {
                return rank;
            }
        }
        return null;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

}