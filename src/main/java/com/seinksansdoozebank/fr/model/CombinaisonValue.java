package com.seinksansdoozebank.fr.model;

public class CombinaisonValue {
    private final Combinaison combinaison;
    private final Card cards;

    public CombinaisonValue(Combinaison combinaison, Card cards) {
        this.combinaison = combinaison;
        this.cards = cards;
    }

    /**
     * Compare two combinaison values
     * @param combinaison2 the combinaison value to compare
     * @return 1 if the combinaison value is greater, -1 if the combinaison value is lower, 0 if they are equals
     */
    public int compareTo(CombinaisonValue combinaison2) {
        int result = this.combinaison.compareTo(combinaison2.combinaison);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return this.cards.compareTo(combinaison2.cards);
        }
    }

    /**
     * Merci d'ajouter les conditions de victoire suivantes au fur et à mesure de leur implémentation :
     * "La main 1 gagne avec carte la plus élevée : As"
     * "La main 2 gagne avec paire de 5"
     * "La main 1 gagne avec double paire : As et 5"
     * "La main 2 gagne avec brelan de 5"
     * "La main 1 gagne avec suite"
     * "La main 2 gagne avec couleur"
     * "La main 1 gagne avec full : As par les 5"
     * "La main 2 gagne avec carré de 5"
     * "La main 1 gagne avec quinte flush"
     * @return the victory condition string that must be printed
     */
    public String toString() {
        String victoryCondition = "";
        switch (this.combinaison) {
            case HIGHEST_CARD:
                victoryCondition += "carte la plus élevée : " + this.cards.getRank().getName();
                break;
            default:
                victoryCondition += this.combinaison.getName() + " : " + this.cards.getRank().getName();
                break;
        }
        return victoryCondition;
    }

    public Combinaison getCombinaison() {
        return combinaison;
    }

    public Card getCards() {
        return cards;
    }
}
