package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {

    private Referee referee;

    @BeforeEach
    public void setUp() {
        referee = new Referee();
    }

    @Test
    void compareHandsTest() {
        Hand hand1 = new Hand(new ArrayList<>(List.of("A")), 1);
        Hand hand2 = new Hand(new ArrayList<>(List.of("A")), 2);

        Victory victory = referee.compareHands(hand1, hand2);
        assertNull(victory);

        hand1 = new Hand(new ArrayList<>(List.of("A")), 1);
        hand2 = new Hand(new ArrayList<>(List.of("2")), 2);

        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);

        hand1 = new Hand(new ArrayList<>(List.of("2")), 1);
        hand2 = new Hand(new ArrayList<>(List.of("A")), 2);

        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);
    }
}
