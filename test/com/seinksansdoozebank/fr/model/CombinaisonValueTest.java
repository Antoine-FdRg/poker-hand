package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonValueTest {
    private CombinaisonValue cv1;
    private CombinaisonValue cv2;

    @BeforeEach
    void setUp() {
        cv1 = new CombinaisonValue(Combinaison.HIGHEST_CARD, new Card(Rank.JACK,Suit.CLUB));
        cv2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, new Card(Rank.FIVE,Suit.CLUB));
    }

    @Test
    void compareTo() {
        assertTrue(cv1.compareTo(cv2) > 0);
        assertTrue(cv2.compareTo(cv1) < 0);
        assertEquals(0, cv1.compareTo(cv1));
    }

    @Test
    void testToString() {
        assertEquals("carte la plus élevée : "+ cv1.getCards().getRank().getName(), cv1.toString());
        assertEquals("carte la plus élevée : "+ cv2.getCards().getRank().getName(), cv2.toString());
    }
}