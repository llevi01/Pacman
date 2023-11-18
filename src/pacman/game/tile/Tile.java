package pacman.game.tile;

import pacman.game.entity.Entity;
import pacman.game.util.Config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Egy, a pályán lévő tile-t reprezentáló osztály
 */
public abstract class Tile {

    public Tile(Coordinate mapPosition, BufferedImage sprite, boolean isWalkable) {
        this.mapPosition = mapPosition;
        this.sprite = sprite;
        this.isWalkable = isWalkable;
        entities = new ArrayList<>();
    }

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
     * A sprite ami a képernyőn ezt a Tile-t reprezentálja
     */
    private BufferedImage sprite;

    /**
     * A Tile megjelenítése a képernyőn
     * @param graphics Erre történik a festés
     */
    public void render(Graphics2D graphics) {
        Coordinate drawPosition = getDrawPos();

        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_TILE_SIZE, Config.ON_SCREEN_TILE_SIZE, null);
    }

    /**
     * Visszaadja a Tile pályán elfoglalt helyét
     * @return
     */
    public Coordinate getMapPosition() {
        return mapPosition;
    }

    private Coordinate getDrawPos() {
        int multiplier = Config.TILE_SIZE * Config.SCALE;
        return new Coordinate(
                this.mapPosition.x * multiplier,
                this.mapPosition.y * multiplier
        );
    }
}
