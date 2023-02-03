package cell;
import entities.Character;

public interface CellElement {
    String toCharacter();
    void interract(Character player);
}
