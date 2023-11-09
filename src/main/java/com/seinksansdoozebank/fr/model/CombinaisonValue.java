package com.seinksansdoozebank.fr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CombinaisonValue {
    private final Combinaison combinaison;
    private final List<Card> cards;

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
                case PAIR -> {
                    //Création d'une liste dans laquelle on ne garde que les cards qui apparaissent deux fois dans la main
                    List<Card> cardsFilteredByOccurence = getCardsFilteredByOccurence(this.cards, 2);
                    //Création d'une liste dans laquelle on ne garde que les cards qui apparaissent deux fois dans la main
                    List<Card> comparedCardsFilteredByOccurence = getCardsFilteredByOccurence(combinaison2.getCards(), 2);
                    if (cardsFilteredByOccurence.size() != 1 || comparedCardsFilteredByOccurence.size() != 1) {
                        throw new IllegalStateException("There is not a pair in the hand");
                    }
                    // compare the pair of the combinaison
                    result = cardsFilteredByOccurence.get(0).compareTo(comparedCardsFilteredByOccurence.get(0));
                    if (result > 0) {
                        return 1;
                    } else if (result < 0) {
                        return -1;
                    }
                    // compare all kickers of the pair
                    List<Card> kickers = this.getKickers();
                    List<Card> comparedKickers = combinaison2.getKickers();
                    if (kickers.size() != comparedKickers.size()) {
                        throw new IllegalStateException("There is not the same number of kickers");
                    }
                    for (int i = 0; i < kickers.size(); i++) {
                        result = kickers.get(i).compareTo(comparedKickers.get(i));
                        if (result > 0) {
                            return 1;
                        } else if (result < 0) {
                            return -1;
                        }
                    }
                    return 0;
                }
                default -> {
                    return this.getBestCard().compareTo(combinaison2.getBestCard());
                }
            }

        }
    }

    /*
     *  Création d'une map ayant comme clé la card et comme valeur le nombre de fois qu'elle apparait dans la main
     *
     * @param cards
     * @return Map<Card, Integer> map
     */
    protected static Map<Card, Integer> createMapCountingOccurences(List<Card> cards) {
        return cards.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> Collections.frequency(cards, v))
                );
    }

    /**
     * Création d'une liste dans laquelle on ne garde que les cards qui apparaissent un certain nombre de fois dans la main
     * @param cards La liste de cartes
     * @param occurence Le nombre de fois qu'une carte doit apparaitre dans la main
     * @return  List<Card> cards
     */
    public static List<Card> getCardsFilteredByOccurence(List<Card> cards, int occurence) {
        Map<Card, Integer> map = createMapCountingOccurences(cards);
        return new ArrayList<>(map.entrySet().stream()
                .filter(entry -> entry.getValue() == occurence)
                .map(Map.Entry::getKey)
                .toList());
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
                victoryCondition.append("carte la plus élevée : ").append(this.getBestCard().getRank().getName());
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
            case PAIR:
                victoryCondition.append("Paire de ").append(cards.get(0).getRank().getName());
                break;
            default:
                victoryCondition.append(this.combinaison.getName()).append(" : ").append(this.getBestCard().getRank().getName());
                break;
        }
        return victoryCondition.toString();
    }

    /**
     * Get the kicker of the combinaison, usefull when two combinaison are equals
     *
     * @return the kicker of the combinaison
     */
    protected List<Card> getKickers() {
        switch (this.combinaison) {
            case PAIR -> {
                return getCardsFilteredByOccurence(this.cards, 1).stream().sorted(Collections.reverseOrder()).toList();
            }
            default -> throw new IllegalStateException("There is no kicker for this combinaison");
        }
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
}
