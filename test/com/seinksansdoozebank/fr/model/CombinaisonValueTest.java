package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinaisonValueTest {
    private CombinaisonValue testBestCardJack;
    private CombinaisonValue testBestCardSix;

    @BeforeEach
    void setUp() {
        Hand hand1 = new Hand(List.of("V", "2", "3", "4", "5"));
        Hand hand2 = new Hand(List.of("5", "2", "3", "4", "6"));
        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand1.getBestCard()));
        testBestCardSix = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand2.getBestCard()));
    }

    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardSix) > 0);
        assertTrue(testBestCardSix.compareTo(testBestCardJack) < 0);
        assertEquals(0, testBestCardJack.compareTo(testBestCardJack));
    }

    @Test
    void testToString() {
        assertEquals("carte la plus élevée : " + testBestCardJack.getBestCard().getRank().getName(), testBestCardJack.toString());
        assertEquals("carte la plus élevée : " + testBestCardSix.getBestCard().getRank().getName(), testBestCardSix.toString());
    }
}