package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test
    void getRankFromSymbol() {
        assertEquals(Rank.ACE, Rank.getRankFromSymbol("A"));
        assertEquals(Rank.TEN, Rank.getRankFromSymbol("10"));
        assertEquals(Rank.FOUR, Rank.getRankFromSymbol("4"));
    }
}