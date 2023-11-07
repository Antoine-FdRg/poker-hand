package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Test the compareHands method of the Referee class
     * Here all the hand are straights with identical cards
     * so the compareHands method should return null
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void compareHandsDraw() {
        hand1 = new Hand(new ArrayList<>(List.of("ACa", "2Ca", "4Ca", "5Ca", "6Ca")));
        hand2 = new Hand(new ArrayList<>(List.of("ACo", "2Co", "4Co", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hands have two different combinations
     * so the compareHands method should return a Victory with the hand with the highest combination
     * <p>
     * Here the hand1 has a straight and the hand2 has a highest card
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void compareHandsFirstWin() {
        hand1 = new Hand(new ArrayList<>(List.of("ACa", "2Ca", "4Ca", "5Ca", "6Ca")));
        hand2 = new Hand(new ArrayList<>(List.of("9Co", "2Co", "4Co", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
    }

    /**
     * Test the compareHands method of the Referee class
     * Here all the hand are straights
     * so the compareHands method should return a Victory with the hand with the highest straight
     *
     * @see Referee#compareHands(Hand, Hand)
     */
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
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("4Ca", "5Co", "3Pi", "2Tr", "ATr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);
    }

    /**
     * Test the getBestCombinaison method of the Referee class
     * Here all the hand are straights
     * so the getBestCombinaison method should return a CombinaisonValue with the STRAIGHT combination
     *
     * @see Referee#getBestCombinaison(Hand)
     */
    @Test
    void straighCombinaisonTest() {
        //TODO: Split this test into multiple tests
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
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("4Ca", "5Co", "3Pi", "2Tr", "ATr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here there is 2 different combinations
     * so the compareHands method should return a Victory with the hand with the highest combination
     * <p>
     * Here the hand1 has a straight and the hand2 has a highest card
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void compareHandsSecondWin() {
        hand1 = new Hand(new ArrayList<>(List.of("9Ca", "2Ca", "4Ca", "5Ca", "6Ca")));
        hand2 = new Hand(new ArrayList<>(List.of("ACo", "2Co", "4Co", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);
    }

    /**
     * Test the getBestCombinaison method of the Referee class
     * Here the hand has no combination except the highest card
     * so the getBestCombinaison method should return a CombinaisonValue with the HIGHEST_CARD combinaison
     *
     * @see Referee#getBestCombinaison(Hand)
     */
    @Test
    void getBestCombinaisonForHighestCard() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "DTr", "7Pi", "3Pi", "4Pi")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand1.getBestCard()));
        assertEquals(cv.toString(), referee.getBestCombinaison(hand1).toString());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the two hands have straights
     * but the hand with the higher straight should win
     *
     * @see Referee#compareHands(Hand, Hand)
     */
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

    /**
     * Test the compareHands method of the Referee class
     * Here the two hands have straights
     * but the hand with the lower straight should lose
     * so the compareHands method should return a Victory with the hand with the lower straight
     *
     * @see Referee#compareHands(Hand, Hand)
     */
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

    /**
     * Test the compareHands method of the Referee class
     * Here the two hands have the same combination with the same values
     * so the compareHands method should return null
     *
     * @see Referee#compareHands(Hand, Hand)
     */
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

    /**
     * Test the getBestCombinaison method of the Referee class
     * Here the hand has a straight
     * so the getBestCombinaison method should return a CombinaisonValue with the STRAIGHT combinaison
     *
     * @see Referee#getBestCombinaison(Hand)
     */
    @Test
    void testGetBestCombinaisonStraight() {
        // Create a hand with a straight
        Hand hand = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "9Pi"));

        Referee referee = new Referee();
        CombinaisonValue combinaisonValue = referee.getBestCombinaison(hand);

        assertNotNull(combinaisonValue);
        assertEquals(Combinaison.STRAIGHT, combinaisonValue.getCombinaison());
    }

    /**
     * Test the getBestCombinaison method of the Referee class
     * Here the hand has no combination except the highest card
     * so the getBestCombinaison method should return a CombinaisonValue with the HIGHEST_CARD combinaison
     *
     * @see Referee#getBestCombinaison(Hand)
     */
    @Test
    void testGetBestCombinaisonNoStraight() {
        // Create a hand with no straight
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "7Co"));

        Referee referee = new Referee();
        CombinaisonValue combinaisonValue = referee.getBestCombinaison(hand);

        assertNotNull(combinaisonValue);
        assertEquals(Combinaison.HIGHEST_CARD, combinaisonValue.getCombinaison());
    }

    /**
     * Test the searchStraight method of the Referee class
     * Here the hand is a straight
     * so the searchStraight method should return a list of cards
     *
     * @see Referee#searchStraight(Hand)
     */
    @Test
    void testIsStraightWithStraight() {
        // Create a hand with a straight
        Hand hand = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "9Pi"));

        Referee referee = new Referee();
        Optional<List<Card>> isStraight = referee.searchStraight(hand);

        assertTrue(isStraight.isPresent());
    }

    /**
     * Test the searchStraight method of the Referee class
     * Here the hand is not a straight
     * so the searchStraight method should return an empty optional
     *
     * @see Referee#searchStraight(Hand)
     */
    @Test
    void testIsStraightNoStraight() {
        // Create a hand with no straight
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));

        Referee referee = new Referee();
        Optional<List<Card>> isStraight = referee.searchStraight(hand);

        assertFalse(isStraight.isPresent());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hand has a flush
     * and the hand2 has a double pair
     * So the victory should be for the hand with the flush
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testVictoryFlushAgainstThreeOfAKind() {
        // Create a hand with a full house
        Hand hand = new Hand(List.of("2Co", "3Co", "10Co", "VCo", "ACo"));
        Hand hand2 = new Hand(List.of("2Co", "2Ca", "2Tr", "3Pi", "4Co"));

        Referee referee = new Referee();
        Victory victory = referee.compareHands(hand, hand2);

        assertNotNull(victory);
        assertEquals(Combinaison.FLUSH, victory.getCombinaisonValue().getCombinaison());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hand has a flush
     * And the hand2 has a straight
     * So the victory should be for the hand with the flush
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testVictoryFlushAgainstStraight() {
        // Create a hand with a flush
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));

        Referee referee = new Referee();
        Victory victory = referee.compareHands(hand, hand2);

        assertNotNull(victory);
        assertEquals(Combinaison.FLUSH, victory.getCombinaisonValue().getCombinaison());
    }

    /**
     * Test the getBestCombinaison method of the Referee class
     * Here the hand has a flush
     * so the getBestCombinaison method should return a CombinaisonValue with the FLUSH combinaison
     * and the list of cards of the flush
     *
     * @see Referee#getBestCombinaison(Hand)
     */
    @Test
    void testGetBestCombinaisonFlush() {
        // Create a hand with a flush
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));

        Referee referee = new Referee();
        CombinaisonValue combinaisonValue = referee.getBestCombinaison(hand);

        assertNotNull(combinaisonValue);
        assertEquals(Combinaison.FLUSH, combinaisonValue.getCombinaison());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hand1 has a flush
     * and the hand2 has a flush to with the exact same cards
     * so the Victory should be null
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testVictoryFlushAgainstFlush() {
        // Create a hand with a flush
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));

        Referee referee = new Referee();
        Victory victory = referee.compareHands(hand, hand2);

        assertNull(victory);
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hand1 has a flush
     * and the hand2 has a flush too, but with different cards
     * so the Victory should be null
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testVictoryFlushAgainstFlushDifferentCards() {
        // Create a hand with a flush
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Ca", "3Ca", "4Ca", "5Ca", "VCo"));

        Referee referee = new Referee();
        Victory victory = referee.compareHands(hand, hand2);

        assertNotNull(victory);
        assertEquals(Combinaison.FLUSH, victory.getCombinaisonValue().getCombinaison());
    }

    /**
     * Test the searchFlush method of the Referee class
     * Here the hand is a flush
     * so the searchFlush method should return a list of cards
     *
     * @see Referee#searchFlush(Hand)
     */
    @Test
    void testIsFlushWithFlush() {
        // Create a hand with a flush
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));

        Referee referee = new Referee();
        Optional<List<Card>> isFlush = referee.searchFlush(hand);

        assertTrue(isFlush.isPresent());
    }

    /**
     * Test the searchFlush method of the Referee class
     * Here the hand is not a flush
     * so the searchFlush method should return an empty optional
     *
     * @see Referee#searchFlush(Hand)
     */
    @Test
    void testIsFlushNoFlush() {
        // Create a hand with no flush
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));

        Referee referee = new Referee();
        Optional<List<Card>> isFlush = referee.searchFlush(hand);

        assertFalse(isFlush.isPresent());
    }

}
