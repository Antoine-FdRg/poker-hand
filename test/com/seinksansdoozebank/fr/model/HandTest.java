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
        hand = new Hand(new ArrayList<>(Arrays.asList("VCo","8Co","3Co","ACo","5Co")));
        hand2 = new Hand(new ArrayList<>(Arrays.asList("VCo","8Co","RCo","ACo","5Co")));
    }

    @Test
    void getBestCard() {
        assertEquals(Rank.ACE, hand.getBestCard().getRank());
        assertNotEquals(Rank.KING, hand2.getBestCard().getRank());
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