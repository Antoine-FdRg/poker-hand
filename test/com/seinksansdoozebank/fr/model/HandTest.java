package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;
    private Hand hand2;

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(Arrays.asList("V","8","3","A","5")));
        hand2 = new Hand(new ArrayList<>(Arrays.asList("V","8","R","A","5")));
    }

    @Test
    void cardsAreSortded() {
        List<Card> sortedCards = hand.getCards();
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            assertTrue(sortedCards.get(i).compareTo(sortedCards.get(i + 1)) < 0);
        }
    }

    @Test
    void getBestCard() {
        assertEquals(hand.getBestCard().getRank(), new Card(Rank.ACE).getRank());
        assertNotEquals(hand2.getBestCard().getRank(), new Card(Rank.KING).getRank());
    }
}