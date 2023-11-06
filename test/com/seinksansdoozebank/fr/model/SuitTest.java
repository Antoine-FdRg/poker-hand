package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SuitTest {

    @Test
    void testGetSuitFromSymbolValid() {
        // Test getting a suit from a valid symbol
        assertEquals(Suit.HEART, Suit.getSuitFromSymbol("Co"));
        assertEquals(Suit.SPADE, Suit.getSuitFromSymbol("Pi"));
        assertEquals(Suit.DIAMOND, Suit.getSuitFromSymbol("Ca"));
        assertEquals(Suit.CLUB, Suit.getSuitFromSymbol("Tr"));
    }

    @Test
    void testGetSuitFromSymbolInvalid() {
        // Test getting a suit from an invalid symbol
        assertNull(Suit.getSuitFromSymbol("Invalid"));
        assertNull(Suit.getSuitFromSymbol("Xx"));
        assertNull(Suit.getSuitFromSymbol("123"));
        assertNull(Suit.getSuitFromSymbol(""));
    }

    @Test
    void testGetName() {
        // Test getting the name of each suit
        assertEquals("Coeur", Suit.HEART.getName());
        assertEquals("Pique", Suit.SPADE.getName());
        assertEquals("Carreau", Suit.DIAMOND.getName());
        assertEquals("Trèfle", Suit.CLUB.getName());
    }

    @Test
    void testGetSymbol() {
        // Test getting the symbol of each suit
        assertEquals("Co", Suit.HEART.getSymbol());
        assertEquals("Pi", Suit.SPADE.getSymbol());
        assertEquals("Ca", Suit.DIAMOND.getSymbol());
        assertEquals("Tr", Suit.CLUB.getSymbol());
    }
}
