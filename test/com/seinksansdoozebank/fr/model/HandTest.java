package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(Arrays.asList("V","8","3","A","5")));
    }

    @Test
    void cardsAreSortded() {
        assertFalse(hand.getCards().get(0).compareTo(hand.getCards().get(1)) < 0);

        List<Card> sortedCards = hand.getSortedCards();
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            assertTrue(sortedCards.get(i).compareTo(sortedCards.get(i + 1)) < 0);
        }
    }

    @Test
    void getBestCard() {
        assertEquals(hand.getBestCard().getRank(), new Card(Rank.ACE).getRank());
    }
}