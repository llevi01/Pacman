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
    public Tile(Coordinate mapPosition, BufferedImage sprite) {
        this.mapPosition = mapPosition;
        this.sprite = sprite;
        entities = new ArrayList<>();
    }

    /**
     * A tile helye a pályán
     * Nem képernyő koordinátákat tárol
     */
    protected Coordinate mapPosition;

    /**
     * Igaz, ha entitások átsétálhatnak ezen a tile-n
     */
    public boolean isWalkable() {
        return true;
    }

    /**
     * Entity-k, amik jelenleg ezen a Tile-n vannak
     */
    public ArrayList<Entity> entities;

    /**
     * A sprite ami a képernyőn ezt a Tile-t reprezentálja
     */
    protected BufferedImage sprite;

    /**
     * A Tile megjelenítése a képernyőn
     * @param graphics Erre történik a festés
     */
    public void render(Graphics2D graphics) {
        Coordinate drawPosition = getDrawPosition();

        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_TILE_SIZE, Config.ON_SCREEN_TILE_SIZE, null);
    }

    /**
     * Visszaadja azt a képernyő koordinátát, ahonnan a sprite rajzolását kezdeni kell
     */
    protected Coordinate getDrawPosition() {
        int multiplier = Config.TILE_SPRITE_SIZE * Config.SCALE;
        return new Coordinate(
                this.mapPosition.x * multiplier,
                this.mapPosition.y * multiplier
        );
    }
}
