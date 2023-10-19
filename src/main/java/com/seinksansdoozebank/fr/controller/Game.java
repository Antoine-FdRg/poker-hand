package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import com.seinksansdoozebank.fr.view.Cli;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Hand> hands = new ArrayList<>();
    private final Cli view = new Cli();
    private final Referee referee = new Referee();
    private int numberOfCards;

    /**
     * Run the game
     *
     * @param numberOFHands the number of hands
     */
    public void run(int numberOFHands, int numberOfCards) {
        this.numberOfCards = numberOfCards;
        for (int i = 0; i < numberOFHands; i++) {
            List<String> cardsHand = view.initializeHand(i+1);
            while (!checkInput(cardsHand)) {
                cardsHand = view.initializeHand(i+1);
            }
            Hand hand = new Hand(cardsHand, i+1);
            hands.add(hand);
        }
        for (Hand hand : hands) {
            view.displayHand(hand.getID(), hand.getCards());
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
        // check if the input is valid
        if (cards.size() != this.numberOfCards) {
            view.displayAlertSize(this.numberOfCards);
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
