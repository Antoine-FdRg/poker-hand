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
        this.cardEight = new Card(Rank.EIGHT, Suit.CLUB);
        this.cardThree = new Card(Rank.THREE, Suit.CLUB);
        this.cardThreeBis = new Card(Rank.THREE, Suit.CLUB);
        this.cardKing = new Card(Rank.KING, Suit.CLUB);
        this.cardQueen = new Card(Rank.QUEEN, Suit.CLUB);
        this.cardJack = new Card(Rank.JACK, Suit.CLUB);
        this.cardAce = new Card(Rank.ACE, Suit.CLUB);
        this.cardAceBis = new Card(Rank.ACE, Suit.CLUB);
    }

    @Test
    void compareTo() {
        assertTrue(cardEight.compareTo(cardThree) > 0);
        assertTrue(cardThree.compareTo(cardEight) < 0);
        assertEquals(0, cardThreeBis.compareTo(cardThree));
        assertTrue(cardKing.compareTo(cardAce) < 0);
        assertTrue(cardKing.compareTo(cardQueen) > 0);
        assertTrue(cardJack.compareTo(cardAce) < 0);
        assertTrue(cardJack.compareTo(cardEight) > 0);
        assertTrue(cardAce.compareTo(cardEight) > 0);
        assertTrue(cardThree.compareTo(cardQueen) < 0);
        assertEquals(0, cardAce.compareTo(cardAceBis));
    }

    @Test
    void testGetRank() {
        // Test getting the rank of a card
        Card card = new Card(Rank.TWO, Suit.HEART);
        assertEquals(Rank.TWO, card.getRank());
    }

    @Test
    void testGetSuit() {
        // Test getting the suit of a card
        Card card = new Card(Rank.TWO, Suit.HEART);
        assertEquals(Suit.HEART, card.getSuit());
    }

    @Test
    void testCompareToEqual() {
        // Test comparing two equal cards
        Card card1 = new Card(Rank.TWO, Suit.HEART);
        Card card2 = new Card(Rank.TWO, Suit.HEART);
        assertEquals(0, card1.compareTo(card2));
    }

    @Test
    void testCompareToDifferentRank() {
        // Test comparing two cards with different ranks
        Card card1 = new Card(Rank.TWO, Suit.HEART);
        Card card2 = new Card(Rank.FIVE, Suit.SPADE);
        assertNotEquals(0, card1.compareTo(card2));
        assertTrue(card1.compareTo(card2) < 0); // card1 < card2
    }

    @Test
    void testCompareToDifferentSuit() {
        // Test comparing two cards with different suits
        Card card1 = new Card(Rank.TWO, Suit.HEART);
        Card card2 = new Card(Rank.TWO, Suit.CLUB);
        assertEquals(0, card1.compareTo(card2));
        assertNotEquals(1, card1.compareTo(card2)); // card1 > card2
    }

    @Test
    void testEquals() {
        // Test equality of two equal cards
        Card card1 = new Card(Rank.TWO, Suit.HEART);
        Card card2 = new Card(Rank.TWO, Suit.HEART);
        assertEquals(card1, card2);
    }

    @Test
    void testNotEquals() {
        // Test inequality of two different cards
        Card card1 = new Card(Rank.TWO, Suit.HEART);
        Card card2 = new Card(Rank.FIVE, Suit.SPADE);
        assertNotEquals(card1, card2);
    }
}