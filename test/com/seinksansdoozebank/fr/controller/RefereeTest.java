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
    public void setUp() {
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
        hand1 = new Hand(new ArrayList<>(List.of("A", "2", "4", "5", "6")));
        hand2 = new Hand(new ArrayList<>(List.of("A", "2", "4", "5", "6")));
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
        hand1 = new Hand(new ArrayList<>(List.of("A", "2", "4", "5", "6")));
        hand2 = new Hand(new ArrayList<>(List.of("9", "2", "4", "5", "6")));
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
        hand1 = new Hand(new ArrayList<>(List.of("2", "3", "4", "5", "6")));
        hand2 = new Hand(new ArrayList<>(List.of("2", "3", "4", "5", "6")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);

        hand1 = new Hand(new ArrayList<>(List.of("2", "3", "4", "5", "6")));
        hand2 = new Hand(new ArrayList<>(List.of("3", "4", "5", "6", "7")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("A", "2", "3", "4", "5")));
        hand2 = new Hand(new ArrayList<>(List.of("2", "3", "4", "5", "6")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("9", "10", "R", "D", "V")));
        hand2 = new Hand(new ArrayList<>(List.of("10", "R", "D", "V", "A")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("4", "5", "3", "2", "A")));
        hand2 = new Hand(new ArrayList<>(List.of("10", "R", "D", "V", "2")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
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
        hand1 = new Hand(new ArrayList<>(List.of("2", "3", "4", "5", "6")));
        hand2 = new Hand(new ArrayList<>(List.of("2", "3", "4", "5", "6")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand2 = new Hand(new ArrayList<>(List.of("3", "4", "5", "6", "7")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("A", "2", "3", "4", "5")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("9", "10", "R", "D", "V")));
        hand2 = new Hand(new ArrayList<>(List.of("10", "R", "D", "V", "A")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("4", "5", "3", "2", "A")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
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
        hand1 = new Hand(new ArrayList<>(List.of("9", "2", "4", "5", "6")));
        hand2 = new Hand(new ArrayList<>(List.of("A", "2", "4", "5", "6")));
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
        hand1 = new Hand(new ArrayList<>(List.of("2", "D", "7", "3", "4")));
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
        Hand hand1 = new Hand(List.of("2", "3", "4", "5", "6"));
        Hand hand2 = new Hand(List.of("7", "8", "9", "10", "V"));

        Referee referee = new Referee();

        // The hand with the straight should win
        Victory result = referee.compareHands(hand1, hand2);
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
        Hand hand1 = new Hand(List.of("10", "V", "D", "R", "A"));
        Hand hand2 = new Hand(List.of("2", "3", "4", "5", "6"));

        Referee referee = new Referee();

        // The hand with the lower straight should lose
        Victory result = referee.compareHands(hand1, hand2);
        assertEquals(result.getHand(), hand1);
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
        Hand hand1 = new Hand(List.of("2", "3", "4", "5", "6"));
        Hand hand2 = new Hand(List.of("2", "3", "4", "5", "6"));

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
        Hand hand = new Hand(List.of("10", "V", "D", "R", "9"));

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
        Hand hand = new Hand(List.of("2", "3", "4", "5", "7"));

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
        Hand hand = new Hand(List.of("10", "V", "D", "R", "9"));

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
        Hand hand = new Hand(List.of("2", "3", "4", "5", "V"));

        Referee referee = new Referee();
        Optional<List<Card>> isStraight = referee.searchStraight(hand);

        assertFalse(isStraight.isPresent());
    }

    @Test
    void threeOfAKindCombinaisonTest(){

        /* we check if the method searchThreeOfAKind works */
       Hand  hand1= new Hand(new ArrayList<>(List.of("2","2","3","4","2")));
       Hand hand2= new Hand(new ArrayList<>(List.of("2","A","3","4","2")));
       assertEquals(Combinaison.THREE_OF_A_KIND, referee.getBestCombinaison(hand1).getCombinaison());
       assertNotEquals(Combinaison.THREE_OF_A_KIND, referee.getBestCombinaison(hand2).getCombinaison());
    }

    @Test
    void threeOfAKindVictoryTest(){

        /* case with threeOfAKind combination and highest card combination*/
        Hand  threeOfAKindHandOfEight = new Hand(new ArrayList<>(List.of("8", "3", "8", "5", "8")));
        Hand nonSpecificHand = new Hand(new ArrayList<>(List.of("3","2","5","8","7")));
        victory = referee.compareHands(threeOfAKindHandOfEight, nonSpecificHand);
        assertEquals(victory.getHand(), threeOfAKindHandOfEight);

        /* case with two different threeOfAKind  */
        Hand threeOfAKindHandOfAce = new Hand(new ArrayList<>(List.of("A","3","A","5","A")));
        victory = referee.compareHands(threeOfAKindHandOfEight, threeOfAKindHandOfAce);
        assertEquals(victory.getHand(), threeOfAKindHandOfAce);

        /* case with the same threeOfAKind but the highest card of each hand is different */
        Hand threeOfAKindHandOfEightStronger = new Hand(new ArrayList<>(List.of("8","3","8","7","8")));
        victory = referee.compareHands(threeOfAKindHandOfEight, threeOfAKindHandOfEightStronger);
        assertEquals(victory.getHand(), threeOfAKindHandOfEightStronger);

        /* We test if the straight is stronger than the threeOfAKind*/
        Hand straigthHand = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        Hand threeOfAKindHandOfthree = new Hand(new ArrayList<>(List.of("2","3","4","3","3")));
        victory = referee.compareHands(straigthHand, threeOfAKindHandOfthree);
        assertEquals(victory.getHand(), straigthHand);
    }
}
