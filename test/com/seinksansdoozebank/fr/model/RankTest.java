package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    void testGetRankFromSymbolValid() {
        // Test getting a rank from a valid symbol
        assertEquals(Rank.TWO, Rank.getRankFromSymbol("2Co"));
        assertEquals(Rank.THREE, Rank.getRankFromSymbol("3Ca"));
        assertEquals(Rank.FOUR, Rank.getRankFromSymbol("4Tr"));
        assertEquals(Rank.FIVE, Rank.getRankFromSymbol("5Pi"));
        assertEquals(Rank.SIX, Rank.getRankFromSymbol("6Co"));
        assertEquals(Rank.JACK, Rank.getRankFromSymbol("VCo"));
        assertEquals(Rank.QUEEN, Rank.getRankFromSymbol("DCa"));
        assertEquals(Rank.KING, Rank.getRankFromSymbol("RTr"));
        assertEquals(Rank.ACE, Rank.getRankFromSymbol("APi"));
    }

    @Test
    void testGetRankFromSymbolInvalid() {
        // Test getting a rank from an invalid symbol
        assertNull(Rank.getRankFromSymbol("Invalid"));
        assertNull(Rank.getRankFromSymbol("Xx"));
        assertNull(Rank.getRankFromSymbol("123"));
        assertNull(Rank.getRankFromSymbol(""));
    }

    @Test
    void testGetSymbol() {
        // Test getting the symbol of each rank
        assertEquals("2", Rank.TWO.getSymbol());
        assertEquals("3", Rank.THREE.getSymbol());
        assertEquals("4", Rank.FOUR.getSymbol());
        assertEquals("5", Rank.FIVE.getSymbol());
        assertEquals("6", Rank.SIX.getSymbol());
        assertEquals("V", Rank.JACK.getSymbol());
        assertEquals("D", Rank.QUEEN.getSymbol());
        assertEquals("R", Rank.KING.getSymbol());
        assertEquals("A", Rank.ACE.getSymbol());
    }


    @Test
    void testGetName() {
        // Test getting the name of each rank
        assertEquals("2", Rank.TWO.getName());
        assertEquals("3", Rank.THREE.getName());
        assertEquals("4", Rank.FOUR.getName());
        assertEquals("5", Rank.FIVE.getName());
        assertEquals("6", Rank.SIX.getName());
        assertEquals("Valet", Rank.JACK.getName());
        assertEquals("Dame", Rank.QUEEN.getName());
        assertEquals("Roi", Rank.KING.getName());
        assertEquals("As", Rank.ACE.getName());
    }
}