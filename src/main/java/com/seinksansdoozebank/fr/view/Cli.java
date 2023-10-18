package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.model.Victory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cli {

    public List<String> initializeHand(int numHand, int numCards) {
        String[] cards;
        boolean handIsCorrect = true;
        boolean lengthIsCorrect = true;
        do {
            if (!lengthIsCorrect) {
                System.out.println("La main n'est pas de la bonne taille, veuillez réessayer");
            }
            if (!handIsCorrect) {
                System.out.println("La main n'est pas correcte (trop de cartes), veuillez réessayer");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Main " + numHand + ": ");
            cards = scanner.nextLine().split(" ");
            for (String card : cards) {
                // if number is between 2 and 10 or in (v,d,r,a)
                if (card.matches("^10|[2-9]|[VDRA]$")) {
                    handIsCorrect = true;
                } else {
                    handIsCorrect = false;
                    break;
                }
            }
        } while (cards.length != numCards || !handIsCorrect || !lengthIsCorrect);
        return new ArrayList<>(Arrays.asList(cards));
    }

    public void displayWinner(Victory winner) {
        System.out.println("La main " + winner.getHand().getID() + " gagne avec " + winner.getCombinaisonValue());
    }

    public void displayDraw() {
        System.out.println("Egalité");
    }
}
