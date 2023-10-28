package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonValueTest {
    private CombinaisonValue combinaisonValue1;
    private CombinaisonValue combinaisonValue2;
    Hand hand1;
    Hand hand2;

    @BeforeEach
    void setUp() {
        hand1 = new Hand(List.of("V","10","2","8","7"));
        hand2 = new Hand(List.of("5","6","2","3","8"));
    }

/////////////////////////////// compareTo tests ///////////////////////////////////////

    @Test
    void compareToWithTwoHighCard() {
        hand1 = new Hand(List.of("5","6","2","3","8"));
        hand2 = new Hand(List.of("V","10","2","8","7"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2);
        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
        assertEquals(0, combinaisonValue1.compareTo(combinaisonValue1));
    }
    @Test
    void compareToBetweenHighestCardAndTwoPair() {
        hand1 = new Hand(List.of("V","10","2","8","7"));
        hand2 = new Hand(List.of("5","5","2","3","3"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.TWO_PAIR, hand2);
        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
    }
    @Test
    void compareToBetweenTwoDifferentsTwoPair() {
        hand1 = new Hand(List.of("V","3","7","3","7"));
        hand2 = new Hand(List.of("8","8","2","9","9"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.TWO_PAIR, hand2);

        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
    }
    @Test
    void compareToBetweenTwoTwoPairWhereTheSecondPairAreTheSame() {
        hand1 = new Hand(List.of("V","3","V","3","7"));
        hand2 = new Hand(List.of("3","3","2","D","D"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.TWO_PAIR, hand2);

        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
    }

    @Test
    void compareToBetweenTwoTwoPairWhereTheFirstPairAreTheSame() {
        hand1 = new Hand(List.of("V","2","V","2","7"));
        hand2 = new Hand(List.of("3","3","2","V","V"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.TWO_PAIR, hand2);

        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
    }
    @Test
    void compareToBetweenTwoSameTwoPairButDifferentsKickers() {
        hand1 = new Hand(List.of("V","2","V","2","5"));
        hand2 = new Hand(List.of("8","2","2","V","V"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.TWO_PAIR, hand2);

        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
    }
    @Test
    void compareToBetweenTwoSameTwoPairAndSameKickers() {
        hand1 = new Hand(List.of("D","3","D","3","6"));
        hand2 = new Hand(List.of("6","3","3","D","D"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.TWO_PAIR, hand2);

        assertEquals(0, combinaisonValue1.compareTo(combinaisonValue2));
        assertEquals(0, combinaisonValue2.compareTo(combinaisonValue1));
    }

    @Test
    void compareToBetweenTwoPairAndStraight() {
        hand1 = new Hand(List.of("5","5","2","3","3"));
        hand2 = new Hand(List.of("V","10","9","8","7"));
        combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        combinaisonValue2 = new CombinaisonValue(Combinaison.STRAIGHT, hand2);
        assertTrue(combinaisonValue1.compareTo(combinaisonValue2) < 0);
        assertTrue(combinaisonValue2.compareTo(combinaisonValue1) > 0);
    }

/////////////////////////////// getKicker tests ///////////////////////////////////////
        @Test
        void getKickerWithTwoPair() {
            hand1 = new Hand(List.of("V","3","7","3","7"));
            combinaisonValue1 = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
            assertEquals(new Card(Rank.JACK), combinaisonValue1.getKicker());
        }
        @Test
        void getKickerWithTwoPairError() {
            hand1 = new Hand(List.of("V","D","R","10","9"));
            combinaisonValue1 = new CombinaisonValue(Combinaison.STRAIGHT, hand1);
            assertThrows(IllegalStateException.class, ()->combinaisonValue1.getKicker());
        }

/////////////////////////////// toString tests ///////////////////////////////////////

    @Test
    void testToStringHighestCard() {
        combinaisonValue1 = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        assertEquals("carte la plus élevée : "+ combinaisonValue1.getBestCard().getRank().getName(), combinaisonValue1.toString());
    }


    @Test
    void toStringTwoPair(){
        hand1 = new Hand(new ArrayList<>(List.of("2","D","2","7","7")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        String expected = "double paire : "+new Card(Rank.TWO)+" et "+new Card(Rank.SEVEN);
        assertEquals(expected, cv.toString());
    }
    @Test
    void toStringTwoPairError(){
        hand1 = new Hand(new ArrayList<>(List.of("2","D","8","7","7")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        assertThrows(IllegalStateException.class, cv::toString);
    } 
}