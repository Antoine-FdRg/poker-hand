package com.seinksansdoozebank.fr.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VictoryTest {
    @Test
    void testToString() {
        Hand hand = new Hand(List.of(new String[]{"2Co", "3Ca", "4Tr", "5Pi", "6Co"}));
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand.getCards());
        Victory victory = new Victory(hand, combinaisonValue);

        String expectedString = "Victory with hand:" + hand + ", combinaisonValue=" + combinaisonValue;
        assertEquals(expectedString, victory.toString());
    }

    @Test
    void testToStringNullCombinaisonValue() {
        Hand hand = new Hand(List.of(new String[]{"2Co", "3Ca", "4Tr", "5Pi", "6Co"}));
        CombinaisonValue combinaisonValue = null;
        Victory victory = new Victory(hand, combinaisonValue);

        String expectedString = "Victory with hand:" + hand + ", combinaisonValue=null";
        assertEquals(expectedString, victory.toString());
    }

    @Test
    void testToStringNullHand() {
        Hand hand = null;
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.HIGHEST_CARD, new Hand(List.of(new String[]{"2Co", "3Ca", "4Tr", "5Pi", "6Co"})).getCards());
        Victory victory = new Victory(hand, combinaisonValue);

        String expectedString = "Victory{hand=null, combinaisonValue=" + combinaisonValue + "}";
        assertEquals(expectedString, victory.toString());
    }
}
