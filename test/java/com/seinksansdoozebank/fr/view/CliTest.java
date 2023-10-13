package com.seinksansdoozebank.fr.view;

import com.seinksansdoozebank.fr.controller.Card;
import com.seinksansdoozebank.fr.controller.Combinaison;
import com.seinksansdoozebank.fr.view.Cli;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        Card highestCard = new Card(1);

        cli.displayWinner(handNumber, Combinaison.HIGH_CARD, highestCard);

        String expectedOutput = "La main 1 gagne avec carte la plus élevée : 1\r";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDisplayDraw() {
        cli.displayDraw();

        String expectedOutput = "Egalité\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
