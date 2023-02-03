package game;

import exceptions.InformationIncompleteException;
import exceptions.InvalidCommandException;

import java.io.IOException;

public class Test{
    public static void main(String[] args) throws IOException, InformationIncompleteException, InvalidCommandException {
        Game g = Game.getInstance();
        g.run();
    }
}
