package com.seinksansdoozebank.fr.model;

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

    @BeforeEach
    void setUp() {
        Hand hand1 = new Hand(List.of("V"));
        Hand hand2 = new Hand(List.of("5"));
        Hand hand3= new Hand(List.of("5","6","6","A","6"));
        Hand hand4=new Hand(List.of("5","8","8","A","8"));
        Hand hand5= new Hand(List.of("5","8","8","R","8"));
        testBestCardJack = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        testBestCardFive = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand2);
        testThreeOfAKindSix= new CombinaisonValue(Combinaison.THREE_OF_A_KIND, hand3);
        testThreeOfAKindEight = new CombinaisonValue(Combinaison.THREE_OF_A_KIND,hand4);
        testThreeOfAKindKing= new CombinaisonValue(Combinaison.THREE_OF_A_KIND,hand5);

    }

    @Test
    void compareTo() {
        assertTrue(testBestCardJack.compareTo(testBestCardFive) > 0);
        assertTrue(testBestCardFive.compareTo(testBestCardJack) < 0);
        assertEquals(0, testBestCardJack.compareTo(testBestCardJack));

        /*We test if the method compareto in a threeOfAKind case works*/
        assertTrue(testThreeOfAKindEight.compareTo(testThreeOfAKindSix) > 0);
        assertTrue(testThreeOfAKindEight.compareTo(testThreeOfAKindKing)>0);
        assertEquals(0, testThreeOfAKindSix.compareTo(testThreeOfAKindSix));
    }

    @Test
    void testToString() {
        assertEquals("carte la plus élevée : "+ testBestCardJack.getBestCard().getRank().getName(), testBestCardJack.toString());
        assertEquals("carte la plus élevée : "+ testBestCardFive.getBestCard().getRank().getName(), testBestCardFive.toString());
    }

    @Test
    void testToStringThreeOfAKind(){
        assertEquals("brelan de : "+ testThreeOfAKindSix.toStringThreeOfAKind(), testThreeOfAKindSix.toString());
    }
}