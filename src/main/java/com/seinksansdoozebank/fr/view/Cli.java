package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.Victory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cli {

    /**
     * Initialize the hand with the cards given by the user
     *
     * @param numHand the number of the hand
     * @return the list of the cards
     */
    public List<String> initializeHand(int numHand) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Main " + numHand + ": ");
        String[] cards = scanner.nextLine().split(" ");
        return new ArrayList<>(List.of(cards));
    }

    /**
     * Display the hand
     * @param numHand the hand id
     * @param cards the cards of the hand
     */
    public void displayHand(int numHand, List<Card> cards) {
        System.out.print("Main " + numHand + ": ");
        for (Card card : cards) {
            System.out.print(card.getRank().getSymbol() + " ");
        }
        System.out.println();
    }

    /**
     * Display the alert for the size of the hand
     * @param numberOfCards the number of cards
     */
    public void displayAlertSize(int numberOfCards) {
        System.out.println("Veuillez entrer " + numberOfCards + " cartes");
    }

    /**
     * Display the alert for the invalid card
     */
    public void displayAlertInvalidCard() {
        System.out.println("Veuillez entrer des cartes valides");
    }

    /**
     * Display the winner of the game
     *
     * @param winner the winner of the game
     */
    public void displayWinner(Victory winner) {
        System.out.println("La main " + winner.getHand().getID() + " gagne avec " + winner.getCombinaisonValue().getVictoryCondition());
    }

    /**
     * Display the draw
     */
    public void displayDraw() {
        System.out.println("Egalité");
    }
}
