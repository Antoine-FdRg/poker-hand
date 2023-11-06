package com.seinksansdoozebank.fr.model;

public enum Combinaison {
    HIGHEST_CARD("carte la plus élevée"),   // meilleure carte
    PAIR("paire"),                          // deux cartes de même valeur
    // DOUBLE_PAIR("double paire"),            // deux fois deux cartes de même valeur
    // THREE_OF_A_KIND("brelan"),              // trois cartes de même valeur
    STRAIGHT("suite");                      // cinq cartes qui se suivent
    // FLUSH("couleur"),                       // cinq cartes de la même couleur
    // FULL_HOUSE("full"),                     // un brelan et une paire
    // FOUR_OF_A_KIND("carré"),                // quatre cartes de même valeur
    // STRAIGHT_FLUSH("quinte flush");         // cinq cartes qui se suivent et de la même couleur

    private final String name;


    Combinaison(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
