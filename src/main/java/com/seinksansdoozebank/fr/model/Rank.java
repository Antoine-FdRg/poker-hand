package com.seinksansdoozebank.fr.model;

public enum Rank implements Comparable<Rank> {
    TWO("2", 2,"2"),
    THREE("3", 3,"3"),
    FOUR("4", 4,"4"),
    FIVE("5", 5,"5"),
    SIX("6", 6,"6"),
    SEVEN("7", 7,"7"),
    EIGHT("8", 8,"8"),
    NINE("9", 9,"9"),
    TEN("10", 10,"10"),
    VALET("V", 11,"Valet"),
    QUEEN("D", 12,"Dame"),
    KING("R", 13,"Roi"),
    AS("A", 14,"As");

    private final String symbol;
    private final int value;
    private final String fullName;

    Rank(String symbol, int value, String fullName) {
        this.symbol = symbol;
        this.value = value;
        this.fullName = fullName;
    }

    public static Rank getRankBySymbol(String card) {
        switch (card) {
            case "2":
                return TWO;
            case "3":
                return THREE;
            case "4":
                return FOUR;
            case "5":
                return FIVE;
            case "6":
                return SIX;
            case "7":
                return SEVEN;
            case "8":
                return EIGHT;
            case "9":
                return NINE;
            case "10":
                return TEN;
            case "V":
                return VALET;
            case "D":
                return QUEEN;
            case "R":
                return KING;
            case "A":
                return AS;
            default:
                return null;
        }

    }

    @Override
    public String toString() {
        return fullName;
    }
}
