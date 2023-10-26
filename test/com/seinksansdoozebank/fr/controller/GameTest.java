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
        assertTrue(game.checkInput(cards));
    }
}