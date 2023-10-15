package com.seinksansdoozebank.fr.model;

public enum Combinaison {
    HIGHEST_CARD("carte la plus élevée");

    private final String name;


    Combinaison(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Combinaison getCombinaison(String name) {
        for (Combinaison combinaison : Combinaison.values()) {
            if (combinaison.getName().equals(name)) {
                return combinaison;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Combinaison{" +
                "name='" + name + '\'' +
                '}';
    }
}
