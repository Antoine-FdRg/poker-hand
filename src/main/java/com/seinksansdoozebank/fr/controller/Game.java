package com.seinksansdoozebank.fr.controller;


import com.seinksansdoozebank.fr.model.Hand;
import com.seinksansdoozebank.fr.model.Victory;
import com.seinksansdoozebank.fr.view.Cli;

public class Game {

    public void run(){
        Cli view = new Cli();
        Hand hand1 = new Hand(view.initializeHand(1));
        Hand hand2 = new Hand(view.initializeHand(2));
        Referee referee = new Referee();
        Victory winner = referee.compareHands(hand1, hand2);
        if(winner==null){
            view.displayDraw();
        }else{
            view.displayWinner(winner);
        }
    }

}
