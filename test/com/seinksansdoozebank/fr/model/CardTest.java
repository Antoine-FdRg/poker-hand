package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card cardEight;
    private Card cardThree;
    private Card cardThreeBis;
    private Card cardKing;
    private Card cardQueen;
    private Card cardJack;
    private Card cardAce;
    private Card cardAceBis;

    @BeforeEach
    void setUp() {
        this.cardEight = new Card(Rank.EIGHT);
        this.cardThree = new Card(Rank.THREE);
        this.cardThreeBis = new Card(Rank.THREE);
        this.cardKing = new Card(Rank.KING);
        this.cardQueen = new Card(Rank.QUEEN);
        this.cardJack = new Card(Rank.JACK);
        this.cardAce = new Card(Rank.ACE);
        this.cardAceBis = new Card(Rank.ACE);
    }

    @Test
    void compareTo() {
        assertTrue(cardEight.compareTo(cardThree)>0);
        assertTrue(cardThree.compareTo(cardEight)<0);
        assertEquals(0, cardThreeBis.compareTo(cardThree));
        assertTrue(cardKing.compareTo(cardAce)<0);
        assertTrue(cardKing.compareTo(cardQueen)>0);
        assertTrue(cardJack.compareTo(cardAce)<0);
        assertTrue(cardJack.compareTo(cardEight)>0);
        assertTrue(cardAce.compareTo(cardEight)>0);
        assertTrue(cardThree.compareTo(cardQueen)<0);
        assertEquals(0, cardAce.compareTo(cardAceBis));
    }
}