package com.seinksansdoozebank.fr.model;

import java.util.*;

public class CombinaisonValue {
    private final Combinaison combinaison;
    private final Hand hand;

    public CombinaisonValue(Combinaison combinaison, Hand hand) {
        this.combinaison = combinaison;
        this.hand = hand;
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
                case THREE_OF_A_KIND -> {
                    int cptCard1 = 0;
                    ArrayList<Card> liste1 = new ArrayList<>();
                    Card cardBrelan1 = new Card(Rank.TWO);
                    for (Card card : hand.getCards()) {
                        if (cptCard1 == 3) {
                            break;
                        }
                        cptCard1 = 0;
                        liste1 = new ArrayList<>();
                        for (Card card1 : hand.getCards()) {
                            if (card1.getRank().equals(card.getRank())) {
                                cptCard1 += 1;
                                if (cptCard1 == 3) {
                                    cardBrelan1 = new Card(card1.getRank());
                                }
                            } else {
                                liste1.add(card1);
                            }
                        }
                    }
                    Card cardBrelan2 = new Card(Rank.TWO);
                    int cptCard2 = 0;
                    ArrayList<Card> liste2 = new ArrayList<>();
                    for (Card card : combinaison2.getHand().getCards()) {
                        if (cptCard2 == 3) {
                            break;
                        }
                        cptCard2 = 0;
                        liste2 = new ArrayList<>();
                        for (Card card1 : combinaison2.getHand().getCards()) {
                            if (card1.getRank().equals(card.getRank())) {
                                cptCard2 += 1;
                                if (cptCard2 == 3) {
                                    cardBrelan2 = new Card(card1.getRank());
                                }
                            } else {
                                liste2.add(card1);
                            }
                        }
                    }
                    if (cardBrelan1.compareTo(cardBrelan2) != 0) {
                        return cardBrelan1.compareTo(cardBrelan2);
                    } else {
                        Collections.reverse(liste1);
                        Collections.reverse(liste2);
                        return liste1.get(0).compareTo(liste2.get(0));
                    }
                }
                default -> {
                    return this.getBestCard().compareTo(combinaison2.getBestCard());
                }
            }

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
     *
     * @return the victory condition string that must be printed
     */
    @Override
    public String toString() {
        String victoryCondition = "";
        switch (this.combinaison) {
            case HIGHEST_CARD:
                victoryCondition += "carte la plus élevée : " + this.getBestCard().getRank().getName();
                break;
            case STRAIGHT:
                victoryCondition += "suite : " + this.toStringStraight();
                break;
            case THREE_OF_A_KIND:
                victoryCondition += "brelan de : " + this.toStringThreeOfAKind();
                break;
            default:
                victoryCondition += this.combinaison.getName() + " : " + this.getBestCard().getRank().getName();
                break;
        }
        return victoryCondition;
    }

    private String toStringStraight() {
        List<Card> cards = this.hand.getSortedCards();
        StringBuilder stringBuilder = new StringBuilder();
        if (cards.contains(new Card(Rank.ACE))) {
            stringBuilder.append("A ");
        }
        // if there is a ACE in the hand, the ACE is set to the first card
        for (Card card : cards) {
            if (card.getRank().equals(Rank.ACE)) {
                continue;
            }
            stringBuilder.append(card.getRank().getSymbol()).append(" ");
        }
        return stringBuilder.toString();
    }

    public String toStringThreeOfAKind() {
        int compteur = 0;
        Card cardBrelan = new Card(Rank.TWO);
        for (Card card : hand.getCards()) {
            if (compteur == 3) {
                break;
            }
            compteur = 0;
            for (Card card1 : hand.getCards()) {
                if (card1.getRank().equals(card.getRank())) {
                    compteur += 1;
                    if (compteur == 3) {
                        cardBrelan = card1;
                    }
                }
            }
        }
        return cardBrelan.getRank().getName();
    }

    /**
     * Get the best card of the combinaison
     *
     * @return the best card of the combinaison
     */
    public Card getBestCard() {
        // if the combinaison is a straight so the best card is the last card of the hand.
        // And if there is an ACE in the hand, the ACE is NOT the best card.
        if (this.combinaison.equals(Combinaison.STRAIGHT)) {
            return this.hand.getCards().get(this.hand.getCards().size() - 1);
        }
        return this.hand.getBestCard();
    }

    /**
     * Get the cards of the combinaison
     *
     * @return the cards of the combinaison
     */
    public List<Card> getCards() {
        return this.hand.getCards();
    }

    /**
     * Get the combinaison
     *
     * @return the combinaison
     */
    public Combinaison getCombinaison() {
        return this.combinaison;
    }


    public Hand getHand() {
        return hand;
    }
}
