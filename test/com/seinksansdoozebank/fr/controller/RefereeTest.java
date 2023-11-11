package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
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
    private Hand threeOfAKindHandOfEight;

    @BeforeEach
    void setUp() {
        referee = new Referee();
        Hand.resetIdCounter();
        threeOfAKindHandOfEight = new Hand(new ArrayList<>(List.of("8Ca", "3Tr", "8Tr", "5Tr", "8Co")));
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
     * Test the searchThreeOfAKind method of the Referee class
     * Here the hand is not a threeOfAKind hand
     * So the searchThreeOfAKind method will return false
     *
     * @see Referee#searchThreeOfAKind(Hand)
     */
    @Test
    void searchmissedThreeOfAKindTest(){
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));
        Referee referee = new Referee();
        Optional<List<Card>> isStraight = referee.searchThreeOfAKind(hand);
        assertFalse(isStraight.isPresent());
    }
    /**
     * Test the searchThreeOfAKind method of the Referee class
     * Here the hand has a three of a kind
     * So the searchThreeOfAKind method will return an optional list with one card
     *
     * @see Referee#searchThreeOfAKind(Hand)
     */
    @Test
    void searchThreeOfAKindTest(){
        Hand hand = new Hand(List.of("2Co", "3Ca", "2Tr", "2Pi", "VCo"));
        Referee referee = new Referee();
        Optional<List<Card>> isStraight = referee.searchThreeOfAKind(hand);
        assertTrue(isStraight.isPresent());
    }

    @Test
    void getBestCombinaisonThreeOfAKind() {

        /* we check if the method searchThreeOfAKind works */
        Hand threeOfAKindOfTwo = new Hand(new ArrayList<>(List.of("2Ca", "2Tr", "3Tr", "4Tr", "2Co")));
        Hand pairCombination = new Hand(new ArrayList<>(List.of("2Ca", "ATr", "3Tr", "4Tr", "2Tr")));
        assertEquals(Combinaison.THREE_OF_A_KIND, referee.getBestCombinaison(threeOfAKindOfTwo).getCombinaison());
        assertNotEquals(Combinaison.THREE_OF_A_KIND, referee.getBestCombinaison(pairCombination).getCombinaison());
    }

    @Test
    void threeOfAKindVictoryTestWithHighestCardHand() {

        /* case with threeOfAKind combination and highest card combination*/
        Hand nonSpecificHand = new Hand(new ArrayList<>(List.of("3Co", "2Tr", "5Tr", "8Tr", "7Tr")));
        victory = referee.compareHands(threeOfAKindHandOfEight, nonSpecificHand);
        assertEquals(victory.getHand(), threeOfAKindHandOfEight);
    }

    @Test
    void threeOfAKindVictoryTestWithTwoDifferentsThreeOfAKind() {
        /* case with two different threeOfAKind  */
        Hand threeOfAKindHandOfAce = new Hand(new ArrayList<>(List.of("ACa", "3Tr", "ATr", "5Tr", "ACo")));
        victory = referee.compareHands(threeOfAKindHandOfEight, threeOfAKindHandOfAce);
        assertEquals(victory.getHand(), threeOfAKindHandOfAce);
    }

    @Test
    void threeOfAKindVictoryTestWithASTraight() {
        /* We test if the straight is stronger than the threeOfAKind*/
        Hand straigthHand = new Hand(new ArrayList<>(List.of("2Co", "3Tr", "4Tr", "5Tr", "6Tr")));
        Hand threeOfAKindHandOfthree = new Hand(new ArrayList<>(List.of("2Pi", "3Pi", "4Ca", "3Tr", "3Ca")));
        victory = referee.compareHands(straigthHand, threeOfAKindHandOfthree);
        assertEquals(victory.getHand(), straigthHand);
    }

    /**
     * Test the searchPair method of the Referee class
     * Here the hand is a pair
     * so the searchPair method should return a list of cards
     *
     * @see Referee#searchPair(Hand)
     */
    @Test
    void testIsPairWithPair() {
        // Create a hand with a pair
        Hand hand = new Hand(List.of("2Co", "2Ca", "4Tr", "5Pi", "VCo"));

        Referee referee = new Referee();
        Optional<List<Card>> isPair = referee.searchPair(hand);

        assertTrue(isPair.isPresent());
    }

    /**
     * Test the searchPair method of the Referee class
     * Here the hand is not a pair
     * so the searchPair method should return an empty optional
     *
     * @see Referee#searchPair(Hand)
     */
    @Test
    void testIsPairNoPair() {
        // Create a hand with no pair
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));

        Referee referee = new Referee();
        Optional<List<Card>> isPair = referee.searchPair(hand);

        assertFalse(isPair.isPresent());
    }
}
