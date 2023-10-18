package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.model.*;
import com.seinksansdoozebank.fr.view.Cli;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CliTest {
    private Cli cli;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        cli = new Cli();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void testDisplayWinner() {
        int handNumber = 1;
        Card highestCard = new Card(Rank.TWO);
        Hand hand = new Hand(new ArrayList<>(List.of("A")), handNumber);
        CombinaisonValue combinaisonValue = new CombinaisonValue(Combinaison.HIGHEST_CARD, highestCard);
        Victory victory = new Victory(hand, combinaisonValue);

        cli.displayWinner(victory);

        String expectedOutput = "La main 1 gagne avec carte la plus élevée : 2" + System.lineSeparator(); // Use System.lineSeparator()
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDisplayDraw() {
        cli.displayDraw();

        String expectedOutput = "Egalité" + System.lineSeparator(); // Use System.lineSeparator()
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDisplayHand() {
        int handNumber = 1;
        List<Card> cards = new ArrayList<>(List.of(new Card(Rank.TWO)));

        cli.displayHand(handNumber, cards);

        String expectedOutput = "Main 1: 2 " + System.lineSeparator(); // Use System.lineSeparator()
        assertEquals(expectedOutput, outContent.toString());
    }
}
