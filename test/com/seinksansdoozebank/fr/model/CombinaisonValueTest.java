package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonValueTest {
    private CombinaisonValue testBestCardJack;
    private CombinaisonValue testBestCardFive;

    @BeforeEach
    void setUp() {
        Hand hand1 = new Hand(List.of("VCa"));
        Hand hand2 = new Hand(List.of("5Co"));
        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        testBestCardFive = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2);
    }

    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardFive) > 0);
        assertTrue(testBestCardFive.compareTo(testBestCardJack) < 0);
        assertEquals(0, testBestCardJack.compareTo(testBestCardJack));
    }

    @Test
    void testToString() {
        assertEquals("carte la plus élevée : "+ testBestCardJack.getBestCard().getRank().getName(), testBestCardJack.toString());
        assertEquals("carte la plus élevée : "+ testBestCardFive.getBestCard().getRank().getName(), testBestCardFive.toString());
    }

    @Test
    void testCompareToSameCombinaison() {
        // Test comparing two CombinaisonValue objects with the same combinaison
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "VCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1);
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT, hand2);

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result < 0);
    }

    @Test
    void testCompareToDifferentCombinaison() {
        // Test comparing two CombinaisonValue objects with different combinaisons
        Hand hand1 = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        Hand hand2 = new Hand(List.of("7Co", "8Ca", "9Tr", "10Pi", "DCo"));

        CombinaisonValue combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1);
        CombinaisonValue combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2);

        int result = combinaisonValue1.compareTo(combinaisonValue2);
        assertTrue(result > 0); // Straight is lower than Highest Card
    }

    @Test
    void testGetBestCard() {
        // Test getting the best card from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand);

        Card bestCard = combinaisonValue.getBestCard();
        assertEquals(new Card(Rank.SIX, Suit.CLUB), bestCard);
    }

    @Test
    void testGetCombinaison() {
        // Test getting the combinaison from a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand);

        Combinaison combinaison = combinaisonValue.getCombinaison();
        assertEquals(Combinaison.STRAIGHT, combinaison);
    }

    @Test
    void testToStringStraight() {
        // Test generating the string representation of a straight CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand);

        String straightString = combinaisonValue.toStringStraight();
        assertEquals("2 3 4 5 6 ", straightString);
    }

    @Test
    void testToStringStraightWithAce() {
        // Test generating the string representation of a straight with an Ace
        Hand hand = new Hand(List.of("ACo", "2Ca", "3Tr", "4Pi", "5Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand);

        String straightString = combinaisonValue.toStringStraight();
        assertEquals("A 2 3 4 5 ", straightString);
    }

    @Test
    void testStraightToString() {
        // Test generating the string representation of a CombinaisonValue
        Hand hand = new Hand(List.of("2Co", "3Ca", "4Tr", "5Pi", "6Co"));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.STRAIGHT, hand);

        String stringValue = combinaisonValue.toString();
        assertEquals("suite : 2 3 4 5 6 ", stringValue);
    }
}