package com.seinksansdoozebank.fr.controller;

import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.CombinaisonValue;
import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
/////////////////////////////// compareHands tests ///////////////////////////////////////
    @Test
    void compareHandsDraw() {
        hand1 = new Hand(new ArrayList<>(List.of("A")));
        hand2 = new Hand(new ArrayList<>(List.of("A")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);
    }
    @Test
    void compareHandsFirstWin() {
        hand1 = new Hand(new ArrayList<>(List.of("A")));
        hand2 = new Hand(new ArrayList<>(List.of("2")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
    }

    @Test
    void compareHandsSecondWin() {
        hand1 = new Hand(new ArrayList<>(List.of("2")));
        hand2 = new Hand(new ArrayList<>(List.of("A")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);
    }
/////////////////////////////// straight tests ///////////////////////////////////////

    @Test
    void straighVictoryTest() {
        hand1 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        hand2 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        victory = referee.compareHands(hand1, hand2);
        assertNull(victory);

        hand1 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        hand2 = new Hand(new ArrayList<>(List.of("3","4","5","6","7")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("A","2","3","4","5")));
        hand2 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand2);

        hand1 = new Hand(new ArrayList<>(List.of("9","10","R","D","V")));
        hand2 = new Hand(new ArrayList<>(List.of("10","R","D","V","A")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);

        hand1 = new Hand(new ArrayList<>(List.of("4","5","3","2","A")));
        hand2 = new Hand(new ArrayList<>(List.of("10","R","D","V","A")));
        victory = referee.compareHands(hand1, hand2);
        assertEquals(victory.getHand(), hand1);
    }

    @Test
    void straighCombinaisonTest() {
        hand1 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        hand2 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        hand2 = new Hand(new ArrayList<>(List.of("3","4","5","6","7")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("A","2","3","4","5")));
        hand2 = new Hand(new ArrayList<>(List.of("2","3","4","5","6")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("9","10","R","D","V")));
        hand2 = new Hand(new ArrayList<>(List.of("10","R","D","V","A")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertNotEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());

        hand1 = new Hand(new ArrayList<>(List.of("4","5","3","2","A")));
        hand2 = new Hand(new ArrayList<>(List.of("10","R","D","V","A")));
        assertEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand1).getCombinaison());
        assertNotEquals(Combinaison.STRAIGHT, referee.getBestCombinaison(hand2).getCombinaison());
    }

/////////////////////////////// getBestCombinaison tests ///////////////////////////////////////

    @Test
    void getBestCombinaisonForStraight(){
        hand1 = new Hand(new ArrayList<>(List.of("8","D","V","9","10")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.STRAIGHT, hand1);
        assertEquals(cv.toString(), referee.getBestCombinaison(hand1).toString());
    }
    @Test
    void getBestCombinaisonForTwoPair(){
        hand1 = new Hand(new ArrayList<>(List.of("3","D","10","D","10")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.TWO_PAIR, hand1);
        assertEquals(cv.toString(), referee.getBestCombinaison(hand1).toString());
    }
    @Test
    void getBestCombinaisonForHighestCard(){
        hand1 = new Hand(new ArrayList<>(List.of("2","D","7","8","10")));
        CombinaisonValue cv = new CombinaisonValue(Combinaison.HIGHEST_CARD, hand1);
        assertEquals(cv.toString(), referee.getBestCombinaison(hand1).toString());
    }

/////////////////////////////// searchTwoPair tests ///////////////////////////////////////

    @Test
    void searchTwoPairOfNumber(){
        hand1 = new Hand(new ArrayList<>(List.of("2","D","2","7","7")));
        assertTrue(referee.searchTwoPair(hand1));
    }
    @Test
    void searchTwoPairOfFigures(){
        hand1 = new Hand(new ArrayList<>(List.of("D","D","2","A","A")));
        assertTrue(referee.searchTwoPair(hand1));
    }
    @Test
    void searchTwoPairWithFigureAndNumeral(){
        hand1 = new Hand(new ArrayList<>(List.of("2","R","2","A","R")));
        assertTrue(referee.searchTwoPair(hand1));
    }
    @Test
    void searchTwoPairFalse(){
        hand1 = new Hand(new ArrayList<>(List.of("D","R","2","A","A")));
        assertFalse(referee.searchTwoPair(hand1));
    }
}
////////////////////////////////////////////////////////////////////////////////////////