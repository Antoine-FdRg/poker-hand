package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        Hand.resetIdCounter();
        hand = new Hand(new ArrayList<>(Arrays.asList("VCo", "8Ca", "3Tr", "APi", "5Ca")));
    }

    @Test
    void testGetID() {
        // Test getting the ID of the hand
        assertEquals(1, hand.getID());
    }

    @Test
    void testGetCards() {
        // Test getting the cards in the hand
        List<Card> cards = hand.getCards();
        assertEquals(5, cards.size());
    }

    @Test
    void cardsAreSortded() {
        for (int i = 0; i < hand.getCards().size() - 1; i++) {
            assertTrue(hand.getCards().get(i).getRank().compareTo(hand.getCards().get(i + 1).getRank()) <= 0);
        }
    }

    @Test
    void testGetBestCard() {
        // Test getting the best card in the hand
        Card bestCard = hand.getBestCard();
        assertEquals(Rank.ACE, bestCard.getRank());
    }

    @Test
    void testResetIdCounter() {
        // Test resetting the ID counter for hands
        Hand.resetIdCounter();
        List<String> newSampleCards = new ArrayList<>();
        newSampleCards.add("2Co");
        newSampleCards.add("3Ca");

        Hand newHand = new Hand(newSampleCards);
        assertEquals(1, newHand.getID()); // Should start counting from 1 again
    }
}