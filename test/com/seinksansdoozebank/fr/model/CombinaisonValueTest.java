package com.seinksansdoozebank.fr.model;

import com.seinksansdoozebank.fr.controller.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonValueTest {
    private CombinaisonValue testBestCardJack;
    private CombinaisonValue testBestCardSix;
    private CombinaisonValue testFourOfAKindEight;
    private CombinaisonValue testFourOfAKindSix;

    @BeforeEach
    void setUp() {
        Hand hand1 = new Hand(List.of("VCa", "2Ca", "3Ca", "4Ca", "5Ca"));
        Hand hand2 = new Hand(List.of("5Co", "2Ca", "3Ca", "4Ca", "6Ca"));
        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand1.getBestCard()));
        testBestCardSix = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand2.getBestCard()));
        testFourOfAKindEight = new CombinaisonValue(Combinaison.FOUR_OF_A_KIND, List.of(new Card(Rank.EIGHT,Suit.HEART)));
        testFourOfAKindSix = new CombinaisonValue(Combinaison.FOUR_OF_A_KIND, List.of(new Card(Rank.SIX,Suit.HEART)));

    }

    /**
     * Test the compareTo method of the CombinaisonValue class
     */
    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardSix) > 0);
        assertTrue(testBestCardSix.compareTo(testBestCardJack) < 0);
        assertEquals(0, testBestCardJack.compareTo(testBestCardJack));
    }

    /**
     * Test the toString method of the CombinaisonValue class
     * when generating the string representation of a CombinaisonValue
     * Here the combinaison is the Highest Card
     * and the best card is a JACK
     * <p>
     * So it should return "carte la plus élevée : V"
     * <p>
     * The second test is the same but with a SIX
     * <p>
     * So it should return "carte la plus élevée : 6"
     *
     * @see CombinaisonValue#toString()
     */
    @Test
    void testToString() {
        assertEquals("carte la plus élevée : " + testBestCardJack.getBestCard().getRank().getName(), testBestCardJack.toString());
        assertEquals("carte la plus élevée : " + testBestCardSix.getBestCard().getRank().getName(), testBestCardSix.toString());
    }

    /**
     * Test the compareTo method of the CombinaisonValue class
     * when comparing two CombinaisonValue objects with the same combinaison
     * but different best cards
     * Here the compareTo method should return a number inferior to 0.
     * Because the winner is hand2 with the best card of V
     *
     * @see CombinaisonValue#compareTo(CombinaisonValue)
     */
    @Test
    void testCompareToWithTwoSameStraights() {
        // Test comparing two CombinaisonValue objects with the same combinaison
        Hand hand1 = new Hand(List.of("2Ca", "3Ca", "4Ca", "5Ca", "6Ca"));
        Hand hand2 = new Hand(List.of("7Co", "8Co", "9Co", "10Co", "VCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    /**
     * Test the compareTo method of the CombinaisonValue class
     * when comparing two CombinaisonValue objects with different combinaisons
     * Here the compareTo method should return a number superior to 0.
     * Because the winner is hand1 with a combinaison of Straight
     *
     * @see CombinaisonValue#compareTo(CombinaisonValue)
     */
    @Test
    void testCompareToWithTwoDiffrentsStraights() {
        // Test comparing two CombinaisonValue objects with different combinaisons
        Hand hand1 = new Hand(List.of("2Ca", "3Ca", "4Ca", "5Ca", "6Ca"));
        Hand hand2 = new Hand(List.of("7Co", "8Co", "9Co", "10Co", "DCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result > 0); // Straight is lower than Highest Card
    }

    /**
     * Test the getBestCard method of the CombinaisonValue class
     * when getting the best card from a CombinaisonValue
     * Here the best card is the card with the rank of SIX
     * because the hand is a Straight
     * and the last card of the hand is a SIX
     *
     * @see CombinaisonValue#getBestCard()
     */
    @Test
    void testGetBestCardInStraight () {
        // Test getting the best card from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Card bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.SIX,Suit.HEART), bestCard);
    }

    /**
     * Test the getBestCard method of the CombinaisonValue class
     * when getting the best card from a CombinaisonValue
     * In the first test the best card is FIVE because it's a low Straight
     * <p>
     * In the second test the best card is ACE because it's a high Straight
     *
     * @see CombinaisonValue#getBestCard()
     */
    @Test
    void testBestCardInStraightWithAce() {
        // Test getting the best card from a CombinaisonValue with an Ace
        Hand hand = new Hand(List.of("ACo", "2Co", "3Co", "4Co", "5Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Card bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.FIVE,Suit.HEART), bestCard);

        hand = new Hand(List.of("10Co", "RCo", "DCo", "VCo", "ACo"));
        combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.ACE,Suit.HEART), bestCard);
    }

    /**
     * Test the toString method of the CombinaisonValue class
     * when generating the string representation of a CombinaisonValue
     * Here the combinaison is a Straight
     * and the best card is an ACE
     * <p>
     * So it should return "Quinte à l'As" because it's a low Straight
     *
     * @see CombinaisonValue#toString()
     */
    @Test
    void testToStringStraightWithLowAce() {
        // Test generating the string representation of a straight with an Ace
        Hand hand = new Hand(List.of("ACo", "2Co", "3Co", "4Co", "5Co"));
        Referee referee = new Referee();
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, referee.searchStraight(hand).get());

        assertEquals("Quinte à l'As", combinaisonValue.toString());
    }

    /**
     * Test the toString method of the CombinaisonValue class
     * when generating the string representation of a CombinaisonValue
     * Here the combinaison is a Straight
     * and the best card is an ACE
     * <p>
     * So it should return "Quinte Broadway" because it's a high Straight
     *
     * @see CombinaisonValue#toString()
     */
    @Test
    void testToStringStraightWithHighAce() {
        // Test generating the string representation of a straight with an Ace
        Hand hand = new Hand(List.of("10Co", "RCo", "DCo", "VCo", "ACo"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        assertEquals("Quinte Broadway", combinaisonValue.toString());
    }

    @Test
    void testCompareToSameCombinaison() {
        // Test comparing two CombinaisonValue objects with the same combinaison
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "VCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    @Test
    void testCompareToDifferentCombinaison() {
        // Test comparing two CombinaisonValue objects with different combinaisons
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "DCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result > 0); // Straight is lower than Highest Card
    }

    @Test
    void testGetBestCard() {
        // Test getting the best card from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Card bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.SIX, Suit.HEART), bestCard);
    }

    @Test
    void testGetCombinaison() {
        // Test getting the combinaison from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Combinaison combinaison = combinaisonValue.getCombinaison();
        assertEquals(Combinaison.STRAIGHT, combinaison);
    }

    @Test
    void testStraightToString() {
        // Test generating the string representation of a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        String stringValue = combinaisonValue.toString();
        assertEquals("Quinte de 6", stringValue); //TODO: check if this is the best way to print it
    }

    /**
     * Test Pair vs Straight comparison
     * Here the Pair should lose
     * because the Pair is a Pair of 2
     * and the Straight is a Straight of 6
     */
    @Test
    void testComparePairVsStraight() {
        // Test comparing a Pair and a Straight
        Hand hand1 = new Hand(List.of("2Co", "2Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "VCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.PAIR, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    /**
     * Test Pair vs Pair comparison
     * Here the Pair of 2 should lose
     */
    @Test
    void testComparePairVsPair() {
        // Test comparing two Pairs
        Hand hand1 = new Hand(List.of("2Co", "2Ca", "4Tr", "RPi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "7Ca", "9Tr", "10Pi", "VCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.PAIR, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.PAIR, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    /**
     * Test Pair vs Pair equals comparison
     * Here the Pair of 2 should lose because it's first kicker is a 6
     * and the Pair of 7 has a 10 as first kicker
     */
    @Test
    void testComparePairVsPairEqualsKicker1() {
        // Test comparing two Pairs
        Hand hand1 = new Hand(List.of("2Co", "2Ca", "4Tr", "6Co", "5Pi"));
        Hand hand2 = new Hand(List.of("2Co", "2Ca", "7Co", "4Tr", "5Pi"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.PAIR, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.PAIR, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    /**
     * Test Pair vs Pair equals comparison
     * Here the Pair of 2 should lose because it's second kicker is a 7
     */
    @Test
    void testComparePairVsPairEqualsKicker2() {
        // Test comparing two Pairs
        Hand hand1 = new Hand(List.of("2Co", "2Ca", "7Ca", "4Tr", "5Pi"));
        Hand hand2 = new Hand(List.of("2Co", "2Ca", "4Tr", "6Pi", "7Co"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.PAIR, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.PAIR, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    /**
     * Test Pair vs Pair equals comparison
     */
    @Test
    void testComparePairVsPairEqualsKicker3() {
        // Test comparing two Pairs
        Hand hand1 = new Hand(List.of("2Co", "2Ca", "3Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("2Co", "2Ca", "5Pi", "4Tr", "6Co"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.PAIR, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.PAIR, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }


    /**
     * Test the toString method of the CombinaisonValue class
     * when generating the string representation of a CombinaisonValue
     * Here the combinaison is a Pair
     * and the best card is a JACK
     * <p>
     * So it should return "Paire de V"
     *
     * @see CombinaisonValue#toString()
     */
    @Test
    void testToStringPair() {
        // Test generating the string representation of a Pair CombinaisonValue
        Hand hand = new Hand(List.of("VCa", "VCo", "2Ca", "3Ca", "4Ca"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.PAIR, hand.getCards());

        assertEquals("Paire de Valet", combinaisonValue.toString());

        // test with a pair of 10
        hand = new Hand(List.of("10Ca", "10Co", "2Ca", "3Ca", "4Ca"));
        combinaisonValue = new CombinaisonValue(Combinaison.PAIR, hand.getCards());

        assertEquals("Paire de 10", combinaisonValue.toString());


    }

    @Test
    void compareToWithFourOfAKindDifferent() {
        /*We test if the method compareto in a fourOfAKind case when there are different works*/
        assertTrue(testFourOfAKindEight.compareTo(testFourOfAKindSix) > 0);
    }

    @Test
    void compareToWithFourOfAKindEquality() {
        /*We test if the method compareto in a fourOfAKind case when there are equals works*/
        assertThrows(IllegalStateException.class, () -> testFourOfAKindEight.compareTo(testFourOfAKindEight), "Il est impossible d'avoir deux carrés identiques");
    }
    @Test
    void testToStringFourOfAKindBasicCard() {
        CombinaisonValue fourOfAKindOfTenCombination = new CombinaisonValue(Combinaison.FOUR_OF_A_KIND, List.of(new Card(Rank.TEN,Suit.CLUB)));
        assertEquals("Carré de 10", fourOfAKindOfTenCombination.toString());
    }

    @Test
    void testToStringFourOfAKindAceCard() {
        CombinaisonValue threeOfAKindOfAceCombination= new CombinaisonValue(Combinaison.FOUR_OF_A_KIND, List.of(new Card(Rank.ACE,Suit.CLUB)));
        assertEquals("Carré d'As", threeOfAKindOfAceCombination.toString());
    }

    /**
     * Test Pair vs four of a kind comparison
     */
    @Test
    void testFourOfAKindVSPair() {

        Hand pairHand = new Hand(List.of("2Co", "2Ca", "5Pi", "4Tr", "6Co"));

        CombinaisonValue fourOfAKindCombination = new CombinaisonValue(Combinaison.FOUR_OF_A_KIND, List.of(new Card(Rank.TWO,Suit.HEART)));
        CombinaisonValue pairCombination = new CombinaisonValue(Combinaison.PAIR, pairHand.getCards());

        int result = fourOfAKindCombination.compareTo(pairCombination);
        assertTrue(result >0);
    }

    @Test
    void testFourOfAKindVSStraight(){
        Hand straightHand = new Hand(List.of("ACo", "2Co", "3Co", "4Co", "5Co"));
        CombinaisonValue straightCombinationValue = new CombinaisonValue(Combinaison.STRAIGHT, straightHand.getCards());
        int result= testFourOfAKindEight.compareTo(straightCombinationValue);
        assertTrue(result>0);
    }

}