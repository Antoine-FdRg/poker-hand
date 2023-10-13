package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.model.Card;
import com.seinksansdoozebank.fr.model.Combinaison;

import java.util.ArrayList;
import java.util.Scanner;

public class Cli {

    public ArrayList<Integer> initializeHand(int numHand) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Main "+ numHand +": ");
        String[] cards = scanner.nextLine().split(" ");
        ArrayList<Integer> hand = new ArrayList<>();
        for (String card : cards) {
            hand.add(Integer.parseInt(card));
        }
        return hand;
    }

    public void displayWinner(int handNumber, Combinaison combinaison, Card highestCard) {
        System.out.println("La main " + handNumber + " gagne avec " + combinaison.getName() + " : " + highestCard.getValue());
    }

    public void displayDraw() {
        System.out.println("Egalité");
    }
}
