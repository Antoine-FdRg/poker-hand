package com.seinksansdoozebank.fr.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        int nbCards = 3;
        this.game = new Game(2, nbCards);
        Game.resetDeck();
    }

    @Test
    void checkInput(){
        List<String> cards = new ArrayList<>();
        cards.add("A");
        cards.add("6");
        assertFalse(game.checkInput(cards));
        cards.add("11");
        assertFalse(game.checkInput(cards));
        cards.remove(2);
        cards.add("V");
        assertFalse(game.checkInput(cards));
        cards = new ArrayList<>();
        cards.add("VPi");
        cards.add("2Co");
        cards.add("3Ci");
        assertFalse(game.checkInput(cards));
        cards.remove(2);
        Game.resetDeck();
        cards.add("2Ca");
        assertTrue(game.checkInput(cards));
    }

    /**
     * CheckInput tests for duplicates Management
     */
    @Test
    void checkInputDuplicates(){
        List<String> cards = new ArrayList<>();
        cards.add("2Ca");
        cards.add("2Ca");
        cards.add("2Co");
        assertFalse(game.checkInput(cards));
        cards.remove(1);
        cards.add("2Pi");
        assertTrue(game.checkInput(cards));
    }
}