package pacman.game.tile;

import pacman.game.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

/**
 * Egy, a pályán lévő tile-t reprezentáló osztály
 */
public abstract class Tile {
    /**
     * A tile helye a pályán
     * Nem képernyő koordinátákat tárol
     */
    private Coordinate mapPosition;

    /**
     * Igaz, ha entitások átsétálhatnak ezen a tile-n
     */
    public boolean isWalkable;

    /**
     * Entity-k, amik jelenleg ezen a Tile-n vannak
     */
    public ArrayList<Entity> entities;

    /**
     * A Tile megjelenítése a képernyőn
     * @param graphics Erre történik a festés
     */
    public abstract void render(Graphics2D graphics);

    /**
     * Visszaadja a Tile pályán elfoglalt helyét
     * @return
     */
    public Coordinate getMapPosition() {
        return mapPosition;
    }
}
