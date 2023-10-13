package com.seinksansdoozebank.fr.model;

public enum Combinaison {
    HIGH_CARD("carte la plus élevée");

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
}
