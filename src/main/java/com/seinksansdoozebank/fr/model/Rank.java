package com.seinksansdoozebank.fr.model;

public enum Rank {
    AS("A", 14),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    VALET("V", 11),
    QUEEN ("D",12),
    KING("R",13);

    private String symbol;
    private int value;
    Rank(String symbol, int value) {
        this.symbol=symbol;
        this.value=value;
    }

    public static Rank getRank(Integer card) {
        //FIXME because its not that cool
        return Rank.values()[card-1];
    }


    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }
}
