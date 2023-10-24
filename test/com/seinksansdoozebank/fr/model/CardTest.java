package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card card1;
    private Card card2;
    private Card card3;

    @BeforeEach
    void setUp() {
        this.card1 = new Card(Rank.EIGHT);
        this.card2 = new Card(Rank.THREE);
        this.card3 = new Card(Rank.THREE);
    }

    @Test
    void compareTo() {
        assertTrue(card1.compareTo(card2)>0);
        assertTrue(card2.compareTo(card1)<0);
        assertEquals(0,card3.compareTo(card2));
    }
}