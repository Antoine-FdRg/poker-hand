package com.seinksansdoozebank.fr;

import com.seinksansdoozebank.fr.controller.Game;

public class Launcher {
    public static void main(String[] args) {
        int nbrOfHands = 2;
        int nbrOfCards = 1;
        Game game = new Game(nbrOfHands, nbrOfCards);
        game.run();
    }
}