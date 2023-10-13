package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.view.Cli;

public class Game {

    public void run(){
        int nbCard = 1;
        Cli view = new Cli();
        Hand hand1 = new Hand(view.initializeHand(1));
        Hand hand2 = new Hand(view.initializeHand(1));

    }

}
