package com.seinksansdoozebank.fr.model;

public enum Rank implements Comparable<Rank> {
    TWO("2", 2, "2"),
    THREE("3", 3, "3"),
    FOUR("4", 4, "4"),
    FIVE("5", 5, "5"),
    SIX("6", 6, "6"),
    SEVEN("7", 7, "7"),
    EIGHT("8", 8, "8"),
    NINE("9", 9, "9"),
    TEN("10", 10, "10"),
    JACK("V", 11, "Valet"),
    QUEEN("D", 12, "Dame"),
    KING("R", 13, "Roi"),
    ACE("A", 14, "As");

    private final String symbol;
    private final int value;
    private final String name;

    Rank(String symbol, int value, String name) {
        this.symbol = symbol;
        this.value = value;
        this.name = name;
    }

    /**
     * Get the rank from the value
     *
     * @param value the value of the rank
     * @return the rank
     */
    public static Rank getRankFromValue(int value) {
        //TODO test this
        for (Rank rank : Rank.values()) {
            if (rank.getValue() == value) {
                return rank;
            }
        }
        return null;
    }

    /**
     * Get the rank from the symbol
     *
     * @param symbol the symbol of the rank
     * @return the rank
     */
    public static Rank getRankFromSymbol(String symbol) {
        //TODO test this
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

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
