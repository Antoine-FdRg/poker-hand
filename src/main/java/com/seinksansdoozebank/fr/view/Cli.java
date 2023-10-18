package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.Combinaison;
import com.seinksansdoozebank.fr.model.Victory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cli {

    /**
     * Initialize the hand with the cards given by the user
     * @param numHand the number of the hand
     * @return the list of the cards
     */
    public List<String> initializeHand(int numHand) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Main " + numHand + ": ");
        String[] cards = scanner.nextLine().split(" ");
        return new ArrayList<>(List.of(cards));
    }

    public void displayHand(int numHand, List<Card> cards) {
        System.out.print("Main " + numHand + ": ");
        for (Card card : cards) {
            System.out.print(card.getRank().getSymbol() + " ");
        }
        System.out.println();
    }

    /**
     * Display the winner of the game
     * @param handNumber the number of the hand
     * @param combinaison the combinaison of the hand
     * @param highestCard the highest card of the hand
     */
    public void displayWinner(int handNumber, Combinaison combinaison, Card highestCard) {
        System.out.println("La main " + handNumber + " gagne avec " + combinaison.getName() + " : " + highestCard.getRank().getSymbol());
    }

    /**
     * Display the winner of the game
     * @param winner the winner of the game
     */
    public void displayWinner(Victory winner) {
        System.out.println("La main " + winner.getHand().getID() + " gagne avec " +
                winner.getCombinaisonValue().getCombinaison().getName() + " : " +
                winner.getCombinaisonValue().getCards().getRank().getName());
    }

    /**
     * Display the draw
     */
    public void displayDraw() {
        System.out.println("Egalité");
    }
}
