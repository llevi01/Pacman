package pacman.game.tile;

/**
 * Koordinátákat kezelő osztály.
 * A koordinátákat a bal felső sarokból kezdve számoljuk,
 * jobbra nő az x, lefelé nő az y.
 */
public class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
