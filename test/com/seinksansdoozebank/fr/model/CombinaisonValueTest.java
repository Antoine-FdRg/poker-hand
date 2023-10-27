package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonValueTest {
    private CombinaisonValue testBestCardJack;
    private CombinaisonValue testBestCardFive;

    @BeforeEach
    void setUp() {
        Hand hand1 = new Hand(List.of("V"));
        Hand hand2 = new Hand(List.of("5"));
        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        testBestCardFive = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2);
    }

    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardFive) > 0);
        assertTrue(testBestCardFive.compareTo(testBestCardJack) < 0);
        assertEquals(0, testBestCardJack.compareTo(testBestCardJack));
    }

    @Test
    void testToString() {
        assertEquals("carte la plus élevée : "+ testBestCardJack.getBestCard().getRank().getName(), testBestCardJack.toString());
        assertEquals("carte la plus élevée : "+ testBestCardFive.getBestCard().getRank().getName(), testBestCardFive.toString());
    }
}