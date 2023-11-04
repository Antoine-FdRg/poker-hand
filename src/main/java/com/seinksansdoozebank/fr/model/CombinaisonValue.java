package com.seinksansdoozebank.fr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            switch(this.combinaison){
                case TWO_PAIR -> {
                    //Création d'une map ayant comme clé la card et comme valeur le nombre de fois qu'elle apparait dans la main
                    Map<Card, Integer> map1 = hand.getCards().stream()
                            .distinct()
                            .collect(Collectors.toMap(
                                    Function.identity(),
                                    v -> Collections.frequency(hand.getCards(), v))
                            );
                    //Création d'une liste dans laquelle on ne garde que les cards qui apparaissent deux fois dans la main
                    List<Card> cards1 = new ArrayList<>(map1.entrySet().stream()
                            .filter(entry -> entry.getValue() == 2)
                            .map(Map.Entry::getKey)
                            .toList());
                    //Tri pour afficheri la carte la plus élevée en premier
                    Collections.sort(cards1);
                    cards1.sort(Collections.reverseOrder());
                    //Création d'une map ayant comme clé la card et comme valeur le nombre de fois qu'elle apparait dans la main
                    Map<Card, Integer> map2 = combinaison2.getHand().getCards().stream()
                            .distinct()
                            .collect(Collectors.toMap(
                                    Function.identity(),
                                    v -> Collections.frequency(combinaison2.getHand().getCards(), v))
                            );
                    //Création d'une liste dans laquelle on ne garde que les cards qui apparaissent deux fois dans la main
                    List<Card> cards2 = new ArrayList<>(map2.entrySet().stream()
                            .filter(entry -> entry.getValue() == 2)
                            .map(Map.Entry::getKey)
                            .toList());
                    //Tri pour afficher la carte la plus élevée en premier
                    Collections.sort(cards2);
                    cards2.sort(Collections.reverseOrder());
                    if (cards1.size() != 2 || cards2.size() != 2) {
                        throw new IllegalStateException("There is not two pair in the hand");
                    }
                    if(cards1.get(0).compareTo(cards2.get(0)) != 0) {
                        return cards1.get(0).compareTo(cards2.get(0));
                    }
                    else if(cards1.get(1).compareTo(cards2.get(1)) != 0){
                        return cards1.get(1).compareTo(cards2.get(1));
                    } else{
                        return this.getKicker().compareTo(combinaison2.getKicker());
                    }
                }
                default -> {
                    return this.getBestCard().compareTo(combinaison2.getBestCard());
                }
            }
        }
    }

    /**
     * Get the kicker of the combinaison, usefull when two combinaison are equals
     * @return the kicker of the combinaison
     */
    protected Card getKicker() {
        switch (this.combinaison){
            case TWO_PAIR -> {
                //Création d'une map ayant comme clé la card et comme valeur le nombre de fois qu'elle apparait dans la main
                Map<Card, Integer> map = this.getHand().getCards().stream()
                        .distinct()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                v -> Collections.frequency(this.getHand().getCards(), v))
                        );
                //Création d'une liste dans laquelle on ne garde que ls cards qui apparaissent une seule fois dans la main
                List<Card> cards = new ArrayList<>(map.entrySet().stream()
                        .filter(entry -> entry.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .toList());
                return cards.get(0);
            }
            default -> throw new IllegalStateException("There is no kicker for this combinaison");
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
        String victoryCondition =  this.combinaison+" : ";
        switch (this.combinaison) {
            case HIGHEST_CARD:
                victoryCondition += this.getBestCard().getRank().getName();
                break;
            case TWO_PAIR:
                //Création d'une map ayant comme clé la card et comme valeur le nombre de fois qu'elle apparait dans la main
                Map<Card, Integer> map = hand.getCards().stream()
                        .distinct()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                v -> Collections.frequency(hand.getCards(), v))
                        );
                //Création d'une liste dans laquelle on ne garde que les cards qui apparaissent deux fois dans la main
                List<Card> cards = new ArrayList<>(map.entrySet().stream()
                        .filter(entry -> entry.getValue() == 2)
                        .map(Map.Entry::getKey)
                        .toList());
                //Tri pour afficher la carte la plus élevée en premier
                Collections.sort(cards);
                if(cards.size() != 2)
                    throw new IllegalStateException("There is not two pair in the hand");
                victoryCondition += cards.get(0) + " et " + cards.get(1);
                break;
            case STRAIGHT:
                victoryCondition += this.toStringStraight();
                break;
            default:
                victoryCondition += " : " + this.getBestCard().getRank().getName();
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

    /**
     * Get the best card of the combinaison
     *
     * @return the best card of the combinaison
     */
    public Card getBestCard() {
        // if the combinaison is a straight then the best card is the last card of the hand.
        // And if there is an ACE in the hand, the ACE is NOT the best card.
        if (this.combinaison.equals(Combinaison.STRAIGHT)) {
            return this.hand.getCards().get(this.hand.getCards().size() - 1);
        }
        return this.hand.getBestCard();
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
