package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
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
    void setUp() {
        referee = new Referee();
        Hand.resetIdCounter();
    }

    @Test
    void compareHandsDraw() {
        hand1 = new Hand(new ArrayList<>(List.of("ACa")));
        hand2 = new Hand(new ArrayList<>(List.of("ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);
    }

    @Test
    void compareHandsFirstWin() {
        hand1 = new Hand(new ArrayList<>(List.of("ACa")));
        hand2 = new Hand(new ArrayList<>(List.of("2Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
    }

    @Test
    void straighVictoryTest() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);

        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("3Ca", "4Tr", "5Pi", "6Co", "7Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("ACa", "2Co", "3Pi", "4Tr", "5Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("9Ca", "10Co", "RPi", "DTr", "VTr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);

        hand1 = new Hand(new ArrayList<>(List.of("4Ca", "5Co", "3Pi", "2Tr", "ATr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
    }

    @Test
    void straighCombinaisonTest() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("3Ca", "4Tr", "5Pi", "6Co", "7Co")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("ACa", "2Co", "3Pi", "4Tr", "5Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("9Ca", "10Co", "RPi", "DTr", "VTr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertNotEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("4Ca", "5Co", "3Pi", "2Tr", "ATr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertNotEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());
    }

    @Test
    void compareHandsSecondWin() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca")));
        hand2 = new Hand(new ArrayList<>(List.of("ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);
    }

    @Test
    void getBestCombinaisonForHighestCard() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "DTr", "7Pi")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        assertEquals(cv.toString(), referee.getBestCombinaison(hand1).toString());
    }

    @Test
    void testCompareHandsStraightWins() {
        // Create two hands, one with a straight and one without
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "VCo"));

        Referee referee = new Referee();

        // The hand with the straight should win
        Victory result = referee.compareHands(hand1, hand2);
        assertNotNull(result);
        assertEquals(Combinaison.STRAIGHT, result.getCombinaisonValue().getCombinaison());
    }

    @Test
    void testCompareHandsStraightLoses() {
        // Create two hands, both with straights
        Hand hand1 = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "APi"));
        Hand hand2 = new Hand(List.of("2Ca", "3Tr", "4Co", "5Ca", "6Tr"));

        Referee referee = new Referee();

        // The hand with the lower straight should lose
        Victory result = referee.compareHands(hand1, hand2);
        assertNotNull(result);
        assertEquals(Combinaison.STRAIGHT, result.getCombinaisonValue().getCombinaison());
    }

    @Test
    void testCompareHandsDraw() {
        // Create two hands with the same ranks
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("2Ca", "3Tr", "4Co", "5Ca", "6Tr"));

        Referee referee = new Referee();

        // The hands should result in a draw
        Victory result = referee.compareHands(hand1, hand2);
        assertNull(result);
    }

    @Test
    void testGetBestCombinaisonStraight() {
        // Create a hand with a straight
        Hand hand = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "9Pi"));

        Referee referee = new Referee();
        CombinaisonValue combinaisonValue = referee.getBestCombinaison(hand);

        assertNotNull(combinaisonValue);
        assertEquals(Combinaison.STRAIGHT, combinaisonValue.getCombinaison());
    }

    @Test
    void testGetBestCombinaisonNoStraight() {
        // Create a hand with no straight
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "7Co"));

        Referee referee = new Referee();
        CombinaisonValue combinaisonValue = referee.getBestCombinaison(hand);

        assertNotNull(combinaisonValue);
        assertEquals(Combinaison.HIGHEST_CARD, combinaisonValue.getCombinaison());
    }

    @Test
    void testIsStraightWithStraight() {
        // Create a hand with a straight
        Hand hand = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "9Pi"));

        Referee referee = new Referee();
        boolean isStraight = referee.isStraight(hand);

        assertTrue(isStraight);
    }

    @Test
    void testIsStraightNoStraight() {
        // Create a hand with no straight
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));

        Referee referee = new Referee();
        boolean isStraight = referee.isStraight(hand);

        assertFalse(isStraight);
    }

}
