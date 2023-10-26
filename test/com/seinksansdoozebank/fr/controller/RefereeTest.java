package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {

    private Referee referee;
    private Hand hand1;
    private Hand hand2;
    private Victory victory;

    @BeforeEach
    public void setUp() {
        referee = new Referee();
        Hand.resetIdCounter();
    }

    @Test
    void compareHandsDraw() {
        hand1 = new Hand(new ArrayList<>(List.of("A")));
        hand2 = new Hand(new ArrayList<>(List.of("A")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);
    }
    @Test
    void compareHandsFirstWin() {
        hand1 = new Hand(new ArrayList<>(List.of("A")));
        hand2 = new Hand(new ArrayList<>(List.of("2")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
    }

    @Test
    void compareHandsSecondWin() {
        hand1 = new Hand(new ArrayList<>(List.of("2")));
        hand2 = new Hand(new ArrayList<>(List.of("A")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);
    }

    @Test
    void getBestCombinaisonForHighestCard(){
        hand1 = new Hand(new ArrayList<>(List.of("2","D","7")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.HIGHEST_CARD, new Card(Rank.QUEEN));
        assertEquals(cv.toString(),referee.getBestCombinaison(hand1).toString());
    }

}
