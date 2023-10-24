package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import com.seinksansdoozebank.fr.view.Cli;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Cli view = new Cli();
    private final Referee referee = new Referee();
    private final List<Hand> hands = new ArrayList<>();
    private final int nbrOfHands;
    private final int nbrOfCards;

    public Game(int nbrOfHands, int nbrOfCards) {
        this.nbrOfCards = nbrOfCards;
        this.nbrOfHands = nbrOfHands;
    }

    /**
     * Run the game
     */
    public void run() {
        for (int i = 0; i < this.nbrOfHands; i++) {
            List<String> cardsHand = view.initializeHand(i+1);
            while (!checkInput(cardsHand)) {
                cardsHand = view.initializeHand(i+1);
            }
            Hand hand = new Hand(cardsHand);
            hands.add(hand);
        }
        Victory winner = referee.compareHands(hands.get(0), hands.get(1));
        if (winner == null) {
            view.displayDraw();
        } else {
            view.displayWinner(winner);
        }
    }

    /**
     * Check if the input is valid
     *
     * @param cards the cards to check
     * @return true if the input is valid, false otherwise
     */
    private boolean checkInput(List<String> cards) {
        //TODO test this
        if (cards.size() != this.nbrOfCards) {
            view.displayAlertSize(this.nbrOfCards);
            return false;
        }
        for (String card : cards) {
            if (!card.matches("^10|[2-9]|[VDRA]$")) {
                view.displayAlertInvalidCard();
                return false;
            }
        }
        return true;
    }
}
