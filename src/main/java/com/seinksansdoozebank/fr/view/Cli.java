package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.model.Victory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cli {

    public List<String> initializeHand(int numHand) {
        //TODO check number of cards
        //TODO make working only the symbol of the card
        Scanner scanner = new Scanner(System.in);
        System.out.println("Main "+ numHand +": ");
        String[] cards = scanner.nextLine().split(" ");
        ArrayList<String> hand = new ArrayList<>();
        for (String card : cards) {
            hand.add(card);
        }
        return hand;
    }

    public void displayWinner(Victory winner) {
        System.out.println("La main " + winner.getHand().getID() + " gagne avec " + winner.getCombinaisonValue());
    }

    public void displayDraw() {
        System.out.println("Egalité");
    }
}
