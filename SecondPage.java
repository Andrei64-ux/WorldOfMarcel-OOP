package graphic;
import entities.Character;
import game.Game;

import javax.swing.*;

public class SecondPage {
    public static void initWindow(Character newCharacter) {
        JFrame secondPage = new JFrame("WORLD OF MARCEL");
        secondPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board(newCharacter);
        secondPage.add(board);
        secondPage.addKeyListener(board);

        secondPage.pack();
        secondPage.setVisible(true);
    }
}
