package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(Arrays.asList("V","8","3","A","5")));
    }

    @Test
    void cardsAreSortded() {
        for (int i = 0; i < hand.getCards().size()-1; i++) {
            assertTrue(hand.getCards().get(i).getRank().compareTo(hand.getCards().get(i+1).getRank()) <= 0);
        }
    }

    @Test
    void getBestCard() {
        assertEquals(hand.getBestCard().getRank(), new Card(Rank.ACE,Suit.CLUB).getRank());
    }
}