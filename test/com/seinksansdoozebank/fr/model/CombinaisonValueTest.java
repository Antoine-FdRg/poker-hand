package com.seinksansdoozebank.fr.model;

import com.seinksansdoozebank.fr.controller.Referee;
import com.seinksansdoozebank.fr.controller.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonValueTest {
    private CombinaisonValue testBestCardJack;
    private CombinaisonValue testBestCardFive;
    private CombinaisonValue testThreeOfAKindSix;
    private CombinaisonValue testThreeOfAKindEight;
    private CombinaisonValue testThreeOfAKindKing;
    private CombinaisonValue testBestCardSix;

    @BeforeEach
    void setUp() {
        Hand hand1 = new Hand(List.of("VCa", "2Ca", "3Ca", "4Ca", "5Ca"));
        Hand hand2 = new Hand(List.of("5Co", "2Ca", "3Ca", "4Ca", "6Ca"));
        Hand hand3= new Hand(List.of("5Co","6Ca","6Tr","ATr","6Tr"));
        Hand hand4=new Hand(List.of("5Co","8Ca","8Co","ATr","8Tr"));
        Hand hand5= new Hand(List.of("5Tr","8Ca","8Co","RTr","8Ca"));
        Hand hand6= new Hand(List.of("6Tr","8Ca","8Co","6Tr","6Ca"));

        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand1.getBestCard()));
        testBestCardFive = new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand2.getBestCard()));
        testBestCardSix= new CombinaisonValue(Combinaison.HIGHEST_CARD, List.of(hand3.getBestCard()));
        testThreeOfAKindEight = new CombinaisonValue(Combinaison.THREE_OF_A_KIND,hand4.getCards());
        testThreeOfAKindKing= new CombinaisonValue(Combinaison.THREE_OF_A_KIND,hand5.getCards());
        testThreeOfAKindSix= new CombinaisonValue(Combinaison.THREE_OF_A_KIND,hand6.getCards());

    }

    /**
     * Test the compareTo method of the CombinaisonValue class
     */
    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardSix) < 0);
        assertTrue(testBestCardSix.compareTo(testBestCardJack) > 0);


        /*We test if the method compareto in a threeOfAKind case works*/
        assertTrue(testThreeOfAKindEight.compareTo(testThreeOfAKindSix) < 0);
        assertThrows(IllegalArgumentException.class,()->testThreeOfAKindEight.compareTo(testThreeOfAKindKing),"Il est impossible d'avoir deux brelans identiques");
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
        assertEquals(new Card(Rank.SIX,Suit.CLUB), bestCard);
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
        assertEquals(new Card(Rank.FIVE,Suit.CLUB), bestCard);

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
        assertEquals(new Card(Rank.SIX, Suit.CLUB), bestCard);
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
    @Test
    void testToStringThreeOfAKind() {
        Hand threeOfAKindOfAceHand = new Hand(List.of("APi", "10Ca", "ACo", "DTr", "ATr"));
        Hand threeOfAKindOfTenHand= new Hand(List.of("10Co", "10Tr", "ATr", "10Tr", "ACo"));
        CombinaisonValue threeOfAKindOfAceCombinaison = new CombinaisonValue(Combinaison.THREE_OF_A_KIND, threeOfAKindOfAceHand.getCards());
        CombinaisonValue threeOfAKindOfTenCombinaison=new CombinaisonValue(Combinaison.THREE_OF_A_KIND, threeOfAKindOfTenHand.getCards());
        assertEquals("Brelan d'As", threeOfAKindOfAceCombinaison.toString());
        assertEquals("Brelan de 10", threeOfAKindOfTenCombinaison.toString());
    }
    }