package com.seinksansdoozebank.fr.model;

import java.util.List;

public class CombinaisonValue {
    private final Combinaison combinaison;
    private final List<Card> cards;
    private Card cardMakingTheDifference;

    public CombinaisonValue(Combinaison combinaison, List<Card> cards) {
        this.combinaison = combinaison;
        this.cards = cards;
    }

    /**
     * Compare two combinaison values
     *
     * @param combinaison2 the combinaison value to compare
     * @return 1 if the combinaison value is greater, -1 if the combinaison value is lower, 0 if they are equals
     */
    public int compareTo(CombinaisonValue combinaison2) {
        int result = this.combinaison.compareTo(combinaison2.getCombinaison());
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            switch (this.combinaison) {
                case STRAIGHT -> {
                    return this.getBestCard().compareTo(combinaison2.getBestCard());
                }
                // Flush est géré dans default
                default -> {
                    // compare all kickers of the pair
                    List<Card> kickers = this.getCards();
                    List<Card> comparedKickers = combinaison2.getCards();
                    return compareKickers(kickers, comparedKickers, combinaison2);
                }
            }
        }
    }

    protected int compareKickers(List<Card> kickers, List<Card> comparedKickers, CombinaisonValue combinaisonValue2) {
        if (kickers.size() != comparedKickers.size()) {
            throw new IllegalStateException("There is not the same number of kickers");
        }
        kickers.sort(Card::compareTo);
        comparedKickers.sort(Card::compareTo);
        for (int i = 0; i < kickers.size(); i++) {
            int result = kickers.get(i).compareTo(comparedKickers.get(i));
            if (result > 0) {
                this.cardMakingTheDifference = kickers.get(i);
                return 1;
            } else if (result < 0) {
                combinaisonValue2.setCardMakingTheDifference(comparedKickers.get(i));
                return -1;
            }
        }
        return 0;
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
     *
     * @return the victory condition string that must be printed
     */
    @Override
    public String toString() {
        StringBuilder victoryCondition = new StringBuilder();
        switch (this.combinaison) {
            case HIGHEST_CARD:
                victoryCondition.append("carte la plus élevée : ").append(this.cardMakingTheDifference.toString());
                break;
            case FLUSH:
                victoryCondition.append("Couleur de ").append(this.getBestCard().getSuit().getName());
                break;
            case STRAIGHT:
                int size = this.cards.size();
                if (this.cards.get(size - 1).getRank().equals(Rank.ACE)) {
                    victoryCondition.append("Quinte Broadway");
                } else if (this.cards.get(0).getRank().equals(Rank.ACE)) {
                    victoryCondition.append("Quinte à l'As");
                } else if (this.cards.get(size - 1).getRank().equals(Rank.FIVE)) {
                    victoryCondition.append("Quinte à 5");
                } else {
                    victoryCondition.append("Quinte de ").append(this.cards.get(size - 1).getRank().getName());
                }
                break;
            default:
                victoryCondition.append(this.combinaison.getName()).append(" : ").append(this.getBestCard().getRank().getName());
                break;
        }
        return victoryCondition.toString();
    }

    /**
     * Get the best card of the combinaison
     *
     * @return the best card of the combinaison
     */
    public Card getBestCard() {
        // if the combinaison is a straight
        if (this.combinaison.equals(Combinaison.STRAIGHT) && (this.cards.get(0).getRank().equals(Rank.TWO) && this.cards.get(this.cards.size() - 1).getRank().equals(Rank.ACE))) {
            return this.cards.get(this.cards.size() - 2);
        }
        return this.cards.get(this.cards.size() - 1);
    }

    /**
     * Get the cards of the combinaison
     *
     * @return the cards of the combinaison
     */
    public List<Card> getCards() {
        return this.cards;
    }

    /**
     * Get the combinaison
     *
     * @return the combinaison
     */
    public Combinaison getCombinaison() {
        return this.combinaison;
    }

    public void setCardMakingTheDifference(Card cardMakingTheDifference) {
        this.cardMakingTheDifference = cardMakingTheDifference;
    }

    public Card getCardMakingTheDifference() {
        return this.cardMakingTheDifference;
    }
}
