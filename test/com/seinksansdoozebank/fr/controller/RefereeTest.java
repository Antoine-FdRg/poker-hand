package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.Rank;
import com.seinksansdoozebank.fr.model.Suit;

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
        assertEquals(hand1, victory.getHand());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here all the hand are straights
     * so the compareHands method should return a Victory with the hand with the highest straight
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testStraightVictoryNoWinner() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);
    }

    @Test
    void testStraightVictoryHand2Wins() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("3Ca", "4Tr", "5Pi", "6Co", "7Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(hand2, victory.getHand());
    }

    @Test
    void testStraightVictoryHand2WinsDifferentOrder() {
        hand1 = new Hand(new ArrayList<>(List.of("ACa", "2Co", "3Pi", "4Tr", "5Tr")));
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(hand2, victory.getHand());
    }

    @Test
    void testStraightVictoryHand2WinsWithHighCard() {
        hand1 = new Hand(new ArrayList<>(List.of("9Ca", "10Co", "RPi", "DTr", "VTr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(hand2, victory.getHand());
    }

    @Test
    void testStraightVictoryHand2WinsWithHighCardDifferentOrder() {
        hand1 = new Hand(new ArrayList<>(List.of("4Ca", "5Co", "3Pi", "2Tr", "ATr")));
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "RTr", "DPi", "VCo", "ACo")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(hand2, victory.getHand());
    }

    /**
     * Test the getBestCombinaison method of the Referee class
     * Here all the hand are straights
     * so the getBestCombinaison method should return a CombinaisonValue with the STRAIGHT combination
     *
     * @see Referee#getBestCombinaison(Hand)
     */
    @Test
    void testStraightCombinationHand1() {
        hand1 = new Hand(new ArrayList<>(List.of("2Ca", "3Co", "4Pi", "5Tr", "6Tr")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
    }

    @Test
    void testStraightCombinationHand2() {
        hand2 = new Hand(new ArrayList<>(List.of("2Ca", "3Tr", "4Pi", "5Co", "6Co")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());
    }

    @Test
    void testStraightCombinationHand1DifferentOrder() {
        hand1 = new Hand(new ArrayList<>(List.of("4Ca", "2Co", "3Pi", "ATr", "5Tr")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
    }

    @Test
    void testStraightCombinationHand2DifferentOrder() {
        hand2 = new Hand(new ArrayList<>(List.of("5Ca", "4Tr", "3Pi", "6Co", "7Co")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());
    }

    @Test
    void testStraightCombinationHand1HighCard() {
        hand1 = new Hand(new ArrayList<>(List.of("9Ca", "10Co", "RPi", "VTr", "DTr")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
    }

    @Test
    void testStraightCombinationHand2HighCard() {
        hand2 = new Hand(new ArrayList<>(List.of("10Ca", "ATr", "DPi", "VCo", "RCo")));
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
        assertEquals(hand2, victory.getHand());
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
        Hand handBestCardQueen = new Hand(new ArrayList<>(List.of("2Ca", "DTr", "7Pi", "3Pi", "4Pi")));
        assertEquals(Combinaison.HIGHEST_CARD, referee.getBestCombinaison(handBestCardQueen).getCombinaison());
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
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "VCo"));

        // The hand with the straight should win
        Victory result = this.referee.compareHands(hand1, hand2);
        assertNotNull(result);
        assertEquals(hand2, result.getHand());
        assertEquals(Combinaison.STRAIGHT, result.getCombinaisonValue().getCombinaison());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the two hands have straights
     * but the hand with the lower straight should lose
     * so the compareHands method should return a Victory with the hand with the highest straight
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testCompareHandsStraightLoses() {
        Hand hand1 = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "APi"));
        Hand hand2 = new Hand(List.of("2Ca", "3Tr", "4Co", "5Ca", "6Tr"));

        // The hand with the lower straight should lose
        Victory result = this.referee.compareHands(hand1, hand2);
        assertNotNull(result);
        assertEquals(hand1, result.getHand());
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
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("2Ca", "3Tr", "4Co", "5Ca", "6Tr"));

        // The hands should result in a draw
        Victory result = this.referee.compareHands(hand1, hand2);
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
        Hand hand = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "9Pi"));
        CombinaisonValue combinaisonValue = this.referee.getBestCombinaison(hand);

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
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "7Co"));
        CombinaisonValue combinaisonValue = this.referee.getBestCombinaison(hand);

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
        Hand hand = new Hand(List.of("10Co", "VCa", "DTr", "RPi", "9Pi"));
        Optional<List<Card>> isStraight = this.referee.searchStraight(hand);

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
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));
        Optional<List<Card>> isStraight = this.referee.searchStraight(hand);

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
    void searchMissedThreeOfAKindTest() {
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));
        Optional<List<Card>> isThreeOfAKind = this.referee.searchThreeOfAKind(hand);
        assertFalse(isThreeOfAKind.isPresent());
    }

    /**
     * Test the searchThreeOfAKind method of the Referee class
     * Here the hand has a three of a kind
     * So the searchThreeOfAKind method will return an optional list with one card
     *
     * @see Referee#searchThreeOfAKind(Hand)
     */
    @Test
    void searchThreeOfAKindTest() {
        Hand hand = new Hand(List.of("2Co", "3Ca", "2Tr", "2Pi", "VCo"));
        Optional<List<Card>> isThreeOfAKind = this.referee.searchThreeOfAKind(hand);
        assertTrue(isThreeOfAKind.isPresent());
    }

    @Test
    void getBestCombinaisonThreeOfAKind() {

        /* we check if the method searchThreeOfAKind works */
        Hand threeOfAKindOfTwo = new Hand(new ArrayList<>(List.of("2Ca", "2Tr", "3Tr", "4Tr", "2Co")));
        Hand pairCombination = new Hand(new ArrayList<>(List.of("2Ca", "ATr", "3Tr", "4Tr", "2Tr")));
        assertEquals(Combinaison.THREE_OF_A_KIND, this.referee.getBestCombinaison(threeOfAKindOfTwo).getCombinaison());
        assertNotEquals(Combinaison.THREE_OF_A_KIND, this.referee.getBestCombinaison(pairCombination).getCombinaison());
    }

    @Test
    void threeOfAKindVictoryTestWithHighestCardHand() {

        /* case with threeOfAKind combination and highest card combination*/
        Hand nonSpecificHand = new Hand(new ArrayList<>(List.of("3Co", "2Tr", "5Tr", "8Tr", "7Tr")));
        victory = this.referee.compareHands(threeOfAKindHandOfEight, nonSpecificHand);
        assertEquals(victory.getHand(), threeOfAKindHandOfEight);
    }

    @Test
    void threeOfAKindVictoryTestWithTwoDifferentsThreeOfAKind() {
        /* case with two different threeOfAKind  */
        Hand threeOfAKindHandOfAce = new Hand(new ArrayList<>(List.of("ACa", "3Tr", "ATr", "5Tr", "ACo")));
        victory = this.referee.compareHands(threeOfAKindHandOfEight, threeOfAKindHandOfAce);
        assertEquals(victory.getHand(), threeOfAKindHandOfAce);
    }

    @Test
    void threeOfAKindVictoryTestWithASTraight() {
        /* We test if the straight is stronger than the threeOfAKind*/
        Hand straigthHand = new Hand(new ArrayList<>(List.of("2Co", "3Tr", "4Tr", "5Tr", "6Tr")));
        Hand threeOfAKindHandOfthree = new Hand(new ArrayList<>(List.of("2Pi", "3Pi", "4Ca", "3Tr", "3Ca")));
        victory = this.referee.compareHands(straigthHand, threeOfAKindHandOfthree);
        assertEquals(victory.getHand(), straigthHand);
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
        Hand hand = new Hand(List.of("2Co", "3Co", "10Co", "VCo", "ACo"));
        Hand hand2 = new Hand(List.of("2Co", "2Ca", "2Tr", "3Pi", "4Co"));

        Victory victory = this.referee.compareHands(hand, hand2);

        assertNotNull(victory);
        assertEquals(hand, victory.getHand());
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
    void testVictoryFlushAgainstStraightExpectFlushWinning() {
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));

        Victory victory = this.referee.compareHands(hand, hand2);

        assertNotNull(victory);
        assertEquals(hand, victory.getHand());
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
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));

        CombinaisonValue combinaisonValue = this.referee.getBestCombinaison(hand);

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
    void testEqualFlushAgainstFlush() {
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));

        Victory victory = this.referee.compareHands(hand, hand2);

        assertNull(victory);
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hand1 has a flush with best card as V
     * and the hand2 has a flush too, but with best card as 10
     * so the Victory should be for the hand with the flush with best card as V
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testVictoryFlushAgainstFlushDifferentCards() {
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Ca", "3Ca", "4Ca", "5Ca", "10Ca"));

        Victory victory = this.referee.compareHands(hand, hand2);

        assertNotNull(victory);
        assertEquals(hand, victory.getHand());
        assertEquals(Combinaison.FLUSH, victory.getCombinaisonValue().getCombinaison());
    }

    /**
     * Test the compareHands method of the Referee class
     * Here the hand1 has a flush
     * and the hand2 has a flush too, but with different suits
     * so the Victory should be null
     *
     * @see Referee#compareHands(Hand, Hand)
     */
    @Test
    void testEqualFlushWithDifferentSuits() {
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));
        Hand hand2 = new Hand(List.of("2Ca", "3Ca", "4Ca", "5Ca", "VCa"));

        Victory victory = this.referee.compareHands(hand, hand2);

        assertNull(victory);
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
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "VCo"));

        Optional<List<Card>> isFlush = this.referee.searchFlush(hand);

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
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));

        Optional<List<Card>> isFlush = this.referee.searchFlush(hand);

        assertFalse(isFlush.isPresent());
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
        Hand hand = new Hand(List.of("2Co", "2Ca", "4Tr", "5Pi", "VCo"));

        Optional<List<Card>> isPair = this.referee.searchPair(hand);

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
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "VCo"));

        Optional<List<Card>> isPair = this.referee.searchPair(hand);

        assertFalse(isPair.isPresent());
    }

    @Test
    void testSearchTwoPairWithTwoPair(){
        Hand handWithTwoPair = new Hand(List.of("2Co", "3Ca", "2Tr", "3Pi", "VCo"));
        Optional<List<Card>> optListCardOfTwoPair = referee.searchTwoPair(handWithTwoPair);
        assertTrue(optListCardOfTwoPair.isPresent());

        List<Card> listCardOfTwoPair = optListCardOfTwoPair.get();
        assertEquals(3, listCardOfTwoPair.size());
        assertEquals(Rank.THREE,listCardOfTwoPair.get(0).getRank());
        assertEquals(Rank.TWO,listCardOfTwoPair.get(1).getRank());
        assertEquals(new Card(Rank.JACK, Suit.HEART),listCardOfTwoPair.get(2));
    }

    @Test
    void testSearchTwoPairWithNoTwoPair(){
        Hand handWithPair = new Hand(List.of("2Co", "3Ca", "4Tr", "3Pi", "VCo"));
        Optional<List<Card>> optListCardOfTwoPair = referee.searchTwoPair(handWithPair);
        assertFalse(optListCardOfTwoPair.isPresent());
    }

    @Test
    void testGetBestCombinaisonTwoPairAndVerifOrder(){
        Hand hand = new Hand(List.of("2Co", "3Ca", "VCo", "3Pi", "2Tr"));
        Combinaison combiTwoPair = referee.getBestCombinaison(hand).getCombinaison();
        assertNotEquals(Combinaison.THREE_OF_A_KIND, combiTwoPair);
        assertEquals(Combinaison.TWO_PAIR,combiTwoPair);
        assertNotEquals(Combinaison.PAIR, combiTwoPair);
    }
}
