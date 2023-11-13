package com.seinksansdoozebank.fr.model;

import com.seinksansdoozebank.fr.controller.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CombinaisonValueTest {
    private CombinaisonValue testBestCardJack;
    private CombinaisonValue testThreeOfAKindSix;
    private CombinaisonValue testThreeOfAKindEight;
    private CombinaisonValue testBestCardSix;
    private CombinaisonValue testStraightFlushSixHeart;
    private Referee referee;

    @BeforeEach
    void setUp() {
        Hand handHighestCardJack = new Hand(List.of("VCa", "2Ca", "3Ca", "4Ca", "5Ca"));
        Hand handHighestCardSix = new Hand(List.of("5Co", "2Ca", "3Ca", "4Ca", "6Ca")); // TODO : Why not use ?
        Hand threeOfAKindSix = new Hand(List.of("5Co", "6Ca", "6Tr", "ATr", "6Co"));
        Hand threeOfAKindEight = new Hand(List.of("5Co", "8Ca", "8Co", "ATr", "8Tr")); // TODO : Why not use ?
        Hand threeOfAKindKing = new Hand(List.of("5Co", "RCa", "RCo", "ATr", "RTr")); // TODO : Why not use ?
        Hand straightFlushSixHeart = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "6Co"));
        referee = new Referee();
        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(handHighestCardJack.getBestCard()));
        testBestCardSix = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(threeOfAKindSix.getBestCard()));
        testThreeOfAKindEight = new CombinaisonValue(Combinaison.THREE_OF_A_KIND, List.of(new Card(Rank.EIGHT,Suit.CLUB)));
        testThreeOfAKindSix = new CombinaisonValue(Combinaison.THREE_OF_A_KIND, List.of(new Card(Rank.SIX,Suit.CLUB)));
        testStraightFlushSixHeart = new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, straightFlushSixHeart.getCards());
    }


    /**
     * Test the compareTo method of the CombinaisonValue class
     */
    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardSix) < 0);
        assertTrue(testBestCardSix.compareTo(testBestCardJack) > 0);
    }

    @Test
    void compareTowithThreeOfAKindDifferent() {
        /*We test if the method compareto in a threeOfAKind case when there are different works*/
        assertTrue(testThreeOfAKindEight.compareTo(testThreeOfAKindSix) > 0);
    }

    @Test
    void compareTowithThreeOfAKindEquality() {
        /*We test if the method compareto in a threeOfAKind case when there are equals works*/
        assertThrows(IllegalStateException.class, () -> testThreeOfAKindEight.compareTo(testThreeOfAKindEight), "Il est impossible d'avoir deux brelans identiques");
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
    void testHighestCardToString() {
        assertEquals("carte la plus élevée : " + testBestCardJack.getBestCard().toString(), testBestCardJack.toString());
        assertEquals("carte la plus élevée : " + testBestCardSix.getBestCard().toString(), testBestCardSix.toString());
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
    void testGetBestCardInStraight() {
        // Test getting the best card from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Co", "4Co", "5Co", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Card bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.SIX, Suit.HEART), bestCard);
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
        assertEquals(new Card(Rank.FIVE, Suit.HEART), bestCard);

        hand = new Hand(List.of("10Co", "RCo", "DCo", "VCo", "ACo"));
        combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.ACE, Suit.HEART), bestCard);
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
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, this.referee.searchStraight(hand).get());

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

    /**
     * Test comparing two CombinaisonValue objects with the same combinaison.
     * Here the hand2 is the winner because the best card is a JACK
     *
     * @see CombinaisonValue#compareTo(CombinaisonValue)
     */
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

    /**
     * Test comparing two CombinaisonValue objects with different combinaisons.
     * Here the hand1 is the winner because the combinaison is a Straight
     *
     * @see CombinaisonValue#compareTo(CombinaisonValue)
     */
    @Test
    void testCompareToDifferentCombinaison() {
        // Test comparing two CombinaisonValue objects with different combinaisons
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "DCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result > 0);
    }

    /**
     * Test getting the best card from a CombinaisonValue.
     * Here the best card is a JACK
     *
     * @see CombinaisonValue#getBestCard()
     */
    @Test
    void testGetBestCard() {
        // Test getting the best card from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Card bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.SIX, Suit.HEART), bestCard);
    }

    /**
     * Test getting the combinaison from a CombinaisonValue.
     * Here the combinaison is a Straight
     *
     * @see CombinaisonValue#getCombinaison()
     */
    @Test
    void testGetCombinaison() {
        // Test getting the combinaison from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        Combinaison combinaison = combinaisonValue.getCombinaison();
        assertEquals(Combinaison.STRAIGHT, combinaison);
    }

    /**
     * Test generating the string representation of a CombinaisonValue for a STRAIGHT.
     * Here the combinaison is a Straight of 6
     *
     * @see CombinaisonValue#toString()
     */
    @Test
    void testStraightToString() {
        // Test generating the string representation of a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand.getCards());

        assertEquals("Quinte de 6", combinaisonValue.toString()); //TODO: check if this is the best way to print it
    }

    /**
     * Test generating the string representation of a CombinaisonValue for a FLUSH.
     * Here the combinaison is a Flush of Heart
     *
     * @see CombinaisonValue#toString()
     */
    @Test
    void testFlushToString() {
        // Test generating the string representation of a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Co", "7Co", "5Co", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.FLUSH, hand.getCards());

        assertEquals("Couleur de Coeur", combinaisonValue.toString());
    }

    /**
     * Test generating the string representation of a CombinaisonValue for a HIGHEST_CARD.
     * Here the combination is the Highest Card with the best Card of JACK and the worst Card of SEVEN
     * The second combination is the same but with the best Card of Jack and the worst Card of 8
     * <p>
     * So the combination Two is winning
     *
     * @see CombinaisonValue#compareTo(CombinaisonValue)
     * @see CombinaisonValue#getCardMakingTheDifference()
     */
    @Test
    void testCompareKickers() {
        //7Co VPi 10Ca RCo DPi
        Hand hand1 = new Hand(List.of("7Co", "VPi", "10Ca", "RCo", "DPi"));
        //RCo VPi 10Ca 8Co DTr
        Hand hand2 = new Hand(List.of("RCo", "VPi", "10Ca", "8Co", "DTr"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);

        assertEquals(-1, result);

        assertEquals(new Card(Rank.EIGHT, Suit.HEART), combinaisonValue2.getCardMakingTheDifference());
    }

    @Test
    void testCompareTOBetweenTwoDifferentHighCard() {
        //7Co VPi 10Ca RCo DPi
        Hand hand1 = new Hand(List.of("7Co", "VPi", "10Ca", "RCo", "DPi"));
        //RCo VPi 10Ca 8Co DTr
        Hand hand2 = new Hand(List.of("ACo", "VPi", "10Ca", "8Co", "DTr"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);

        assertEquals(-1, result);
    }

    /**
     * Test Pair vs Straight comparison
     * Here the Pair should lose
     * because the Pair is a Pair of 2
     * and the Straight is a Straight of 6
     */
    @Test
    void testComparePairVsStraight() {
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
    void testToStringThreeOfAKindBasicCard() {
        CombinaisonValue threeOfAKindOfTenCombinaison = new CombinaisonValue(Combinaison.THREE_OF_A_KIND, List.of(new Card(Rank.TEN,Suit.CLUB)));
        assertEquals("Brelan de 10", threeOfAKindOfTenCombinaison.toString());
    }

    @Test
    void testToStringThreeOfAKindAceCard() {
        CombinaisonValue threeOfAKindOfAceCombinaison = new CombinaisonValue(Combinaison.THREE_OF_A_KIND, List.of(new Card(Rank.ACE,Suit.CLUB)));
        assertEquals("Brelan d'As", threeOfAKindOfAceCombinaison.toString());
    }

    /**
     * Test Pair vs three of a kind comparison
     */
    @Test
    void testThreeOAKindVSPair() {
        Hand pairHand = new Hand(List.of("2Co", "2Ca", "5Pi", "4Tr", "6Co"));

        CombinaisonValue threeOfAKindCombination = new CombinaisonValue(Combinaison.THREE_OF_A_KIND, List.of(new Card(Rank.TWO,Suit.HEART)));
        CombinaisonValue pairCombination = new CombinaisonValue(Combinaison.PAIR, pairHand.getCards());

        int result = threeOfAKindCombination.compareTo(pairCombination);
        assertTrue(result >0);
    }

    @Test
    void testStraightFlushToString() {
        assertEquals("Quinte Flush de Coeur", testStraightFlushSixHeart.toString());
    }

    @Test
    void testStraightFlushWinnerVSStraight() {
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "VCo"));

        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT, hand2.getCards());

        int result = testStraightFlushSixHeart.compareTo(combinaisonValue2);
        assertTrue(result > 0);
    }

    @Test
    void testStraightFlushWinnerVSFlush() {
        Hand hand2 = new Hand(List.of("7Co", "8Co", "9Co", "10Co", "DCo"));

        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.FLUSH, hand2.getCards());

        int result = testStraightFlushSixHeart.compareTo(combinaisonValue2);
        assertTrue(result > 0);
    }

    @Test
    void testStraightFlushWinnerVSFlushWithAce() {
        Hand hand1 = new Hand(List.of("ACo", "2Co", "3Co", "4Co", "5Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Co", "9Co", "10Co", "DCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.FLUSH, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result > 0);
    }

    @Test
    void testStraightFlushASWinnerVSStraightFlushKing() {
        Hand hand1 = new Hand(List.of("RCo", "VCo", "ACo", "DCo", "10Co"));
        Hand hand2 = new Hand(List.of("RCa", "DCa", "VCa", "10Ca", "9Ca"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result > 0);
    }
    @Test
    void testStraightFlushEquality() {
        Hand hand1 = new Hand(List.of("RCo", "VCo", "ACo", "DCo", "10Co"));
        Hand hand2 = new Hand(List.of("RCo", "VCo", "ACo", "DCo", "10Co"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, hand1.getCards());
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT_FLUSH, hand2.getCards());

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertEquals(0, result);
    }

}